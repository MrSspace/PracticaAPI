package com.blog.practica.controlador;

import com.blog.practica.DTO.ColeccionDePublicacionesDTO;
import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.servicio.PublicacionServicio;
import com.blog.practica.utilerias.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/publicaciones"})
public class PublicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    @GetMapping
    public ColeccionDePublicacionesDTO listarPublicaciones(
        @RequestParam(value = "numeroDeLaPagina",
                defaultValue = AppConstantes.NUMERO_DE_LA_PAGINA_POR_DEFECTO,
                required = false) int numeroDeLaPagina,
        @RequestParam(value = "publicacionesPorPagina",
                defaultValue = AppConstantes.PUBLICACIONES_POR_PAGINA_POR_DEFECTO,
                required = false) int publicacionesPorPagina,
        @RequestParam(value = "sortByAtributo",
                defaultValue = AppConstantes.ORDENAR_POR_ATRIBUTO_POR_DEFECTO,
                required = false) String ordenarPor,
        @RequestParam(value = "sortDir",
                defaultValue = AppConstantes.DIRECCION_DE_ORDENAMIENTO_POR_DEFECTO,
                required = false) String sortDir){
        return publicacionServicio.buscarTodasLasPublicaciones(
                numeroDeLaPagina,publicacionesPorPagina,ordenarPor,sortDir);
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
