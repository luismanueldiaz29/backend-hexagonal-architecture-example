package com.ceiba.biblioteca.infrastructure.adapter.repository;

import com.ceiba.biblioteca.infrastructure.adapter.entity.PrestamoEntity;

import java.util.Optional;

public interface PrestamoRepositorio {
    int guardar(PrestamoEntity prestamoEntity);
    Optional<PrestamoEntity> buscarProIsbn(String isbn);
    Optional<PrestamoEntity> buscarProId(int id);
    boolean buscarProIdentificacionUsuario(String identificacionUsuario);
}
