package com.blog.practica.Mapeadores;

import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.entidades.Publicacion;

public interface IPublicacionMapeador {

    public PublicacionDTO convertirADTO(Publicacion publicacion);

    public Publicacion convertirAEntidad(PublicacionDTO publicacionDTO);

}
