package com.blog.practica.controlador;

import com.blog.practica.DTO.ColeccionDePublicacionesDTO;
import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.utilerias.AppConstantes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IPublicacionControlador {

    @GetMapping
    public ColeccionDePublicacionesDTO listarPublicaciones(
            @RequestParam(value = "numeroDeLaPagina") int numeroDeLaPagina,
            @RequestParam(value = "publicacionesPorPagina") int publicacionesPorPagina,
            @RequestParam(value = "sortByAtributo") String ordenarPor,
            @RequestParam(value = "sortDir") String sortDir);

    @GetMapping("{titulo}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorTitulo(
            @PathVariable(name = "titulo") String titulo);

    @GetMapping("{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(
            @PathVariable(name = "id") Long id);

    @PostMapping
    public ResponseEntity<PublicacionDTO> crearPublicacion(
            @RequestBody PublicacionDTO publicacionDTO);

    @PutMapping("{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(
            @RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id") Long id);

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id);

}
