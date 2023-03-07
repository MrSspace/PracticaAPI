package com.blog.practica.servicio;

import com.blog.practica.DTO.ComentarioDTO;
import com.blog.practica.Mapeadores.ComentarioMapeador;
import com.blog.practica.entidades.Comentario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioDTOServicioImpl implements IComentarioDTOServicio{

    private final IComentarioServicio comentarioServicio;
    private final ComentarioMapeador mapeadorComentario;

    public ComentarioDTOServicioImpl(
            IComentarioServicio comentarioServicio,
            ComentarioMapeador mapeadorComentario) {
        this.comentarioServicio = comentarioServicio;
        this.mapeadorComentario = mapeadorComentario;
    }

    @Override
    public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario = mapeadorComentario.convertirAEntidad(comentarioDTO);
        comentarioServicio.guardar(comentario,publicacionId);
        comentarioServicio.asignarComentarioALaPublicacion(publicacionId,comentario.getId());
        return mapeadorComentario.convertirADTO(comentario);
    }

    @Override
    public List<ComentarioDTO> obtenerLosComentariosDeLaPublicacion(Long publicacionId) {
        return comentarioServicio.obtenerLosComentariosDeLaPublicacion(publicacionId)
                .stream()
                .map(comentario -> mapeadorComentario.convertirADTO(comentario))
                .collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO buscarComentarioPorId(Long comentarioId){
        return mapeadorComentario.convertirADTO(comentarioServicio.buscarPorId(comentarioId));
    }

    @Override
    public ComentarioDTO actualizarComentario(ComentarioDTO actualizacionDTO,Long comentarioId){
        Comentario comentarioActualizado = comentarioServicio.actualizarComentario(
                mapeadorComentario.convertirAEntidad(actualizacionDTO),comentarioId);
        return mapeadorComentario.convertirADTO(comentarioActualizado);
    }

    @Override
    public void eliminarComentario(Long comentarioId){
        comentarioServicio.eliminarComentario(comentarioId);
    }

}
