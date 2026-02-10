package com.aluracursos.desafio_literalura.repository;

import com.aluracursos.desafio_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Buscar libro por título para evitar duplicados
    Optional<Libro> findByTituloIgnoreCase(String titulo);

    // Listar libros por idioma
    List<Libro> findByIdioma(String idioma);
}
