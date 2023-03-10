package com.blog.practica.controlador;

import com.blog.practica.DTO.ComentarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IComentarioControlador {

    @GetMapping
    public List<ComentarioDTO> mostrarLosComentariosDeLaPublicacaion(
            @PathVariable(value = "publicacionId") Long publicacionId);

    @GetMapping("/{comentarioId}")
    public ResponseEntity<ComentarioDTO> buscarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId);

    @PostMapping
    public ResponseEntity<ComentarioDTO> crearComentario(
            @PathVariable(value = "publicacionId") Long publicacionId,
            @RequestBody ComentarioDTO comentarioDTO);

    @PutMapping("/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId,
            @RequestBody ComentarioDTO comentarioDTO);

    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<String> eliminarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId);

}
