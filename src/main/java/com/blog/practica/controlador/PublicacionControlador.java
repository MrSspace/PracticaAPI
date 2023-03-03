package com.blog.practica.controlador;

import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.servicio.PublicacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/publicaciones"})
public class PublicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    @GetMapping
    public List<PublicacionDTO> listarPublicaciones(
            @RequestParam(value = "numeroDeLaPagina", defaultValue = "0", required = false)
                int numeroDeLaPagina,
            @RequestParam(value = "publicacionesPorPagina", defaultValue = "10", required = false)
                int publicacionesPorPagina){
        return publicacionServicio.buscarTodasLasPublicaciones(numeroDeLaPagina,publicacionesPorPagina);
    }

    @GetMapping("/buscar/titulo/{titulo}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorTitulo(
            @PathVariable(name = "titulo") String titulo){
        return ResponseEntity.ok(publicacionServicio.buscarPublicacionPorTitulo(titulo));
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(
            @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(publicacionServicio.buscarPublicacionPorId(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<PublicacionDTO> crearPublicacion(
            @RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<PublicacionDTO>(
                publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(
            @RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(
                publicacionServicio.actualizarPublicacion(publicacionDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id){
        publicacionServicio.eliminarPublicacion(id);
        return new ResponseEntity<>("Se ha eliminado la publicacion", HttpStatus.OK);
    }

}
