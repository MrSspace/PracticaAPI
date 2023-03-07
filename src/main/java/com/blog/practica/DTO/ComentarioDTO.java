package com.blog.practica.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {

    @Setter @Getter
    private Long id;

    @Setter @Getter
    private String cuerpo;

    @Setter @Getter
    private String email;

    @Setter @Getter
    private String nombre;

}
