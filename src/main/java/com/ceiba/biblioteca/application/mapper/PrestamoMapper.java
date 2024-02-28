package com.ceiba.biblioteca.application.mapper;

import com.ceiba.biblioteca.domain.model.dto.request.PrestamoRequest;
import com.ceiba.biblioteca.domain.model.dto.response.PrestamoRespuesta;
import com.ceiba.biblioteca.domain.model.entity.Prestamo;
import org.springframework.stereotype.Service;

@Service
public class PrestamoMapper {

    public Prestamo convertToDomain(PrestamoRequest request){
        return Prestamo.builder()
                .isbn(request.getIsbn())
                .identificacionUsuario(request.getIdentificacionUsuario())
                .tipoUsuario(request.getTipoUsuario())
                .build();
    }

    public PrestamoRespuesta convertToDto(Prestamo domain){
        return PrestamoRespuesta.builder()
                .id(domain.getId())
                .isbn(domain.getIsbn())
                .identificacionUsuario(domain.getIdentificacionUsuario())
                .tipoUsuario(domain.getTipoUsuario())
                .fechaMaximaDevolucion(domain.getFechaMaximaDevolucion())
                .build();
    }
}
