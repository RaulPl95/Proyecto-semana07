package com.cibertec.semana04.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.semana04.service.ReporteService;

@RestController
@RequestMapping("/report")
public class ReportController {

	private final ReporteService reporteService;

	public ReportController(ReporteService reporteService) {
		this.reporteService = reporteService;
	}

	@GetMapping("/reportBook")
	public ResponseEntity<byte[]> generarReporte() throws Exception {

		byte[] pdf = reporteService.generarReporteLibros();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_libros.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(pdf);
	}
	
	@GetMapping("/reportBookIndicator")
	public ResponseEntity<byte[]> generarReporteIndicador() throws Exception {

		byte[] pdf = reporteService.generarReporteIndicador();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_indicador.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(pdf);
	}
}