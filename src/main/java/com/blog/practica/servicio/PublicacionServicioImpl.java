package com.blog.practica.servicio;

import com.blog.practica.DTO.ColeccionDePublicacionesDTO;
import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.Mapeadores.ColeccionDePublicacionesMapeador;
import com.blog.practica.Mapeadores.PublicacionMapeador;
import com.blog.practica.entidades.ColeccionDePublicaciones;
import com.blog.practica.entidades.Publicacion;
import com.blog.practica.excepciones.ResourceNotFoundException;
import com.blog.practica.repositorio.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionServicioImpl implements PublicacionServicio{

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    private PublicacionMapeador mapeador = new PublicacionMapeador();

    private ColeccionDePublicacionesMapeador mapeadorColeccion = new ColeccionDePublicacionesMapeador();

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

    public ColeccionDePublicacionesDTO buscarTodasLasPublicaciones(
            int numeroDeLaPagina, int publicacionesPorPagina, String ordenarPor, String sortDir){
        ColeccionDePublicaciones coleccionDePublicaciones = crearColeccionDePublicaciones(
                numeroDeLaPagina,publicacionesPorPagina,ordenarPor,sortDir);
        ColeccionDePublicacionesDTO publicacionesDTO;
        publicacionesDTO = crearColeccionDePublicacionesDTO(coleccionDePublicaciones);
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

    private ColeccionDePublicacionesDTO crearColeccionDePublicacionesDTO(
            ColeccionDePublicaciones coleccionDePublicaciones){
        ColeccionDePublicacionesDTO coleccionPublicacionesDTO;
        coleccionPublicacionesDTO= mapeadorColeccion.convertirADTO(coleccionDePublicaciones);
        return coleccionPublicacionesDTO;
    }

    private ColeccionDePublicaciones crearColeccionDePublicaciones(
            int numeroDeLaPagina, int publicacionesPorPagina, String ordenarPor, String sortDir){
        Page<Publicacion> paginadas = paginarPublicaciones(
                numeroDeLaPagina,publicacionesPorPagina,ordenarPor,sortDir);
        List<Publicacion> contenido = listarPublicaciones(paginadas);
        ColeccionDePublicaciones coleccionPublicaciones = new ColeccionDePublicaciones();
        coleccionPublicaciones.setContenido(contenido);
        coleccionPublicaciones.setNumeroDeLaPagina(paginadas.getNumber());
        coleccionPublicaciones.setPublicacionesPorPagina(paginadas.getSize());
        coleccionPublicaciones.setTotalDeElementos(paginadas.getTotalElements());
        coleccionPublicaciones.setTotalDePaginas(paginadas.getTotalPages());
        coleccionPublicaciones.setUltimaPagina(paginadas.isLast());
        return coleccionPublicaciones;
    }

    private List<Publicacion> listarPublicaciones(Page<Publicacion> publicacionesPaginadas) {
        List<Publicacion> publicaciones = publicacionesPaginadas.getContent();
        return publicaciones;
    }

    private Page<Publicacion> paginarPublicaciones(
            int numeroDeLaPagina, int publicacionesPorPagina, String ordenarPor, String sortDir){
        Sort sort = elegirDirDeOrdenamiento(ordenarPor, sortDir);
        Pageable pageable = PageRequest.of(numeroDeLaPagina,publicacionesPorPagina,sort);
        Page<Publicacion> publicacionesPaginadas = publicacionRepositorio.findAll(pageable);
        return publicacionesPaginadas;
    }

    private Sort elegirDirDeOrdenamiento(String ordenarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        return sort;
    }

}
