package com.blog.practica.controlador;

import com.blog.practica.DTO.ComentarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IComentarioControlador {

    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> mostrarLosComentariosDeLaPublicacaion(
            @PathVariable(value = "publicacionId") Long publicacionId);

    @GetMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> buscarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId);

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> crearComentario(
            @PathVariable(value = "publicacionId") Long publicacionId,
            @RequestBody ComentarioDTO comentarioDTO);

    @PutMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId,
            @RequestBody ComentarioDTO comentarioDTO);

    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<String> eliminarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId);

}
