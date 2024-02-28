package com.ceiba.biblioteca.infrastructure.adapter.repository;

import com.ceiba.biblioteca.infrastructure.adapter.entity.PrestamoEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SolicitudPrestarLibroRespuestaJdbcMapper implements RowMapper<PrestamoEntity> {


    @Override
    public PrestamoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        PrestamoEntity solicitudPrestarLibroRespuesta = new PrestamoEntity();
        solicitudPrestarLibroRespuesta.setId(rs.getInt("id"));
        solicitudPrestarLibroRespuesta.setIsbn(rs.getString("isbn"));
        solicitudPrestarLibroRespuesta.setIdentificacionUsuario(rs.getString("identificacion_usuario"));
        solicitudPrestarLibroRespuesta.setTipoUsuario(rs.getInt("tipo_usuario"));
        solicitudPrestarLibroRespuesta.setFechaMaximaDevolucion(rs.getDate("fecha_maxima_devolucion").toLocalDate());
        return solicitudPrestarLibroRespuesta;
    }


}
