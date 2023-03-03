package com.blog.practica.servicio;

import com.blog.practica.DTO.PublicacionDTO;
import com.blog.practica.Mapeadores.PublicacionMapeador;
import com.blog.practica.entidades.Publicacion;
import com.blog.practica.repositorio.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServicioImpl implements PublicacionServicio{

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        guardarPublicacion(publicacionDTO);
        return publicacionDTO;
    }

    @Override
    public void guardarPublicacion(PublicacionDTO publicacionDTO) {
        PublicacionMapeador mapeador = new PublicacionMapeador();
        Publicacion publicacion = mapeador.convertirAEntidad(publicacionDTO);
        publicacionRepositorio.save(publicacion);
    }

    @Override
    public PublicacionDTO buscarPublicacionPorId(Long id) {
        Publicacion publicacion = publicacionRepositorio.getById(id);
        PublicacionMapeador mapeador = new PublicacionMapeador();
        PublicacionDTO publicacionDTO = mapeador.convertirADTO(publicacion);
        return publicacionDTO;
    }

}
