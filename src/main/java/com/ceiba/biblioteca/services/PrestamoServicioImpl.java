package com.ceiba.biblioteca.services;

import com.ceiba.biblioteca.Repositorios.PrestamoRepositorio;
import com.ceiba.biblioteca.dto.MensajeRespuesta;
import com.ceiba.biblioteca.dto.SolicitudPrestarLibro;
import com.ceiba.biblioteca.dto.SolicitudPrestarLibroRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PrestamoServicioImpl implements PrestamoServicio{

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Override
    public MensajeRespuesta guardar(SolicitudPrestarLibro solicitudPrestarLibro) {
        MensajeRespuesta mensajeRespuesta = new MensajeRespuesta();
        if (solicitudPrestarLibro.getTipoUsuario() == 3 && prestamoRepositorio.buscarProIdentificacionUsuario(solicitudPrestarLibro.getIdentificacionUsuario())) {
            mensajeRespuesta.setMensaje("El usuario con identificación "+solicitudPrestarLibro.getIdentificacionUsuario()+" ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
            return mensajeRespuesta;
        }

        LocalDate fechaMaximaDevolucion = obtenerFechaMaximaDevolucion(solicitudPrestarLibro);
        if (fechaMaximaDevolucion == null){
            mensajeRespuesta.setMensaje("Tipo de usuario no permitido en la biblioteca");
            return mensajeRespuesta;
        }

        prestamoRepositorio.guardar(solicitudPrestarLibro, fechaMaximaDevolucion);
        SolicitudPrestarLibroRespuesta respuesta = prestamoRepositorio.buscarProIsbn(solicitudPrestarLibro.getIsbn()).orElse(null);
        if (respuesta == null)
            return mensajeRespuesta;

        respuesta.getFechaMaximaDevolucion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        mensajeRespuesta.setId(respuesta.getId());
        mensajeRespuesta.setFechaMaximaDevolucion(respuesta.getFechaMaximaDevolucion());
        return mensajeRespuesta;
    }

    @Override
    public Optional<SolicitudPrestarLibroRespuesta> buscarProId(int id) {
        return prestamoRepositorio.buscarProId(id);
    }

    private LocalDate obtenerFechaMaximaDevolucion(SolicitudPrestarLibro solicitudPrestarLibro) {
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
