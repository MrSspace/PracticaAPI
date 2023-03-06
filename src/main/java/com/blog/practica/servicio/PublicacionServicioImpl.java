package com.blog.practica.servicio;

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

    @Override
    public void guardarPublicacion(Publicacion publicacion) {
        publicacionRepositorio.save(publicacion);
    }

    @Override
    public Publicacion buscarPublicacionPorTitulo(String titulo) {
        return publicacionRepositorio.findByTitulo(titulo);
    }

    @Override
    public Publicacion buscarPublicacionPorId(Long id) {
        return publicacionRepositorio.findById(id)
                .orElseThrow( ()->new ResourceNotFoundException("Publicacion","id", id) );
    }

    @Override
    public Publicacion actualizarPublicacion(Publicacion actualizacion, Long id) {
        Publicacion publicacion = buscarPublicacionPorId(id);
        publicacion.setTitulo(actualizacion.getTitulo());
        publicacion.setDescripcion(actualizacion.getDescripcion());
        publicacion.setContenido(actualizacion.getContenido());
        publicacionRepositorio.save(publicacion);
        return publicacion;
    }

    public  void eliminarPublicacion(Long id){
        publicacionRepositorio.delete(buscarPublicacionPorId(id));
    }

    public ColeccionDePublicaciones buscarTodasLasPublicaciones(
            int numeroDeLaPagina, int publicacionesPorPagina, String ordenarPor, String sortDir){

        Page<Publicacion> contenidoPaginado =
                paginarPublicaciones(numeroDeLaPagina,publicacionesPorPagina,ordenarPor,sortDir);

        List<Publicacion> contenido = contenidoPaginado.getContent();

        ColeccionDePublicaciones coleccionPublicaciones = new ColeccionDePublicaciones();
        coleccionPublicaciones.setContenido(contenido);
        coleccionPublicaciones.setNumeroDeLaPagina(contenidoPaginado.getNumber());
        coleccionPublicaciones.setPublicacionesPorPagina(contenidoPaginado.getSize());
        coleccionPublicaciones.setTotalDeElementos(contenidoPaginado.getTotalElements());
        coleccionPublicaciones.setTotalDePaginas(contenidoPaginado.getTotalPages());
        coleccionPublicaciones.setUltimaPagina(contenidoPaginado.isLast());

        return coleccionPublicaciones;
    }

    private Page<Publicacion> paginarPublicaciones(
            int numeroDeLaPagina, int publicacionesPorPagina, String ordenarPor, String sortDir){
        Sort sort = elegirDirDeOrdenamiento(ordenarPor, sortDir);
        Pageable pageable = PageRequest.of(numeroDeLaPagina,publicacionesPorPagina,sort);
        return publicacionRepositorio.findAll(pageable);
    }

    private Sort elegirDirDeOrdenamiento(String ordenarPor, String sortDir){
        return sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
    }

}
