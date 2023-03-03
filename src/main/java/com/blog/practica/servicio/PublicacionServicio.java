package com.blog.practica.servicio;

import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.entidades.Publicacion;

public interface PublicacionServicio {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    public void guardarPublicacion(PublicacionDTO publicacionDTO);
    public PublicacionDTO buscarPublicacionPorId(Long id);

}
