package com.blog.practica.servicio;

import com.blog.practica.DTO.PublicacionDTO;

import java.util.List;

public interface PublicacionServicio {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public void guardarPublicacion(PublicacionDTO publicacionDTO);

    public PublicacionDTO buscarPublicacionPorTitulo(String titulo);

    public List<PublicacionDTO> buscarTodasLasPublicaciones(int numeroDeLaPagina, int publicacionesPorPagina);

    public PublicacionDTO buscarPublicacionPorId(Long id);

    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id);

    public void eliminarPublicacion(Long id);

}
