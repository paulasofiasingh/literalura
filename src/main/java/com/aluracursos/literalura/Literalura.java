package com.aluracursos.literalura;

import com.aluracursos.literalura.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Literalura implements CommandLineRunner {

	@Autowired
	private LibroService libroService;

	public static void main(String[] args) {
		SpringApplication.run(Literalura.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		String menu = """
				LiterAlura
				**************************************
				Elija la opción a través de su número:
				1- Buscar libro por título
				2- Listar libros registrados
				3- Listar autores registrados
				4- Listar vivos en un determinado año
				5- Listar libros por idioma
				9- Salir
				Opción: 
				""";

		while (opcion != 9) {
			System.out.println(menu);
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
				case 1:
					System.out.print("Ingrese el título del libro: ");
					String titulo = scanner.nextLine();
					String resultadoBusqueda = libroService.buscarYRegistrarLibro(titulo);
					System.out.println(resultadoBusqueda);
					break;
				case 2:
					libroService.listarLibrosRegistrados();
					break;
				case 3:
					libroService.listarAutoresRegistrados();
					break;
				case 4:
					System.out.print("Ingrese el año: ");
					int año = scanner.nextInt();
					libroService.listarAutoresVivosEnAnio(año);
					break;
				case 5:
					System.out.print("Ingrese el idioma: ");
					String idioma = scanner.nextLine();
					libroService.listarLibrosPorIdioma(idioma);
					break;
				case 9:
					System.out.println("Saliendo...");
					System.exit(0);
					break;
				default:
					System.out.println("Opción no válida.");
					break;
			}
		}
	}
}




