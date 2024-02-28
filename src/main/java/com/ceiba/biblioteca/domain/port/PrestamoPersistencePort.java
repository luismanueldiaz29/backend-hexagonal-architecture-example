package com.ceiba.biblioteca.domain.port;

import com.ceiba.biblioteca.domain.model.entity.Prestamo;

public interface PrestamoPersistencePort {
    int guardar(Prestamo prestamo);
    Prestamo buscarProIsbn(String isbn);
    Prestamo buscarProId(int id);
    boolean buscarProIdentificacionUsuario(String identificacionUsuario);
}
