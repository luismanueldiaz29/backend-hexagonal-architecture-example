package com.ceiba.biblioteca.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {
    private int id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private LocalDate fechaMaximaDevolucion;
}
