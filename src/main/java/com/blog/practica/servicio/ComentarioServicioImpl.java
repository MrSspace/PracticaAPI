package com.blog.practica.servicio;

import com.blog.practica.entidades.Comentario;
import com.blog.practica.excepciones.ResourceNotFoundException;
import com.blog.practica.repositorio.ComentarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServicioImpl implements IComentarioServicio{

    private final ComentarioRepositorio comentarioRepositorio;
    private final IPublicacionServicio publicacionServicio;

    public ComentarioServicioImpl(
            ComentarioRepositorio comentarioRepositorio,
            IPublicacionServicio publicacionServicio) {
        this.comentarioRepositorio = comentarioRepositorio;
        this.publicacionServicio = publicacionServicio;
    }

    @Override
    public void guardar(Comentario comentario, Long publicacionId) {
        comentario.setPublicacion(publicacionServicio.buscarPublicacionPorId(publicacionId));
        comentarioRepositorio.save(comentario);
    }

    @Override
    public void asignarComentarioALaPublicacion(Long publicacionId, Long comentarioId){
        Comentario comentario = buscarPorId(comentarioId);
        comentario.setPublicacion(publicacionServicio.buscarPublicacionPorId(publicacionId));
    }

    @Override
    public Comentario buscarPorId(Long id) {
        return comentarioRepositorio.findById(id)
                .orElseThrow( ()->new ResourceNotFoundException("Comentario","id", id));
    }

    @Override
    public List<Comentario> obtenerLosComentariosDeLaPublicacion(Long publicacionId) {
        return comentarioRepositorio.findByPublicacionId(publicacionId);
    }

    @Override
    public Comentario actualizarComentario(Comentario actualizacion,Long comentarioId){
        Comentario comentario = buscarPorId(comentarioId);
        comentario.setCuerpo(actualizacion.getCuerpo());
        comentario.setNombre(actualizacion.getNombre());
        comentario.setEmail(actualizacion.getEmail());
        return comentario;
    }

    @Override
    public void eliminarComentario(Long comentarioId){
        comentarioRepositorio.delete(buscarPorId(comentarioId));
    }

}
