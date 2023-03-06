package com.blog.practica.Mapeadores;

import com.blog.practica.DTO.ColeccionDePublicacionesDTO;
import com.blog.practica.entidades.ColeccionDePublicaciones;

public interface IColeccionDePublicacionesMapeador {

    public ColeccionDePublicacionesDTO convertirADTO(ColeccionDePublicaciones coleccionDePublicaciones);

    public ColeccionDePublicaciones convertirAEntidad(ColeccionDePublicacionesDTO coleccionDePublicacionesDTO);

}
