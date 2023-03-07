package com.blog.practica.repositorio;

import com.blog.practica.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario,Long> {

    public List<Comentario> findByPublicacionId(Long publicacionId);

}
