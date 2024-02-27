package com.ceiba.biblioteca.Repositorios;

import com.ceiba.biblioteca.dto.SolicitudPrestarLibro;
import com.ceiba.biblioteca.dto.SolicitudPrestarLibroRespuesta;

import java.time.LocalDate;
import java.util.Optional;

public interface PrestamoRepositorio {
    int guardar(SolicitudPrestarLibro solicitudPrestarLibro, LocalDate fechaMaximaDevolucion);
    Optional<SolicitudPrestarLibroRespuesta> buscarProIsbn(String isbn);
    Optional<SolicitudPrestarLibroRespuesta> buscarProId(int id);
    boolean buscarProIdentificacionUsuario(String identificacionUsuario);
}
