package com.blog.practica.servicio;

import com.blog.practica.DTO.ComentarioDTO;

import java.util.List;

public interface IComentarioDTOServicio {

    public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO);

    public List<ComentarioDTO> obtenerLosComentariosDeLaPublicacion(Long publicacionId);

    public ComentarioDTO buscarComentarioPorId(Long comentarioId);

    public ComentarioDTO actualizarComentario(ComentarioDTO actualizacionDTO,Long comentarioId);

    public void eliminarComentario(Long comentarioId);

}
