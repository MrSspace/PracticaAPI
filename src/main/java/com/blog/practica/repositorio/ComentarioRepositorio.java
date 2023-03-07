package com.blog.practica.repositorio;

import com.blog.practica.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario,Long> {

    @Query(value = "SELECT * FROM comentarios WHERE comentarios.publicacion_id = :publicacionId",
            nativeQuery = true)
    public List<Comentario> findByPublicacionId(@Param("publicacionId")Long publicacionId);

}
