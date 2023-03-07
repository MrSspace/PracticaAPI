package com.blog.practica.servicio;

import com.blog.practica.entidades.ColeccionDePublicaciones;
import com.blog.practica.entidades.Publicacion;

public interface IPublicacionServicio {

    public void guardarPublicacion(Publicacion publicacion);

    public Publicacion buscarPublicacionPorId(Long id);

    public Publicacion buscarPublicacionPorTitulo(String titulo);

    public Publicacion actualizarPublicacion(Publicacion actualizacion, Long id);

    public void eliminarPublicacion(Long id);

    public ColeccionDePublicaciones buscarTodasLasPublicaciones(
            int numeroDeLaPagina, int publicacionesPorPagina, String ordenarPor, String sortDir);

}
