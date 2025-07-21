package apiTrackline.proyectoPTC.Models.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class DTOOrdenServicio {
    private Long idOrdenServicio;

    // Cliente
    private String clienteNIT;
    private String nombreCliente;
    private String apellidoCliente;
    private String telefonoCliente;
    private String correoCliente;
    private String codEmpresaCliente;
    //Cliente ------> Usuario
    private Long idUsuarioCliente;
    private String nombreUsuarioCliente;

    // Orden Encabezado
    private Long idOrdenEncabezado;
    private Date fechaOrden;
    private String encargadoUno;
    private String referencia;
    private String importador;
    private String nitUno;
    private String registroIvaUno;
    private String facturaA;
    private String encargadoDos;
    private String nitDos;
    private String registroIvaDos;

    // Info Embarque
    private Long idInfoEmbarque;
    private String facturasEmbarque;
    private String proveedorRefEmbarque;
    private Long bultosEmbarque;
    private String tipoEmbarque;
    private Long kilosEmbarque;
    private Long volumenEmbarque;

    // Aduana
    private Long idAduana;
    private String dm;
    private String primeraModalidad;
    private String segundaModalidad;
    private String digitador;
    private String tramitador;
    //Aduana ----->tipoServicio
    private Long idTipoServicio;
    private String nombreTipoServicio;

    // Transporte
    private Long idTransporte;
    //Transporte -----> Transportista
    private Long idTransportista;
    private String nombreTransportista;
    private String apellidoTransportista;
    private String telefonoTransportista;
    private String correoTransportista;
    private String nitTransportista;
    //Transportista -----> Usuario
    private Long idUsuarioTransportista;
    private String nombreUsuarioTransportista;
    //Transporte -----> ServicioTransporte
    private Long idServicioTransporte;
    private String placaServicio;
    private String tarjetaCirculacionServicio;
    private String capacidadServicio;

    // Recolección
    private Long idRecoleccion;
    private Boolean transporteRecoleccion;
    private Boolean recoleccionEntregaRecoleccion;
    private String numeroDoc;
    private String lugarOrigen;
    private String paisOrigen;
    private String lugarDestino;
    private String paisDestino;

    // Cargos
    private Long idCargos;
    private Long montoCargos;
    //Cargos -----> TipoDatoContable
    private Long idTipoDatoContables;
    private String nombreTipoDatoContables;

    // Financiamientos
    private Long idFinanciamiento;
    private Long montoFinanciamiento;
    //Financiamientos -----> TipoFinanciamiento
    private Long idTipoFinanciamiento;
    private String nombretipoFinanciamineto;

    // Observaciones
    private Long idObservaciones;
    private String textoObservacion;
    //Observaciones -----> Selectivo
    private Long idSelectivo;
    private String colorSelectivo;
}