package com.blog.practica.servicio;

import com.blog.practica.DTO.ColeccionDePublicacionesDTO;
import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.Mapeadores.IColeccionDePublicacionesMapeador;
import com.blog.practica.Mapeadores.IPublicacionMapeador;
import com.blog.practica.entidades.Publicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionDTOServicioImpl implements PublicacionDTOServicio{

    @Autowired
    private PublicacionServicio publicacionServicio;

    @Autowired
    private IPublicacionMapeador publicacionMapeador;

    @Autowired
    private IColeccionDePublicacionesMapeador mapeadorColeccion;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        guardarPublicacion(publicacionDTO);
        return buscarPublicacionPorTitulo(publicacionDTO.getTitulo());
    }

    @Override
    public void guardarPublicacion(PublicacionDTO publicacionDTO) {
        publicacionServicio.guardarPublicacion(
                publicacionMapeador.convertirAEntidad(publicacionDTO));
    }

    @Override
    public PublicacionDTO buscarPublicacionPorId(Long id) {
        return publicacionMapeador.convertirADTO(
                publicacionServicio.buscarPublicacionPorId(id));
    }

    @Override
    public PublicacionDTO buscarPublicacionPorTitulo(String titulo) {
        return publicacionMapeador.convertirADTO(
                publicacionServicio.buscarPublicacionPorTitulo(titulo));
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
        Publicacion publicacion = publicacionMapeador.convertirAEntidad(publicacionDTO);
        return publicacionMapeador.convertirADTO(
                publicacionServicio.actualizarPublicacion(publicacion,id));
    }

    @Override
    public void eliminarPublicacion(Long id) {
        publicacionServicio.eliminarPublicacion(id);
    }

    @Override
    public ColeccionDePublicacionesDTO buscarTodasLasPublicaciones(
            int numeroDeLaPagina, int publicacionesPorPagina, String ordenarPor, String sortDir) {

        return mapeadorColeccion.convertirADTO(
                publicacionServicio.buscarTodasLasPublicaciones(
                        numeroDeLaPagina,publicacionesPorPagina,ordenarPor,sortDir));
    }

}
