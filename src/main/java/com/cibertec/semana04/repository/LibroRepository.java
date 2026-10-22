package com.cibertec.semana04.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.semana04.model.Libro;
import com.cibertec.semana04.model.Reporte;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByEditorialIdEditorial(Long idEditorial);

    List<Libro> findByNombreContaining(String nombre);
    
    List<Libro> findByNombreContainingIgnoreCaseOrAutorContainingIgnoreCase(
            String nombre, String autor);
    
    List<Libro> findByNombreContainingIgnoreCase(String nombre);

    List<Libro> findByAutorContainingIgnoreCase(String autor);

    @Query("SELECT new com.cibertec.semana04.model.Reporte(" +
    	       "e.idEditorial, e.nombre, COUNT(l)) " +
    	       "FROM Editorial e LEFT JOIN e.libros l " +
    	       "GROUP BY e.idEditorial, e.nombre " +
    	       "ORDER BY e.nombre")
    List<Reporte> contarLibroPorEditorial();
}