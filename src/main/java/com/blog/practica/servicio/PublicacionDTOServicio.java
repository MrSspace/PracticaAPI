package com.blog.practica.servicio;

import com.blog.practica.DTO.ColeccionDePublicacionesDTO;
import com.blog.practica.DTO.PublicacionDTO;

public interface PublicacionDTOServicio {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public void guardarPublicacion(PublicacionDTO publicacionDTO);

    public PublicacionDTO buscarPublicacionPorId(Long id);

    public PublicacionDTO buscarPublicacionPorTitulo(String titulo);

    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id);

    public void eliminarPublicacion(Long id);

    public ColeccionDePublicacionesDTO buscarTodasLasPublicaciones(
            int numeroDeLaPagina, int publicacionesPorPagina, String ordenarPor, String sortDir);

}
