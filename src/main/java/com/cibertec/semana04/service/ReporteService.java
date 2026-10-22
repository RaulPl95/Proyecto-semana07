package com.cibertec.semana04.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cibertec.semana04.model.Libro;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReporteService {

	private final BibliotecaService bibliotecaService;

	public ReporteService(BibliotecaService bibliotecaService) {
		this.bibliotecaService = bibliotecaService;
	}

	public byte[] generarReporteLibros() throws Exception {

		// Cargar la plantilla JRXML
		try (InputStream stream = getClass().getResourceAsStream("/reports/report_books.jrxml")) {

			if (stream == null) {
				throw new IllegalStateException("No se encontró report_books.jrxml en resources/reports");
			}

			// Compilar la plantilla
			JasperReport reporte = JasperCompileManager.compileReport(stream);

			// Obtener los libros registrados
			List<Libro> libros = bibliotecaService.listarLibros();

			// Preparar los datos
			JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(libros);

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("usuario", "Raul Pl");

			// Generar el documento
			JasperPrint impresion = JasperFillManager.fillReport(reporte, parametros, datos);

			// Exportar a PDF
			return JasperExportManager.exportReportToPdf(impresion);
		}
	}
	
	public byte[] generarReporteIndicador() throws Exception {

		// Cargar la plantilla JRXML
		try (InputStream stream = getClass().getResourceAsStream("/reports/report_booksindicator.jrxml")) {

			if (stream == null) {
				throw new IllegalStateException("No se encontró report_booksindicator.jrxml en resources/reports");
			}

			// Compilar la plantilla
			JasperReport reporte = JasperCompileManager.compileReport(stream);

			// Obtener los libros registrados
			List<Libro> libros = bibliotecaService.listarLibros();

			// Preparar los datos
			JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(libros);

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("usuario", "Raul Pl");

			// Generar el documento
			JasperPrint impresion = JasperFillManager.fillReport(reporte, parametros, datos);

			// Exportar a PDF
			return JasperExportManager.exportReportToPdf(impresion);
		}
	}
}