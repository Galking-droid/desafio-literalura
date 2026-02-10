package com.aluracursos.desafio_literalura.repository;

import com.aluracursos.desafio_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Buscar autor por nombre para evitar duplicados
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    // Consulta para listar autores vivos en un determinado año
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :anio)")
    List<Autor> buscarAutoresVivosEnAnio(Integer anio);
}
