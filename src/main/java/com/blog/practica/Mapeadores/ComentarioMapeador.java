package com.blog.practica.Mapeadores;

import com.blog.practica.DTO.ComentarioDTO;
import com.blog.practica.entidades.Comentario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapeador implements IComentarioMapeador{

    public ComentarioMapeador(){}

    @Override
    public ComentarioDTO convertirADTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setCuerpo(comentario.getCuerpo());
        comentarioDTO.setEmail(comentario.getEmail());
        comentarioDTO.setNombre(comentario.getNombre());
        return comentarioDTO;
    }

    @Override
    public Comentario convertirAEntidad(ComentarioDTO comentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setNombre(comentarioDTO.getNombre());
        return comentario;
    }

}
