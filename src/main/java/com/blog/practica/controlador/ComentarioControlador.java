package com.blog.practica.controlador;

import com.blog.practica.DTO.ComentarioDTO;
import com.blog.practica.servicio.IComentarioDTOServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioControlador implements IComentarioControlador {

    @Autowired
    private IComentarioDTOServicio comentarioDTOServicio;

    @Override
    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> mostrarLosComentariosDeLaPublicacaion(
            @PathVariable(value = "publicacionId") Long publicacionId){
        return comentarioDTOServicio.obtenerLosComentariosDeLaPublicacion(publicacionId);
    }

    @Override
    @GetMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> buscarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId){
        return ResponseEntity.ok(comentarioDTOServicio.buscarComentarioPorId(comentarioId));
    }

    @Override
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> crearComentario(
            @PathVariable(value = "publicacionId") Long publicacionId,
            @RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(
                comentarioDTOServicio.crearComentario(publicacionId,comentarioDTO),
                HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId,
            @RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(
                comentarioDTOServicio.actualizarComentario(comentarioDTO,comentarioId),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<String> eliminarComentario(
            @PathVariable(value = "comentarioId") Long comentarioId){
        comentarioDTOServicio.eliminarComentario(comentarioId);
        return new ResponseEntity<>(String.format("El comentario # %d fue eliminado",comentarioId),
                HttpStatus.OK);
    }

}
