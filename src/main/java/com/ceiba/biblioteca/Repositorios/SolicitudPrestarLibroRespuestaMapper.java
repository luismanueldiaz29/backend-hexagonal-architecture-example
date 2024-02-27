package com.ceiba.biblioteca.Repositorios;

import com.ceiba.biblioteca.dto.SolicitudPrestarLibroRespuesta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;


public class SolicitudPrestarLibroRespuestaMapper implements RowMapper<SolicitudPrestarLibroRespuesta> {


    @Override
    public SolicitudPrestarLibroRespuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        SolicitudPrestarLibroRespuesta solicitudPrestarLibroRespuesta = new SolicitudPrestarLibroRespuesta();
        solicitudPrestarLibroRespuesta.setId(rs.getInt("id"));
        solicitudPrestarLibroRespuesta.setIsbn(rs.getString("isbn"));
        solicitudPrestarLibroRespuesta.setIdentificacionUsuario(rs.getString("identificacion_usuario"));
        solicitudPrestarLibroRespuesta.setTipoUsuario(rs.getInt("tipo_usuario"));
        solicitudPrestarLibroRespuesta.setFechaMaximaDevolucion(rs.getDate("fecha_maxima_devolucion").toLocalDate());
        return solicitudPrestarLibroRespuesta;
    }


}
