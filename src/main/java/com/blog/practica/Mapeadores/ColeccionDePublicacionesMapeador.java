package com.blog.practica.Mapeadores;

import com.blog.practica.DTO.ColeccionDePublicacionesDTO;
import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.entidades.Publicacion;
import com.blog.practica.entidades.ColeccionDePublicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColeccionDePublicacionesMapeador implements IColeccionDePublicacionesMapeador{

    @Autowired
    PublicacionMapeador mapeador;

    public ColeccionDePublicacionesMapeador(){}

    @Override
    public ColeccionDePublicacionesDTO convertirADTO(ColeccionDePublicaciones coleccionDePublicaciones){
        ColeccionDePublicacionesDTO coleccionDTO = new ColeccionDePublicacionesDTO();
        coleccionDTO.setContenido(convertirPublicacionesADTO(coleccionDePublicaciones.getContenido()));
        coleccionDTO.setNumeroDeLaPagina(coleccionDePublicaciones.getNumeroDeLaPagina());
        coleccionDTO.setPublicacionesPorPagina(coleccionDePublicaciones.getPublicacionesPorPagina());
        coleccionDTO.setTotalDeElementos(coleccionDePublicaciones.getTotalDeElementos());
        coleccionDTO.setTotalDePaginas(coleccionDePublicaciones.getTotalDePaginas());
        coleccionDTO.setUltimaPagina(coleccionDePublicaciones.isUltimaPagina());
        return coleccionDTO;
    }

    @Override
    public ColeccionDePublicaciones convertirAEntidad(ColeccionDePublicacionesDTO coleccionDePublicacionesDTO){
        ColeccionDePublicaciones coleccion = new ColeccionDePublicaciones();
        coleccion.setContenido(convertirPublicacionesAEntidad(coleccionDePublicacionesDTO.getContenido()));
        coleccion.setNumeroDeLaPagina(coleccionDePublicacionesDTO.getNumeroDeLaPagina());
        coleccion.setPublicacionesPorPagina(coleccionDePublicacionesDTO.getPublicacionesPorPagina());
        coleccion.setTotalDeElementos(coleccionDePublicacionesDTO.getTotalDeElementos());
        coleccion.setTotalDePaginas(coleccionDePublicacionesDTO.getTotalDePaginas());
        coleccion.setUltimaPagina(coleccionDePublicacionesDTO.isUltimaPagina());
        return coleccion;
    }

    private List<PublicacionDTO> convertirPublicacionesADTO(List<Publicacion> publicaciones){
        List<PublicacionDTO> publicacionesDTO = publicaciones.stream()
                .map(publicacion -> mapeador.convertirADTO(publicacion))
                .collect(Collectors.toList());
        return publicacionesDTO;
    }

    private List<Publicacion> convertirPublicacionesAEntidad(List<PublicacionDTO> publicacionesDTO){
        List<Publicacion> publicaciones = publicacionesDTO.stream()
                .map(publicacionDTO -> mapeador.convertirAEntidad(publicacionDTO))
                .collect(Collectors.toList());
        return publicaciones;
    }

}
