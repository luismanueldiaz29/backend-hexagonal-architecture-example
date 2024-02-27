package com.ceiba.biblioteca.services;

import com.ceiba.biblioteca.dto.MensajeRespuesta;
import com.ceiba.biblioteca.dto.SolicitudPrestarLibro;
import com.ceiba.biblioteca.dto.SolicitudPrestarLibroRespuesta;
import java.util.Optional;

public interface PrestamoServicio {
    MensajeRespuesta guardar(SolicitudPrestarLibro solicitudPrestarLibro);

    Optional<SolicitudPrestarLibroRespuesta> buscarProId(int id);
}
