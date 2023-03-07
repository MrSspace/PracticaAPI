package com.blog.practica.DTO;

import com.blog.practica.utilerias.AppConstantes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PublicacionDTO {

    @Setter @Getter
    private Long id;

    @NotEmpty(message = AppConstantes.CAMPO_NO_DEBE_SER_VACIO)
    @Size(min = 5, message = AppConstantes.LONGITUD_MINIMA_DE_5)
    @Setter @Getter
    private String titulo;

    @NotEmpty(message = AppConstantes.CAMPO_NO_DEBE_SER_VACIO)
    @Size(min = 5, message = AppConstantes.LONGITUD_MINIMA_DE_5)
    @Setter @Getter
    private String descripcion;

    @NotEmpty(message = AppConstantes.CAMPO_NO_DEBE_SER_VACIO)
    @Size(min = 10, message = AppConstantes.LONGITUD_MINIMA_DE_10)
    @Setter @Getter
    private String contenido;

}
