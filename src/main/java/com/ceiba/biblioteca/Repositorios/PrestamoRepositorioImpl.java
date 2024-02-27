package com.ceiba.biblioteca.Repositorios;

import com.ceiba.biblioteca.dto.SolicitudPrestarLibro;
import com.ceiba.biblioteca.dto.SolicitudPrestarLibroRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoRepositorioImpl implements PrestamoRepositorio{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int guardar(SolicitudPrestarLibro solicitudPrestarLibro, LocalDate fechaMaximaDevolucion) {
        return jdbcTemplate.update("INSERT INTO PRESTAMOS(\n" +
                "                isbn,\n" +
                "                identificacion_usuario,\n" +
                "                tipo_usuario,\n" +
                "                fecha_maxima_devolucion\n" +
                "            ) VALUES ( ?, ?, ?, ?)", solicitudPrestarLibro.getIsbn(), solicitudPrestarLibro.getIdentificacionUsuario(), solicitudPrestarLibro.getTipoUsuario(), fechaMaximaDevolucion);
    }

    @Override
    public Optional<SolicitudPrestarLibroRespuesta> buscarProIsbn(String isbn) {
        String sql = "SELECT * FROM PRESTAMOS WHERE isbn = ?";
        List<SolicitudPrestarLibroRespuesta> results = jdbcTemplate.query(sql, new Object[]{ isbn }, new SolicitudPrestarLibroRespuestaMapper());
        if (results.isEmpty()) return Optional.empty();
        return results.stream().findFirst();
    }


    @Override
    public Optional<SolicitudPrestarLibroRespuesta> buscarProId(int id) {
        String query = "SELECT * FROM PRESTAMOS WHERE id = ?";
        List<SolicitudPrestarLibroRespuesta> results = jdbcTemplate.query(query, new Object[]{ id }, new SolicitudPrestarLibroRespuestaMapper());
        if (results.isEmpty()) return Optional.empty();
        return results.stream().findFirst();
    }

    @Override
    public boolean buscarProIdentificacionUsuario(String identificacionUsuario) {
        String sql = "SELECT * FROM PRESTAMOS WHERE identificacion_usuario = ?";
        List<SolicitudPrestarLibroRespuesta> results = jdbcTemplate.query(sql, new Object[]{ identificacionUsuario }, new SolicitudPrestarLibroRespuestaMapper());
        return !results.isEmpty();
    }
}
