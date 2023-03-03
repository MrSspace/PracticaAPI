package com.blog.practica.repositorio;

import com.blog.practica.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {

    @Query(value = "SELECT * FROM publicaciones WHERE publicaciones.titulo = :titulo",
    nativeQuery = true)
    public Publicacion findByTitulo(@Param("titulo") String titulo);

}
