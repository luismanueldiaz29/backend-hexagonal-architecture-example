package com.ceiba.biblioteca.infrastructure.adapter.mapper;

import com.ceiba.biblioteca.domain.model.entity.Prestamo;
import com.ceiba.biblioteca.infrastructure.adapter.entity.PrestamoEntity;
import org.springframework.stereotype.Service;

@Service
public class PrestamoDboMapper {

    public Prestamo convertToDomain(PrestamoEntity prestamoEntity){
        return Prestamo.builder()
                .id(prestamoEntity.getId())
                .isbn(prestamoEntity.getIsbn())
                .identificacionUsuario(prestamoEntity.getIdentificacionUsuario())
                .tipoUsuario(prestamoEntity.getTipoUsuario())
                .fechaMaximaDevolucion(prestamoEntity.getFechaMaximaDevolucion())
                .build();
    }

    public PrestamoEntity convertToDbo(Prestamo domain){
        return PrestamoEntity.builder()
                .id(domain.getId())
                .isbn(domain.getIsbn())
                .identificacionUsuario(domain.getIdentificacionUsuario())
                .tipoUsuario(domain.getTipoUsuario())
                .fechaMaximaDevolucion(domain.getFechaMaximaDevolucion())
                .build();
    }
}
