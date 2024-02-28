package com.ceiba.biblioteca.infrastructure.adapter.repository;

import com.ceiba.biblioteca.domain.model.entity.Prestamo;
import com.ceiba.biblioteca.domain.port.PrestamoPersistencePort;
import com.ceiba.biblioteca.infrastructure.adapter.entity.PrestamoEntity;
import com.ceiba.biblioteca.infrastructure.adapter.mapper.PrestamoDboMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrestamoJdbcAdapter implements PrestamoPersistencePort {
    private final PrestamoRepositorio prestamoRepositorio;
    private final PrestamoDboMapper prestamoDboMapper;

    public PrestamoJdbcAdapter(
            PrestamoRepositorio prestamoRepositorio,
            PrestamoDboMapper prestamoDboMapper
    ) {
        this.prestamoRepositorio = prestamoRepositorio;
        this.prestamoDboMapper = prestamoDboMapper;
    }

    @Override
    public int guardar(Prestamo prestamo) {
        PrestamoEntity prestamoEntity = prestamoDboMapper.convertToDbo(prestamo);
        return prestamoRepositorio.guardar(prestamoEntity);
    }

    @Override
    public Prestamo buscarProIsbn(String isbn) {
        return prestamoRepositorio.buscarProIsbn(isbn).map(prestamoDboMapper::convertToDomain).orElse(null);
    }

    @Override
    public Prestamo buscarProId(int id) {
        return prestamoRepositorio.buscarProId(id).map(prestamoDboMapper::convertToDomain).orElse(null);
    }

    @Override
    public boolean buscarProIdentificacionUsuario(String identificacionUsuario) {
        return prestamoRepositorio.buscarProIdentificacionUsuario(identificacionUsuario);
    }
}
