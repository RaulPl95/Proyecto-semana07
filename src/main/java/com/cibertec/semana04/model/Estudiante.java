package com.cibertec.semana04.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiante")
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int idEstudiante;

	@Column(name = "nombre", nullable = false)
	public String nombre;

	@Column(name = "apellidos", nullable = false)
	public String apellidos;

	@Column(name = "dni", nullable = false)
	public int dni;

	@Column(name = "codigo", nullable = false)
	public Long codigo;

	@Column(name = "carrera", nullable = false)
	public String carrera;

	public int getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		carrera = carrera;
	}

}
