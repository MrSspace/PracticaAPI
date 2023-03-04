package com.blog.practica.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ColeccionDePublicacionesDTO {

    @Getter @Setter
    private List<PublicacionDTO> contenido;

    @Getter @Setter
    private int numeroDeLaPagina;

    @Getter @Setter
    private int publicacionesPorPagina;

    @Getter @Setter
    private Long totalDeElementos;

    @Getter @Setter
    private int totalDePaginas;
    @Getter @Setter
    private boolean ultimaPagina;

}
