package com.blog.practica.Mapeadores;

import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.entidades.Publicacion;

public class PublicacionMapeador {

    public PublicacionMapeador(){}

    public PublicacionDTO convertirADTO(Publicacion publicacion){
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setId( publicacion.getId() );
        publicacionDTO.setTitulo( publicacion.getTitulo() );
        publicacionDTO.setDescripcion( publicacion.getDescripcion() );
        publicacionDTO.setContenido( publicacion.getContenido() );
        return publicacionDTO;
    }

    public Publicacion convertirAEntidad(PublicacionDTO publicacionDTO){
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo( publicacionDTO.getTitulo() );
        publicacion.setDescripcion( publicacionDTO.getDescripcion() );
        publicacion.setContenido( publicacionDTO.getContenido() );
        return publicacion;
    }

}
