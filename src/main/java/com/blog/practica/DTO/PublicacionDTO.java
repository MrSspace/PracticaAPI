package com.blog.practica.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PublicacionDTO {

    @Setter @Getter
    private Long id;

    @Setter @Getter
    private String titulo;

    @Setter @Getter
    private String descripcion;

    @Setter @Getter
    private String contenido;
}
