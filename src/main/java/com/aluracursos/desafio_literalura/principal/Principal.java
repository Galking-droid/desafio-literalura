package com.aluracursos.desafio_literalura.principal;

import com.aluracursos.desafio_literalura.model.DatosLibro;
import com.aluracursos.desafio_literalura.model.RespuestaAPI;
import com.aluracursos.desafio_literalura.service.ConsumoAPI;
import com.aluracursos.desafio_literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

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
            opcion = lectura.nextInt();
            lectura.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var nombreLibro = lectura.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, RespuestaAPI.class);
        if (!datos.resultados().isEmpty()) {
            return datos.resultados().get(0);
        } else {
            System.out.println("Libro no encontrado");
            return null;
        }
    }

    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos != null) {
            System.out.println("Datos del libro encontrado: " + datos);
            // Aquí más adelante guardaremos en la base de datos
        }
    }
}
