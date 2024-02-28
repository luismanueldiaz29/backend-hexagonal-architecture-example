package com.ceiba.biblioteca.application.services;

import com.ceiba.biblioteca.application.mapper.PrestamoMapper;
import com.ceiba.biblioteca.application.usecases.PrestamoServicio;
import com.ceiba.biblioteca.domain.constant.PrestamoConstant;
import com.ceiba.biblioteca.domain.model.entity.Prestamo;
import com.ceiba.biblioteca.domain.port.PrestamoPersistencePort;
import com.ceiba.biblioteca.domain.model.dto.response.MensajeRespuesta;
import com.ceiba.biblioteca.domain.model.dto.request.PrestamoRequest;
import com.ceiba.biblioteca.domain.model.dto.response.PrestamoRespuesta;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PrestamoServicioImpl implements PrestamoServicio {
    private final PrestamoMapper prestamoMapper;
    private final PrestamoPersistencePort prestamoPort;

    public PrestamoServicioImpl(
            PrestamoMapper prestamoMapper,
            PrestamoPersistencePort prestamoPort
    ) {
        this.prestamoMapper = prestamoMapper;
        this.prestamoPort = prestamoPort;
    }

    @Override
    public MensajeRespuesta guardar(PrestamoRequest solicitudPrestarLibro) {
        MensajeRespuesta mensajeRespuesta = new MensajeRespuesta();
        if (solicitudPrestarLibro.getTipoUsuario() == 3 && prestamoPort.buscarProIdentificacionUsuario(solicitudPrestarLibro.getIdentificacionUsuario())) {
            mensajeRespuesta.setMensaje(String.format(PrestamoConstant.PRESTAMO_ENCONTRADO, solicitudPrestarLibro.getIdentificacionUsuario()));
            return mensajeRespuesta;
        }

        LocalDate fechaMaximaDevolucion = obtenerFechaMaximaDevolucion(solicitudPrestarLibro);
        if (fechaMaximaDevolucion == null){
            mensajeRespuesta.setMensaje(PrestamoConstant.PRESTAMO_NO_PERMITIDO);
            return mensajeRespuesta;
        }
        
        Prestamo prestamo = prestamoMapper.convertToDomain(solicitudPrestarLibro);
        prestamo.setFechaMaximaDevolucion(fechaMaximaDevolucion);
        prestamoPort.guardar(prestamo);
        
        PrestamoRespuesta respuesta = prestamoMapper.convertToDto(prestamoPort.buscarProIsbn(solicitudPrestarLibro.getIsbn()));
        if (respuesta == null)
            return mensajeRespuesta;

        respuesta.getFechaMaximaDevolucion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        mensajeRespuesta.setId(respuesta.getId());
        mensajeRespuesta.setFechaMaximaDevolucion(respuesta.getFechaMaximaDevolucion());
        return mensajeRespuesta;
    }

    @Override
    public Optional<PrestamoRespuesta> buscarProId(int id) {
        return Optional.of(prestamoPort.buscarProId(id)).map(prestamoMapper::convertToDto);
    }

    private LocalDate obtenerFechaMaximaDevolucion(PrestamoRequest solicitudPrestarLibro) {
        LocalDate fechaMaximaDevolucion;
        switch (solicitudPrestarLibro.getTipoUsuario()){
            case 1:
                fechaMaximaDevolucion = aggregarFechasIgnorandoFinSemana(LocalDate.now(), 10);
                break;
            case 2:
                fechaMaximaDevolucion = aggregarFechasIgnorandoFinSemana(LocalDate.now(), 8);
                break;
            case 3:
                fechaMaximaDevolucion = aggregarFechasIgnorandoFinSemana(LocalDate.now(), 7);
                break;
            default:
                fechaMaximaDevolucion = null;
                break;
        }
        return fechaMaximaDevolucion;
    }

    private LocalDate aggregarFechasIgnorandoFinSemana(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
}
