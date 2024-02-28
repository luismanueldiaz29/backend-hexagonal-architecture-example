package com.ceiba.biblioteca.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoEntity {
    private int id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private LocalDate fechaMaximaDevolucion;
}
