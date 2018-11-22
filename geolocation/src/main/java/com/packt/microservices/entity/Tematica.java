package com.packt.microservices.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tematica {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (nullable = false)
	private String nombre;
	
	@Column (nullable = false)
	private Boolean es_experto;

	public Tematica(String nombre, Boolean esExperto) {
		this.nombre = nombre;
		this.es_experto = esExperto;
	}
	
	public Tematica() {
	}

	@Override
	public String toString() {
		String s = "";
		
		if (es_experto) { s = "Conocimiento experto"; }
		else { s = "Conocimiento general"; }
		
		return "Tematica [id = " + this.id + ", nombre = " + this.nombre + ", esExperto = " + s + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getEsExperto() {
		return es_experto;
	}

	public void setEsExperto(Boolean esExperto) {
		this.es_experto = esExperto;
	}

	public int getId() {
		return id;
	}

}
