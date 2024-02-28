package com.ceiba.biblioteca.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoRequest {
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
}

