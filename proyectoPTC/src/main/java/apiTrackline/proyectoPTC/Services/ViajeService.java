package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.*;
import apiTrackline.proyectoPTC.Models.DTO.DTOViaje;
import apiTrackline.proyectoPTC.Repositories.ViajeRepository;
import apiTrackline.proyectoPTC.Repositories.OrdenServicioRepository;
import apiTrackline.proyectoPTC.Repositories.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository repo;

    @Autowired
    private OrdenServicioRepository ordenRepo;

    @Autowired
    private TransporteRepository transporteRepo;

    public List<DTOViaje> getAll() {
        List<ViajeEntity> lista = repo.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private DTOViaje convertirADTO(ViajeEntity entity) {
        DTOViaje dto = new DTOViaje();
        dto.setIdViaje(entity.getIdViaje());

        // ----- ORDEN SERVICIO -----
        OrdenServicioEntity orden = entity.getOrdenServicio();
        if (orden != null) {
            dto.setIdOrdenServicio(orden.getIdOrdenServicio());
            dto.setClienteNIT(orden.getClienteNIT());

            if (orden.getCliente() != null) {
                dto.setNombreCliente(orden.getCliente().getNombre());
                dto.setApellidoCliente(orden.getCliente().getApellido());
                dto.setTelefonoCliente(orden.getCliente().getTelefono());
                dto.setCorreoCliente(orden.getCliente().getCorreo());
                dto.setCodEmpresaCliente(orden.getCliente().getCodEmpresa());
                if (orden.getCliente().getUsuario() != null) {
                    dto.setIdUsuarioCliente(orden.getCliente().getUsuario().getIdUsuario());
                    dto.setNombreUsuarioCliente(orden.getCliente().getUsuario().getUsuario());
                }
            }

            if (orden.getIdOrdenEncabezado() != null) {
                var encabezado = orden.getIdOrdenEncabezado();
                dto.setIdOrdenEncabezado(encabezado.getIdOrdenEncabezado());
                dto.setFechaOrden(encabezado.getFecha());
                dto.setEncargadoUno(encabezado.getEncargado());
                dto.setReferencia(encabezado.getReferencia());
                dto.setImportador(encabezado.getImportador());
                dto.setNitUno(encabezado.getNit());
                dto.setRegistroIvaUno(encabezado.getRegistroIva());
                dto.setFacturaA(encabezado.getFacturaA());
                dto.setEncargadoDos(encabezado.getEncargado2());
                dto.setNitDos(encabezado.getNit2());
                dto.setRegistroIvaDos(encabezado.getRegistroIva2());
            }

            if (orden.getAduana() != null) {
                var aduana = orden.getAduana();
                dto.setIdAduana(aduana.getIdAduana());
                dto.setDm(aduana.getDM());
                dto.setPrimeraModalidad(aduana.getPrimeraModalidad());
                dto.setSegundaModalidad(aduana.getSegundaModalidad());
                dto.setDigitador(aduana.getDigitador());
                dto.setTramitador(aduana.getTramitador());
                if (aduana.getTipoServicio() != null) {
                    dto.setIdTipoServicio(aduana.getTipoServicio().getIdTipoServicio());
                    dto.setNombreTipoServicio(aduana.getTipoServicio().getTipoServicio());
                }
            }

            if (orden.getIdTransporte() != null) {
                var transporte = orden.getIdTransporte();
                dto.setIdTransporteOrdenServicio(transporte.getIdTransporte());

                if (transporte.getTransportista() != null) {
                    var t = transporte.getTransportista();
                    dto.setIdTransportistaOrden(t.getIdTransportista());
                    dto.setNombreTransportistaOrden(t.getNombre());
                    dto.setApellidoTransportistaOrden(t.getApellido());
                    dto.setTelefonoTransportistaOrden(t.getTelefono());
                    dto.setCorreoTransportistaOrden(t.getCorreo());
                    dto.setNitTransportistaOrden(t.getNit());

                    if (t.getUsuarioT() != null) {
                        dto.setIdUsuarioTransportistaOrden(t.getUsuarioT().getIdUsuario());
                        dto.setNombreUsuarioTransportistaOrden(t.getUsuarioT().getUsuario());
                    }
                }

                if (transporte.getServicioTransporte() != null) {
                    var st = transporte.getServicioTransporte();
                    dto.setIdServicioTransporteOrden(st.getIdServicioTransporte());
                    dto.setPlacaServicioOrden(st.getPlaca());
                    dto.setTarjetaCirculacionServicioOrden(st.getTarjetaCirculacion());
                    dto.setCapacidadServicioOrden(st.getCapacidad());
                }
            }
        }

        // ----- TRANSPORTE DEL VIAJE -----
        TransporteEntity transporte = entity.getTransporte();
        if (transporte != null) {
            dto.setIdTransporteViaje(transporte.getIdTransporte());

            if (transporte.getTransportista() != null) {
                var t = transporte.getTransportista();
                dto.setIdTransportistaViaje(t.getIdTransportista());
                dto.setNombreTransportistaViaje(t.getNombre());
                dto.setApellidoTransportistaViaje(t.getApellido());
                dto.setTelefonoTransportistaViaje(t.getTelefono());
                dto.setCorreoTransportistaViaje(t.getCorreo());
                dto.setNitTransportistaViaje(t.getNit());

                if (t.getUsuarioT() != null) {
                    dto.setIdUsuarioTransportistaViaje(t.getUsuarioT().getIdUsuario());
                    dto.setNombreUsuarioTransportistaViaje(t.getUsuarioT().getUsuario());
                }
            }

            if (transporte.getServicioTransporte() != null) {
                var st = transporte.getServicioTransporte();
                dto.setIdServicioTransporteViaje(st.getIdServicioTransporte());
                dto.setPlacaServicioViaje(st.getPlaca());
                dto.setTarjetaCirculacionServicioViaje(st.getTarjetaCirculacion());
                dto.setCapacidadServicioViaje(st.getCapacidad());
            }
        }

        return dto;
    }

    public String create(Long idOrdenServicio, Long idTransporte) {
        try {
            Optional<OrdenServicioEntity> orden = ordenRepo.findById(idOrdenServicio);
            Optional<TransporteEntity> transporte = transporteRepo.findById(idTransporte);

            if (orden.isEmpty()) return "Error: Orden de servicio no encontrada";
            if (transporte.isEmpty()) return "Error: Transporte no encontrado";

            ViajeEntity viaje = new ViajeEntity();
            viaje.setOrdenServicio(orden.get());
            viaje.setTransporte(transporte.get());

            repo.save(viaje);
            return "Viaje creado correctamente";

        } catch (Exception e) {
            return "Error al crear viaje: " + e.getMessage();
        }
    }

    public String delete(Long id) {
        try {
            if (!repo.existsById(id)) return "Error: Viaje no encontrado";
            repo.deleteById(id);
            return "Viaje eliminado correctamente";
        } catch (Exception e) {
            return "Error al eliminar viaje: " + e.getMessage();
        }
    }
    public String patch(Long id, Long idOrdenServicio, Long idTransporte) {
        try {
            Optional<ViajeEntity> optionalViaje = repo.findById(id);
            if (optionalViaje.isEmpty()) return "Error: Viaje no encontrado";

            ViajeEntity viaje = optionalViaje.get();

            if (idOrdenServicio != null) {
                Optional<OrdenServicioEntity> orden = ordenRepo.findById(idOrdenServicio);
                if (orden.isEmpty()) return "Error: Orden de servicio no encontrada";
                viaje.setOrdenServicio(orden.get());
            }

            if (idTransporte != null) {
                Optional<TransporteEntity> transporte = transporteRepo.findById(idTransporte);
                if (transporte.isEmpty()) return "Error: Transporte no encontrado";
                viaje.setTransporte(transporte.get());
            }

            repo.save(viaje);
            return "Viaje actualizado correctamente";

        } catch (Exception e) {
            return "Error al actualizar viaje: " + e.getMessage();
        }
    }

    public String putUpdate(Long id, Long idOrdenServicio, Long idTransporte) {
        try {
            Optional<ViajeEntity> optionalViaje = repo.findById(id);
            if (optionalViaje.isEmpty()) return "Error: Viaje no encontrado";

            if (idOrdenServicio == null || idTransporte == null) {
                return "Error: PUT requiere ambos campos: idOrdenServicio e idTransporte";
            }

            Optional<OrdenServicioEntity> orden = ordenRepo.findById(idOrdenServicio);
            Optional<TransporteEntity> transporte = transporteRepo.findById(idTransporte);

            if (orden.isEmpty()) return "Error: Orden de servicio no encontrada";
            if (transporte.isEmpty()) return "Error: Transporte no encontrado";

            ViajeEntity viaje = optionalViaje.get();
            viaje.setOrdenServicio(orden.get());
            viaje.setTransporte(transporte.get());

            repo.save(viaje);
            return "Viaje actualizado (PUT) correctamente";

        } catch (Exception e) {
            return "Error al actualizar viaje con PUT: " + e.getMessage();
        }
    }

}
