package com.blog.practica.controlador;

import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.servicio.PublicacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/publicaciones"})
public class PublicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(
            @RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<PublicacionDTO>(
                publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

}