package com.ceiba.biblioteca;


import com.ceiba.biblioteca.dto.MensajeRespuesta;
import com.ceiba.biblioteca.dto.SolicitudPrestarLibro;
import com.ceiba.biblioteca.dto.SolicitudPrestarLibroRespuesta;
import com.ceiba.biblioteca.services.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    @Autowired
    private PrestamoServicio prestamoServicio;

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudPrestarLibroRespuesta> buscarPorId(@PathVariable("id") int id){
        Optional<SolicitudPrestarLibroRespuesta> solicitudPrestarLibroRespuesta = prestamoServicio.buscarProId(id);
        return solicitudPrestarLibroRespuesta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.accepted().body(null));
    }

    @PostMapping
    public ResponseEntity<MensajeRespuesta> guardar(@RequestBody SolicitudPrestarLibro solicitudPrestarLibro){
        MensajeRespuesta mensajeRespuesta = prestamoServicio.guardar(solicitudPrestarLibro);

        if (mensajeRespuesta.getMensaje() != null)
            return ResponseEntity.badRequest().body(mensajeRespuesta);

        return ResponseEntity.ok(mensajeRespuesta);
    }
}

