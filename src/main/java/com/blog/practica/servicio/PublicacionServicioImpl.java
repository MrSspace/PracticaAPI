package com.blog.practica.servicio;

import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.Mapeadores.PublicacionMapeador;
import com.blog.practica.entidades.Publicacion;
import com.blog.practica.excepciones.ResourceNotFoundException;
import com.blog.practica.repositorio.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServicioImpl implements PublicacionServicio{

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    private PublicacionMapeador mapeador = new PublicacionMapeador();

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        guardarPublicacion(publicacionDTO);
        return buscarPublicacionPorTitulo(publicacionDTO.getTitulo());
    }

    @Override
    public void guardarPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = mapeador.convertirAEntidad(publicacionDTO);
        publicacionRepositorio.save(publicacion);
    }

    @Override
    public PublicacionDTO buscarPublicacionPorTitulo(String titulo) {
        Publicacion publicacion = publicacionRepositorio.findByTitulo(titulo);
        return mapeador.convertirADTO(publicacion);
    }

    public List<PublicacionDTO> buscarTodasLasPublicaciones(int numeroDeLaPagina, int publicacionesPorPagina){
        Page<Publicacion> publicacionesPaginadas = paginarPublicaciones(
                numeroDeLaPagina, publicacionesPorPagina);
        List<Publicacion> publicaciones = publicacionesPaginadas.getContent();
        List<PublicacionDTO> publicacionesDTO = convertirPublicacionesADTO(publicaciones);
        return publicacionesDTO;
    }

    @Override
    public PublicacionDTO buscarPublicacionPorId(Long id) {
        Publicacion publicacion = encontrarPublicacion(id);
        return mapeador.convertirADTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
        Publicacion publicacion = encontrarPublicacion(id);
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacionRepositorio.save(publicacion);
        return mapeador.convertirADTO(publicacion);
    }

    public  void eliminarPublicacion(Long id){
        Publicacion publicacion = encontrarPublicacion(id);
        publicacionRepositorio.delete(publicacion);
    }

    private Publicacion encontrarPublicacion(Long id){
        return publicacionRepositorio.findById(id)
                .orElseThrow( ()->new ResourceNotFoundException("Publicacion","id", id) );
    }

    private Page<Publicacion> paginarPublicaciones(int numeroDeLaPagina, int publicacionesPorPagina){
        Pageable pageable = PageRequest.of(numeroDeLaPagina,publicacionesPorPagina);
        Page<Publicacion> publicacionesPaginadas = publicacionRepositorio.findAll(pageable);
        return publicacionesPaginadas;
    }

    private List<PublicacionDTO> convertirPublicacionesADTO(List<Publicacion> publicaciones){
        List<PublicacionDTO> publicacionesDTO = publicaciones.stream()
                .map(publicacion -> mapeador.convertirADTO(publicacion))
                .collect(Collectors.toList());
        return publicacionesDTO;
    }

}
