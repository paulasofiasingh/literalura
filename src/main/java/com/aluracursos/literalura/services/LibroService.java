package com.aluracursos.literalura.services;

import com.aluracursos.literalura.models.Autor;
import com.aluracursos.literalura.models.Libro;
import com.aluracursos.literalura.models.GutendexResponse;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public String buscarYRegistrarLibro(String titulo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gutendex.com/books?search=" + titulo;
        try {
            // Realiza la consulta al API de Gutendex
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

            // Verificar si la respuesta y los resultados no son null
            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                GutendexResponse.Book book = response.getResults().get(0);

                // Mostrar los detalles del libro encontrado
                System.out.println("Libro encontrado:");
                System.out.println("Título: " + book.getTitle());
                System.out.println("Autor: " + book.getAuthorFirstName() + " " + book.getAuthorLastName());
                System.out.println("Idioma: " + book.getLanguage());
                System.out.println("Descargas: " + book.getDownloads());

                // Verificar si el libro ya está registrado
                if (!libroRepository.findByTitulo(book.getTitle()).isEmpty()) {
                    return "El libro ya está registrado.";
                }

                // Lógica de registro del libro
                // Verificamos el autor y lo registramos si es necesario, luego registramos el libro
                Autor autor = registrarAutor(book);
                if (autor != null) {
                    Libro libro = new Libro();
                    libro.setTitulo(book.getTitle());
                    libro.setIdioma(book.getLanguage());
                    libro.setDescargas(book.getDownloads());
                    libro.setAutor(autor);
                    libroRepository.save(libro);
                    return "Libro registrado exitosamente.";
                } else {
                    return "Error al registrar el libro.";
                }
            } else {
                return "El libro no fue encontrado en la API.";
            }
        } catch (Exception e) {
            return "Error al buscar el libro: " + e.getMessage();
        }
    }

    private Autor registrarAutor(GutendexResponse.Book book) {
        if (book.getAuthorFirstName() != null && book.getAuthorLastName() != null) {
            // Buscar si el autor ya existe en la base de datos
            List<Autor> autores = autorRepository.findByNombreAndApellido(book.getAuthorFirstName(), book.getAuthorLastName());
            if (autores.isEmpty()) {
                Autor autor = new Autor();
                autor.setNombre(book.getAuthorFirstName());
                autor.setApellido(book.getAuthorLastName());

                // Obtener año de nacimiento y fallecimiento desde la API
                Integer yearOfBirth = book.getAuthorBirthYear();
                Integer yearOfDeath = book.getAuthorDeathYear();

                // Asignar los valores de nacimiento y fallecimiento
                autor.setFechaDeNacimiento(yearOfBirth);
                autor.setFechaDeFallecimiento(yearOfDeath);

                // Guardar el autor en la base de datos
                return autorRepository.save(autor);
            } else {
                // Retornar el autor existente si ya está registrado
                return autores.get(0);
            }
        }
        return null;
    }


    public void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : libros) {
                System.out.println("Título: " + libro.getTitulo() +
                        " | Autor: " + libro.getAutor() +
                        " | Idioma: " + libro.getIdioma() +
                        " | Número de descargas: " + libro.getDescargas());
            }
        }
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            for (Autor autor : autores) {
                System.out.print("Autor: " + autor.getNombre() + " " + autor.getApellido() +
                        ", Fecha de nacimiento: " + autor.getFechaDeNacimiento() +
                        ", Fecha de fallecimiento: " + autor.getFechaDeFallecimiento() +
                        ", Libros: ");

                // Recorremos la lista de libros y mostramos los títulos
                if (autor.getLibros() != null && !autor.getLibros().isEmpty()) {
                    for (Libro libro : autor.getLibros()) {
                        System.out.print(libro.getTitulo() + " ");  // Imprime el título del libro
                    }
                } else {
                    System.out.print("No hay libros disponibles.");
                }

                System.out.println();  // Nueva línea después de imprimir los libros
            }
        }
    }


    // Este método ahora obtiene autores vivos en un año dado
    public void listarAutoresVivosEnAnio(int año) {
        List<Autor> autoresVivos = autorRepository.findAutoresVivosEnAnio(año);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + año);
        } else {
            for (Autor autor : autoresVivos) {
                System.out.println("Autor vivo: " + autor.getNombre() + " " + autor.getApellido());
            }
        }
    }

    public void listarLibrosPorIdioma(String idioma) {
        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros registrados en el idioma " + idioma);
        } else {
            for (Libro libro : librosPorIdioma) {
                System.out.println("Título: " + libro.getTitulo() + " | Autor: " + libro.getAutor().getNombre() + " " + libro.getAutor().getApellido());
            }
        }
    }
}


