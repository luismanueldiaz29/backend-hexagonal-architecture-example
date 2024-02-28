package com.ceiba.biblioteca.application.usecases;

import com.ceiba.biblioteca.domain.model.dto.response.MensajeRespuesta;
import com.ceiba.biblioteca.domain.model.dto.request.PrestamoRequest;
import com.ceiba.biblioteca.domain.model.dto.response.PrestamoRespuesta;
import java.util.Optional;

public interface PrestamoServicio {
    MensajeRespuesta guardar(PrestamoRequest solicitudPrestarLibro);

    Optional<PrestamoRespuesta> buscarProId(int id);
}
