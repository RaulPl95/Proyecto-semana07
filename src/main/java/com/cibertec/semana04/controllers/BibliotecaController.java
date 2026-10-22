package com.cibertec.semana04.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.semana04.model.Libro;
import com.cibertec.semana04.service.BibliotecaService;

@Controller
@RequestMapping("/biblioteca")
public class BibliotecaController {

	@Autowired
	private BibliotecaService bibliotecaService;

	@GetMapping("/libros")
	public String listarLibros(Model model) {
		List<Libro> libros = bibliotecaService.listarLibros();
		model.addAttribute("libros", libros);
		return "libroList";
	}

	@GetMapping("/buscar")
	public String buscarLibros(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String autor, Model model) {

		List<Libro> libros;

		if (nombre != null && !nombre.isBlank()) {
			libros = bibliotecaService.buscarPorNombre(nombre);
		} else if (autor != null && !autor.isBlank()) {
			libros = bibliotecaService.buscarPorAutor(autor);
		} else {
			libros = bibliotecaService.listarLibros();
		}

		model.addAttribute("libros", libros);
		model.addAttribute("nombre", nombre);
		model.addAttribute("autor", autor);

		return "libroList";
	}

	@GetMapping("/nuevo")
	public String nuevoLibro(Model model) {
		model.addAttribute("libro", new Libro());
		model.addAttribute("editoriales", bibliotecaService.listarEditorial());
		return "libroForm";
	}

	@PostMapping("/guardar")
	public String guardarLibro(@ModelAttribute Libro libro, RedirectAttributes redirectAttributes) {

		bibliotecaService.guardarLibro(libro);

		redirectAttributes.addFlashAttribute("mensaje", "Libro guardado correctamente");

		return "redirect:/biblioteca/libros";
	}

	@GetMapping("/editar/{id}")
	public String editarLibro(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

		Libro libro = bibliotecaService.buscarLibroPorId(id).orElse(null);

		if (libro == null) {
			redirectAttributes.addFlashAttribute("error", "No se encontró el libro");
			return "redirect:/biblioteca/libros";
		}

		model.addAttribute("libro", libro);
		model.addAttribute("editoriales", bibliotecaService.listarEditorial());

		return "libroForm";
	}

	@PostMapping("/eliminar/{id}")
	public String eliminarLibro(@PathVariable Long id, RedirectAttributes redirectAttributes) {

		bibliotecaService.eliminarLibro(id);

		redirectAttributes.addFlashAttribute("mensaje", "Libro eliminado correctamente");

		return "redirect:/biblioteca/libros";
	}
}
