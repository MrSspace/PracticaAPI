package com.blog.practica.servicio;

import com.blog.practica.DTO.ColeccionDePublicacionesDTO;
import com.blog.practica.DTO.PublicacionDTO;

import java.util.List;

public interface PublicacionServicio {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public void guardarPublicacion(PublicacionDTO publicacionDTO);

    public PublicacionDTO buscarPublicacionPorTitulo(String titulo);

    public ColeccionDePublicacionesDTO buscarTodasLasPublicaciones(
            int numeroDeLaPagina, int publicacionesPorPagina, String ordenarPor, String sortDir);

    public PublicacionDTO buscarPublicacionPorId(Long id);

    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id);

    public void eliminarPublicacion(Long id);

}
