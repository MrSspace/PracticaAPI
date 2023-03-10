package com.blog.practica.controlador;

import com.blog.practica.DTO.ComentarioDTO;
import com.blog.practica.servicio.IComentarioDTOServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publicaciones/{publicacionId}/comentarios")
public class ComentarioControlador implements IComentarioControlador {

    private final IComentarioDTOServicio comentarioDTOServicio;

    public ComentarioControlador(IComentarioDTOServicio comentarioDTOServicio) {
        this.comentarioDTOServicio = comentarioDTOServicio;
    }

    @Override
    @GetMapping
    public List<ComentarioDTO> mostrarLosComentariosDeLaPublicacaion(
            @PathVariable(value = "publicacionId") Long publicacionId){
        return comentarioDTOServicio.obtenerLosComentariosDeLaPublicacion(publicacionId);
    }

    @Override
    @GetMapping("/{comentarioId}")
    public ResponseEntity<ComentarioDTO> buscarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId){
        return ResponseEntity.ok(comentarioDTOServicio.buscarComentarioPorId(comentarioId));
    }

    @Override
    @PostMapping
    public ResponseEntity<ComentarioDTO> crearComentario(
            @PathVariable(value = "publicacionId") Long publicacionId,
            @Valid
            @RequestBody
            ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(
                comentarioDTOServicio.crearComentario(publicacionId,comentarioDTO),
                HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId,
            @Valid
            @RequestBody
            ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(
                comentarioDTOServicio.actualizarComentario(comentarioDTO,comentarioId),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<String> eliminarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId){
        comentarioDTOServicio.eliminarComentario(comentarioId);
        return new ResponseEntity<>(String.format("El comentario # %d fue eliminado",comentarioId),
                HttpStatus.OK);
    }

}
