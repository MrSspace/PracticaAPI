package com.blog.practica.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class DetallesDeErrorDTO {

    @Getter @Setter
    private Date marcaDeTiempo;

    @Getter @Setter
    private String mensaje;

    @Getter @Setter
    private String detalles;

}
