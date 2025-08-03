package apiTrackline.proyectoPTC.Exceptions.ValidacionesGlobales;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Esta anotación indica que esta clase manejará errores globalmente para todos los controladores.
public class ValidacionGlobalHandler {

    // Este método captura errores de validaciones como @NotBlank, @Size, @Pattern, etc.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        // Recorre todos los errores de los campos del DTO y los agrega al mapa
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        // Devuelve un mapa con los nombres de campo y sus mensajes de error, con código 400 Bad Request
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    // Este metodo captura errores de formato en el cuerpo de la petición, como fechas mal escritas o tipos numéricos inválidos.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> manejarErroresDeLectura(HttpMessageNotReadableException ex) {
        Map<String, String> errores = new HashMap<>();

        // Verificamos si la causa fue un formato inválido (por ejemplo, fecha mal escrita)
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) ex.getCause();

            // Recorremos los paths del error para identificar el campo que causó el error
            ife.getPath().forEach(ref -> {
                String campo = ref.getFieldName();
                String mensaje;

                // Si el tipo esperado era Date, damos un mensaje personalizado
                if (ref.getFrom() instanceof java.util.Date || ref.getFrom().toString().contains("Date")) {
                    mensaje = "El formato de fecha es inválido. Use 'dd-MM-yyyy'.";
                } else {
                    mensaje = "El valor enviado no es válido para el campo: " + campo + ". Verifique los datos";
                }

                errores.put(campo, mensaje);
            });
        } else {
            // Si no se sabe la causa exacta, mostramos un mensaje genérico
            errores.put("error", "Error en el formato de la solicitud. Verifique los datos enviados.");
        }

        // Devuelve los errores con código 400 Bad Request
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
