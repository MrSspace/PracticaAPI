package com.blog.practica.controlador;

import com.blog.practica.DTO.ColeccionDePublicacionesDTO;
import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.servicio.IPublicacionDTOServicio;
import com.blog.practica.utilerias.AppConstantes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/v1/publicaciones"})
public class PublicacionControlador implements IPublicacionControlador {

    private final IPublicacionDTOServicio publicacionDTOServicio;

    public PublicacionControlador(IPublicacionDTOServicio publicacionDTOServicio) {
        this.publicacionDTOServicio = publicacionDTOServicio;
    }

    @Override
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
        return publicacionDTOServicio.buscarTodasLasPublicaciones(
                numeroDeLaPagina,publicacionesPorPagina,ordenarPor,sortDir);
    }

    @Override
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorTitulo(
            @PathVariable(name = "titulo") String titulo){
        return ResponseEntity.ok(publicacionDTOServicio.buscarPublicacionPorTitulo(titulo));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(
            @PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .header("Accion","BuscandoPorId")
                .body(publicacionDTOServicio.buscarPublicacionPorId(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<PublicacionDTO> crearPublicacion(
            @Valid
            @RequestBody
            PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(
                publicacionDTOServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(
            @Valid
            @RequestBody
            PublicacionDTO publicacionDTO, @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(
                publicacionDTOServicio.actualizarPublicacion(publicacionDTO,id),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id){
        publicacionDTOServicio.eliminarPublicacion(id);
        return new ResponseEntity<>("Se ha eliminado la publicacion", HttpStatus.OK);
    }

}
