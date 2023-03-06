package com.blog.practica.Mapeadores;

import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.entidades.Publicacion;
import org.springframework.stereotype.Component;

@Component
public class PublicacionMapeador implements IPublicacionMapeador{

    public PublicacionMapeador(){}

    @Override
    public PublicacionDTO convertirADTO(Publicacion publicacion){
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setId( publicacion.getId() );
        publicacionDTO.setTitulo( publicacion.getTitulo() );
        publicacionDTO.setDescripcion( publicacion.getDescripcion() );
        publicacionDTO.setContenido( publicacion.getContenido() );
        return publicacionDTO;
    }

    @Override
    public Publicacion convertirAEntidad(PublicacionDTO publicacionDTO){
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo( publicacionDTO.getTitulo() );
        publicacion.setDescripcion( publicacionDTO.getDescripcion() );
        publicacion.setContenido( publicacionDTO.getContenido() );
        return publicacion;
    }

}
