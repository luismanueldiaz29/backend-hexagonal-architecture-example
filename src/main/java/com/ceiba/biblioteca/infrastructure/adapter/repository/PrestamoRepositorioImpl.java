package com.ceiba.biblioteca.infrastructure.adapter.repository;

import com.ceiba.biblioteca.infrastructure.adapter.entity.PrestamoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoRepositorioImpl implements PrestamoRepositorio{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int guardar(PrestamoEntity prestamoEntity) {
        return jdbcTemplate.update("INSERT INTO PRESTAMOS(\n" +
                "                isbn,\n" +
                "                identificacion_usuario,\n" +
                "                tipo_usuario,\n" +
                "                fecha_maxima_devolucion\n" +
                "            ) VALUES ( ?, ?, ?, ?)", prestamoEntity.getIsbn(), prestamoEntity.getIdentificacionUsuario(), prestamoEntity.getTipoUsuario(), prestamoEntity.getFechaMaximaDevolucion());
    }

    @Override
    public Optional<PrestamoEntity> buscarProIsbn(String isbn) {
        String sql = "SELECT * FROM PRESTAMOS WHERE isbn = ?";
        List<PrestamoEntity> results = jdbcTemplate.query(sql, new Object[]{ isbn }, new SolicitudPrestarLibroRespuestaJdbcMapper());
        if (results.isEmpty()) return Optional.empty();
        return results.stream().findFirst();
    }


    @Override
    public Optional<PrestamoEntity> buscarProId(int id) {
        String query = "SELECT * FROM PRESTAMOS WHERE id = ?";
        List<PrestamoEntity> results = jdbcTemplate.query(query, new Object[]{ id }, new SolicitudPrestarLibroRespuestaJdbcMapper());
        if (results.isEmpty()) return Optional.empty();
        return results.stream().findFirst();
    }

    @Override
    public boolean buscarProIdentificacionUsuario(String identificacionUsuario) {
        String sql = "SELECT * FROM PRESTAMOS WHERE identificacion_usuario = ?";
        List<PrestamoEntity> results = jdbcTemplate.query(sql, new Object[]{ identificacionUsuario }, new SolicitudPrestarLibroRespuestaJdbcMapper());
        return !results.isEmpty();
    }
}
