package com.blog.practica.entidades;

import lombok.Data;

import java.util.List;

@Data
public class ColeccionDePublicaciones {

    private List<Publicacion> contenido;
    private int numeroDeLaPagina;
    private int publicacionesPorPagina;
    private Long totalDeElementos;

    private int totalDePaginas;
    private boolean ultimaPagina;

}
