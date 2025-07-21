package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.*;
import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenServicio;
import apiTrackline.proyectoPTC.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenServicioService {

    @Autowired
    private OrdenServicioRepository repo;

    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private OrdenEncabezadoRepository ordenEncabezadoRepo;
    @Autowired
    private InfoEmbarqueRepository infoEmbarqueRepo;
    @Autowired
    private AduanaRepository aduanaRepo;
    @Autowired
    private TransporteRepository transporteRepo;
    @Autowired
    private RecoleccionRepository recoleccionRepo;
    @Autowired
    private CargosRepository cargosRepo;
    @Autowired
    private FinanciamientoRepository financiamientoRepo;
    @Autowired
    private ObservacionesRepository observacionesRepo;

    // Obtener todos los registros y convertirlos a DTO
    public List<DTOOrdenServicio> getData() {
        List<OrdenServicioEntity> lista = repo.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private DTOOrdenServicio convertirADTO(OrdenServicioEntity entity) {
        DTOOrdenServicio dto = new DTOOrdenServicio();

        // OrdenServicio
        dto.setIdOrdenServicio(entity.getIdOrdenServicio());
        dto.setClienteNIT(entity.getClienteNIT());

        // Cliente y Usuario del Cliente
        if (entity.getCliente() != null) {
            dto.setNombreCliente(entity.getCliente().getNombre());
            dto.setApellidoCliente(entity.getCliente().getApellido());
            dto.setTelefonoCliente(entity.getCliente().getTelefono());
            dto.setCorreoCliente(entity.getCliente().getCorreo());
            dto.setCodEmpresaCliente(entity.getCliente().getCodEmpresa());

            if (entity.getCliente().getUsuario() != null) {
                dto.setIdUsuarioCliente(entity.getCliente().getUsuario().getIdUsuario());
                dto.setNombreUsuarioCliente(entity.getCliente().getUsuario().getUsuario());
            }
        }

        // Orden Encabezado
        if (entity.getIdOrdenEncabezado() != null) {
            dto.setIdOrdenEncabezado(entity.getIdOrdenEncabezado().getIdOrdenEncabezado());
            dto.setFechaOrden(entity.getIdOrdenEncabezado().getFecha());
            dto.setEncargadoUno(entity.getIdOrdenEncabezado().getEncargado());
            dto.setReferencia(entity.getIdOrdenEncabezado().getReferencia());
            dto.setImportador(entity.getIdOrdenEncabezado().getImportador());
            dto.setNitUno(entity.getIdOrdenEncabezado().getNit());
            dto.setRegistroIvaUno(entity.getIdOrdenEncabezado().getRegistroIva());
            dto.setFacturaA(entity.getIdOrdenEncabezado().getFacturaA());
            dto.setEncargadoDos(entity.getIdOrdenEncabezado().getEncargado2());
            dto.setNitDos(entity.getIdOrdenEncabezado().getNit2());
            dto.setRegistroIvaDos(entity.getIdOrdenEncabezado().getRegistroIva2());
        }

        // Info Embarque
        if (entity.getIdInfoEmbarque() != null) {
            dto.setIdInfoEmbarque(entity.getIdInfoEmbarque().getIdInfoEmbarque());
            dto.setFacturasEmbarque(entity.getIdInfoEmbarque().getFacturas());
            dto.setProveedorRefEmbarque(entity.getIdInfoEmbarque().getProveedorRef());
            dto.setBultosEmbarque(entity.getIdInfoEmbarque().getBultos());
            dto.setTipoEmbarque(entity.getIdInfoEmbarque().getTipo());
            dto.setKilosEmbarque(entity.getIdInfoEmbarque().getKilos());
            dto.setVolumenEmbarque(entity.getIdInfoEmbarque().getVolumen());
        }

        // Aduana
        if (entity.getAduana() != null) {
            dto.setIdAduana(entity.getAduana().getIdAduana());
            dto.setDm(entity.getAduana().getDM());
            dto.setPrimeraModalidad(entity.getAduana().getPrimeraModalidad());
            dto.setSegundaModalidad(entity.getAduana().getSegundaModalidad());
            dto.setDigitador(entity.getAduana().getDigitador());
            dto.setTramitador(entity.getAduana().getTramitador());

            if (entity.getAduana().getTipoServicio() != null) {
                dto.setIdTipoServicio(entity.getAduana().getTipoServicio().getIdTipoServicio());
                dto.setNombreTipoServicio(entity.getAduana().getTipoServicio().getTipoServicio());
            }
        }

        // Transporte
        if (entity.getIdTransporte() != null) {
            dto.setIdTransporte(entity.getIdTransporte().getIdTransporte());

            // Transportista
            if (entity.getIdTransporte().getTransportista() != null) {
                dto.setIdTransportista(entity.getIdTransporte().getTransportista().getIdTransportista());
                dto.setNombreTransportista(entity.getIdTransporte().getTransportista().getNombre());
                dto.setApellidoTransportista(entity.getIdTransporte().getTransportista().getApellido());
                dto.setTelefonoTransportista(entity.getIdTransporte().getTransportista().getTelefono());
                dto.setCorreoTransportista(entity.getIdTransporte().getTransportista().getCorreo());
                dto.setNitTransportista(entity.getIdTransporte().getTransportista().getNit());

                // Usuario del transportista
                if (entity.getIdTransporte().getTransportista().getUsuarioT() != null) {
                    dto.setIdUsuarioTransportista(entity.getIdTransporte().getTransportista().getUsuarioT().getIdUsuario());
                    dto.setNombreUsuarioTransportista(entity.getIdTransporte().getTransportista().getUsuarioT().getUsuario());
                }
            }

            // Servicio Transporte
            if (entity.getIdTransporte().getServicioTransporte() != null) {
                dto.setIdServicioTransporte(entity.getIdTransporte().getServicioTransporte().getIdServicioTransporte());
                dto.setPlacaServicio(entity.getIdTransporte().getServicioTransporte().getPlaca());
                dto.setTarjetaCirculacionServicio(entity.getIdTransporte().getServicioTransporte().getTarjetaCirculacion());
                dto.setCapacidadServicio(entity.getIdTransporte().getServicioTransporte().getCapacidad());
            }
        }

        // Recolección
        if (entity.getIdRecoleccion() != null) {
            dto.setIdRecoleccion(entity.getIdRecoleccion().getIdRecoleccion());
            dto.setTransporteRecoleccion(entity.getIdRecoleccion().getTransporte());
            dto.setRecoleccionEntregaRecoleccion(entity.getIdRecoleccion().getRecoleccionEntrega());
            dto.setNumeroDoc(entity.getIdRecoleccion().getNumeroDoc());
            dto.setLugarOrigen(entity.getIdRecoleccion().getLugarOrigen());
            dto.setPaisOrigen(entity.getIdRecoleccion().getPaisOrigen());
            dto.setLugarDestino(entity.getIdRecoleccion().getLugarDestino());
            dto.setPaisDestino(entity.getIdRecoleccion().getPaisDestino());
        }

        // Cargos
        if (entity.getIdCargos() != null) {
            dto.setIdCargos(entity.getIdCargos().getIdCargos());
            dto.setMontoCargos(entity.getIdCargos().getMonto());

            if (entity.getIdCargos().getTipoDatoContable() != null) {
                dto.setIdTipoDatoContables(entity.getIdCargos().getTipoDatoContable().getIdTipoDatoContable());
                dto.setNombreTipoDatoContables(entity.getIdCargos().getTipoDatoContable().getNombre());
            }
        }

        // Financiamiento
        if (entity.getIdFinanciamiento() != null) {
            dto.setIdFinanciamiento(entity.getIdFinanciamiento().getIdFinanciamiento());
            dto.setMontoFinanciamiento(entity.getIdFinanciamiento().getMonto());

            if (entity.getIdFinanciamiento().getTipoFinanciamiento() != null) {
                dto.setIdTipoFinanciamiento(entity.getIdFinanciamiento().getTipoFinanciamiento().getIdTipoFinanciamiento());
                dto.setNombretipoFinanciamineto(entity.getIdFinanciamiento().getTipoFinanciamiento().getNombre());
            }
        }

        // Observaciones
        if (entity.getIdObservaciones() != null) {
            dto.setIdObservaciones(entity.getIdObservaciones().getIdObservaciones());
            dto.setTextoObservacion(entity.getIdObservaciones().getObservaciones());

            if (entity.getIdObservaciones().getIdSelectivo() != null) {
                dto.setIdSelectivo(entity.getIdObservaciones().getIdSelectivo().getIdSelectivo());
                dto.setColorSelectivo(entity.getIdObservaciones().getIdSelectivo().getColorSelectivo());
            }
        }

        return dto;
    }


    public String post(DTOOrdenServicio dto) {
        try {
            OrdenServicioEntity entity = new OrdenServicioEntity();

            // Cliente
            if (dto.getClienteNIT() != null) {
                Optional<ClientesEntity> clientes = clientesRepository.findById(dto.getClienteNIT());
                if (clientes.isPresent()) {
                    entity.setCliente(clientes.get());
                    entity.setClienteNIT(dto.getClienteNIT());
                } else {
                    return "Error: Cliente no encontrado";
                }
            }

            // Orden Encabezado
            if (dto.getIdOrdenEncabezado() != null) {
                Optional<OrdenEncabezadoEntity> ordenEncabezado = ordenEncabezadoRepo.findById(dto.getIdOrdenEncabezado());
                if (ordenEncabezado.isPresent()) {
                    entity.setIdOrdenEncabezado(ordenEncabezado.get());
                } else {
                    return "Error: Orden encabezado no encontrado";
                }
            }

            // Info Embarque
            if (dto.getIdInfoEmbarque() != null) {
                Optional<InfoEmbarqueEntity> infoEmbarque = infoEmbarqueRepo.findById(dto.getIdInfoEmbarque());
                if (infoEmbarque.isPresent()) {
                    entity.setIdInfoEmbarque(infoEmbarque.get());
                } else {
                    return "Error: Info embarque no encontrada";
                }
            }

            // Aduana
            if (dto.getIdAduana() != null) {
                Optional<AduanaEntity> aduana = aduanaRepo.findById(dto.getIdAduana());
                if (aduana.isPresent()) {
                    entity.setAduana(aduana.get());
                } else {
                    return "Error: Aduana no encontrada";
                }
            }

            // Transporte
            if (dto.getIdTransporte() != null) {
                Optional<TransporteEntity> transporte = transporteRepo.findById(dto.getIdTransporte());
                if (transporte.isPresent()) {
                    entity.setIdTransporte(transporte.get());
                } else {
                    return "Error: Transporte no encontrado";
                }
            }

            // Recolección
            if (dto.getIdRecoleccion() != null) {
                Optional<RecoleccionEntity> recoleccion = recoleccionRepo.findById(dto.getIdRecoleccion());
                if (recoleccion.isPresent()) {
                    entity.setIdRecoleccion(recoleccion.get());
                } else {
                    return "Error: Recolección no encontrada";
                }
            }

            // Cargos
            if (dto.getIdCargos() != null) {
                Optional<CargosEntity> cargos = cargosRepo.findById(dto.getIdCargos());
                if (cargos.isPresent()) {
                    entity.setIdCargos(cargos.get());
                } else {
                    return "Error: Cargos no encontrados";
                }
            }

            // Financiamiento
            if (dto.getIdFinanciamiento() != null) {
                Optional<FinanciamientoEntity> financiamiento = financiamientoRepo.findById(dto.getIdFinanciamiento());
                if (financiamiento.isPresent()) {
                    entity.setIdFinanciamiento(financiamiento.get());
                } else {
                    return "Error: Financiamiento no encontrado";
                }
            }

            // Observaciones
            if (dto.getIdObservaciones() != null) {
                Optional<ObservacionesEntity> observaciones = observacionesRepo.findById(dto.getIdObservaciones());
                if (observaciones.isPresent()) {
                    entity.setIdObservaciones(observaciones.get());
                } else {
                    return "Error: Observaciones no encontradas";
                }
            }

            // Guardar entidad
            repo.save(entity);
            return "Orden de servicio creada correctamente";

        } catch (Exception e) {
            return "Error al crear orden de servicio: " + e.getMessage();
        }
    }


    public String update(Long id, DTOOrdenServicio dto) {
        Optional<OrdenServicioEntity> optional = repo.findById(id);
        if (optional.isEmpty()) return "Orden de servicio no encontrada";

        OrdenServicioEntity entity = optional.get();
        entity.setClienteNIT(dto.getClienteNIT());
        setRelaciones(dto, entity);
        repo.save(entity);
        return "Orden de servicio actualizada correctamente";
    }

    public String patch(Long id, DTOOrdenServicio dto) {
        Optional<OrdenServicioEntity> optional = repo.findById(id);
        if (optional.isEmpty()) return "Orden de servicio no encontrada";

        if (dto.getFechaOrden() != null &&(dto.getFechaOrden())) {
            return "Fecha inválida: debe ser entre 1990 y hoy.";
        }

        OrdenServicioEntity entity = optional.get();
        if (dto.getClienteNIT() != null) entity.setClienteNIT(dto.getClienteNIT());
        (dto, entity);
        repo.save(entity);
        return "Orden de servicio actualizada parcialmente.";
    }

    public String delete(Long id) {
        if (!repo.existsById(id)) return "Orden de servicio no encontrada";
        repo.deleteById(id);
        return "Orden de servicio eliminada correctamente";
    }


}