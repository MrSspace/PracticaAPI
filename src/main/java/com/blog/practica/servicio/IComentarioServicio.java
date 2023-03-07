package com.blog.practica.servicio;

import com.blog.practica.entidades.Comentario;

import java.util.List;

public interface IComentarioServicio {

    public void guardar(Comentario comentario, Long publicacionId);

    public void asignarComentarioALaPublicacion(Long publicacionId, Long comentarioId);

    public Comentario buscarPorId(Long id);

    public List<Comentario> obtenerLosComentariosDeLaPublicacion(Long publicacionId);

    public Comentario actualizarComentario(Comentario actualizacion,Long comentarioId);

    public void eliminarComentario(Long comentarioId);

}
