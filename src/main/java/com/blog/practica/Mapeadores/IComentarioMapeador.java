package com.blog.practica.Mapeadores;

import com.blog.practica.DTO.ComentarioDTO;
import com.blog.practica.entidades.Comentario;

public interface IComentarioMapeador {

    public ComentarioDTO convertirADTO(Comentario comentario);

    public Comentario convertirAEntidad(ComentarioDTO comentarioDTO);

}
