package com.packt.microservices.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trabajo {

	//--------------Atributos de clase--------------

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nombre;

	@ManyToOne
	@JoinColumn(nullable = false)
	private TipoTrabajo tipo_trabajo;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "trabajos_investigacion")
	@JsonIgnore
	@Column(nullable = false)
	Set<Usuario> autores;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "trabajos_evaluacion")
	@JsonIgnore
	@Column(nullable = false)
	Set<Usuario> evaluadores;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	Set<Tematica> tematicas;

	//--------------Constructor--------------

	public Trabajo(String nombre, TipoTrabajo tipo, Set<Usuario> autores, Set<Tematica> tematicas) {
		if (autores.size() <= 3) {
			this.autores = autores;	
		}
		else {
			this.autores = new HashSet<Usuario>();
		}
		this.nombre = nombre;
		this.tipo_trabajo = tipo;
		this.tematicas = tematicas;
		this.evaluadores = new HashSet<Usuario>();
	}

	public Trabajo(String nombre, TipoTrabajo tipo) {
		this.nombre = nombre;
		this.tipo_trabajo = tipo;
		this.tematicas = new HashSet<Tematica>();
		this.autores = new HashSet<Usuario>();
		this.evaluadores = new HashSet<Usuario>();
	}

	public Trabajo() {
		this.tematicas = new HashSet<Tematica>();
		this.autores = new HashSet<Usuario>();
		this.evaluadores = new HashSet<Usuario>();
	}

	//--------------toString--------------

	@Override
	public String toString() {
		String a = "";
		if (!this.autores.isEmpty()) {
			for (Usuario usuario : this.autores) {
				a += usuario.getNombre() + ", ";
				a += usuario.getApellido() + ".  ";
			}	
		}
		String t = "";
		if (!this.tematicas.isEmpty()) {
			for (Tematica tematica : this.tematicas) {
				t += tematica.getNombre() + ".  ";
			}	
		}

		String retorno = "Trabajo [id = " + this.id + ", nombre = " + this.nombre + ", tipo = " + this.tipo_trabajo.getNombre() 
		+ ", autores = " + a + ", tematicas = " + t + "]";
		return retorno;
	}

	//--------------Getters y setters--------------

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoTrabajo getTipoTrabajo() {
		return tipo_trabajo;
	}

	public void setTipoTrabajo(TipoTrabajo tipoTrabajo) {
		this.tipo_trabajo = tipoTrabajo;
	}

	public Set<Usuario> getAutores() {
		return autores;
	}

	public void setAutores(Usuario autor) {
		if (this.autores.size() <= 2) {
			this.autores.add(autor);
		}
	}

	public Set<Tematica> getTematicas() {
		return tematicas;
	}

	public void setTematicas(Tematica tema) {
		this.tematicas.add(tema);
	}

	public int getId() {
		return id;
	}

	public int getCantidadAutores() {
		return autores.size();
	}

	public int getCantidadTematicas() {
		return tematicas.size();
	}

	public Set<Usuario> getEvaluadores() {
		return evaluadores;
	}

	public void setEvaluadores(Usuario evaluador) {
		if (this.evaluadores.size() <= 2) {
			this.evaluadores.add(evaluador);
		}
	}

}
