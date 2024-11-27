package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Buscar autores por nombre y apellido
    List<Autor> findByNombreAndApellido(String nombre, String apellido);

    // Buscar autores vivos en un determinado año
    @Query("SELECT a FROM Autor a WHERE (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :año) AND a.fechaDeNacimiento <= :año")
    List<Autor> findAutoresVivosEnAnio(int año);
}

