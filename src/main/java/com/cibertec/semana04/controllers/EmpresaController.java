package com.cibertec.semana04.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.semana04.model.Empresa;
import com.cibertec.semana04.service.EmpresaService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@GetMapping("/empresas")
	public String getAllEmpresas(Model model) {

		List<Empresa> listEmpresas = empresaService.getAllEmpresas();

		model.addAttribute("empresas", listEmpresas);

		return "empresaList";
	}

	@GetMapping("/registrar")
	public String mostrarFormularioRegistro(Model model) {

		model.addAttribute("empresa", new Empresa());

		return "empresaForm";
	}

	@PostMapping("/guardar")
	public String guardarEmpresa(Empresa empresa) {

		empresaService.guardarEmpresa(empresa);

		return "redirect:/empresa/empresas";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {

		Empresa empresa = empresaService.getEmpresaById(id);

		model.addAttribute("empresa", empresa);

		return "empresaForm";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarEmpresa(@PathVariable("id") Long id, Model model) {

		empresaService.eliminarEmpresa(id);

		List<Empresa> listEmpresas = empresaService.getAllEmpresas();
		model.addAttribute("empresas", listEmpresas);

		return "redirect:/empresa/empresas";
	}

	@GetMapping("/reporte")
	public String generarReporte(Model model) {

		List<Empresa> empresas = empresaService.getAllEmpresas();

		model.addAttribute("empresas", empresas);

		return "empresaReport";
	}

}
