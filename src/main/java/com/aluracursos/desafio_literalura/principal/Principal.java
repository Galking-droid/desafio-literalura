package com.aluracursos.desafio_literalura.principal;

import com.aluracursos.desafio_literalura.model.*;
import com.aluracursos.desafio_literalura.repository.AutorRepository;
import com.aluracursos.desafio_literalura.repository.LibroRepository;
import com.aluracursos.desafio_literalura.service.ConsumoAPI;
import com.aluracursos.desafio_literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                opcion = lectura.nextInt();
                lectura.nextLine();
            } catch (Exception e) {
                System.out.println("Opción no válida. Por favor, ingrese un número válido.");
                lectura.nextLine();
                continue;
            }

            switch (opcion) {
                case 1 -> buscarLibroWeb();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosEnAnio();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción no válida");
            }
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var nombreLibro = lectura.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, RespuestaAPI.class);
        if (!datos.resultados().isEmpty()) {
            return datos.resultados().get(0);
        } else {
            return null;
        }
    }

    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();

        if (datos != null) {
            // 1. Verificar si el libro ya está registrado
            Optional<Libro> libroExistente = libroRepository.findByTituloIgnoreCase(datos.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("El libro ya se encuentra registrado en la base de datos.");
                return;
            }

            // 2. Procesar el autor (Verificar si existe o crearlo)
            DatosAutor datosAutor = datos.autor().get(0);
            Autor autor = autorRepository.findByNombreIgnoreCase(datosAutor.nombre())
                    .orElseGet(() -> autorRepository.save(new Autor(datosAutor)));

            // 3. Crear y guardar el libro
            Libro libro = new Libro(datos);
            libro.setAutor(autor);
            libroRepository.save(libro);

            System.out.println(libro);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(a -> System.out.println(
                    "Autor: " + a.getNombre() +
                            " | Fecha de Nacimiento: " + a.getFechaDeNacimiento() +
                            " | Fecha de Fallecimiento: " + a.getFechaDeFallecimiento()
            ));
        }
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Ingrese el año para buscar autores vivos:");
        try {
            var anio = lectura.nextInt();
            lectura.nextLine();
            List<Autor> autoresVivos = autorRepository.buscarAutoresVivosEnAnio(anio);
            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anio);
            } else {
                autoresVivos.forEach(a -> System.out.println("Autor: " + a.getNombre()));
            }
        } catch (Exception e) {
            System.out.println("Año no válido.");
            lectura.nextLine();
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
            Ingrese el idioma para buscar los libros:
            es - español
            en - inglés
            fr - francés
            pt - portugués
            """);
        var idioma = lectura.nextLine();
        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma en la base de datos.");
        } else {
            long cantidad = librosPorIdioma.stream().count();
            System.out.println("Encontrados " + cantidad + " libros en " + idioma);
            librosPorIdioma.forEach(System.out::println);
        }
    }
}
