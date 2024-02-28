package com.ceiba.biblioteca.infrastructure.rest.controller;


import com.ceiba.biblioteca.domain.model.dto.response.MensajeRespuesta;
import com.ceiba.biblioteca.domain.model.dto.request.PrestamoRequest;
import com.ceiba.biblioteca.domain.model.dto.response.PrestamoRespuesta;
import com.ceiba.biblioteca.application.usecases.PrestamoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/prestamo")
public class PrestamoControlador {

    private final PrestamoServicio prestamoServicio;

    public PrestamoControlador(
            PrestamoServicio prestamoServicio
    ) {
        this.prestamoServicio = prestamoServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoRespuesta> buscarPorId(@PathVariable("id") int id){
        Optional<PrestamoRespuesta> solicitudPrestarLibroRespuesta = prestamoServicio.buscarProId(id);
        return solicitudPrestarLibroRespuesta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.accepted().body(null));
    }

    @PostMapping
    public ResponseEntity<MensajeRespuesta> guardar(@RequestBody PrestamoRequest solicitudPrestarLibro){
        MensajeRespuesta mensajeRespuesta = prestamoServicio.guardar(solicitudPrestarLibro);

        if (mensajeRespuesta.getMensaje() != null)
            return ResponseEntity.badRequest().body(mensajeRespuesta);

        return ResponseEntity.ok(mensajeRespuesta);
    }
}

