package com.blog.practica.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColeccionDePublicaciones {

    private List<Publicacion> contenido;
    private int numeroDeLaPagina;
    private int publicacionesPorPagina;
    private Long totalDeElementos;

    private int totalDePaginas;
    private boolean ultimaPagina;

}
