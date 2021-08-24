package com.jruizweb.repasohibernate.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala {
	@Id
	@Column(length = 15)
	private String id_sala;
	private String descripcion;
	private int capacidad;
	
	@OneToMany(mappedBy = "sala")
	private List<Reunion> reuniones;
	

	public Sala() {
		// TODO Auto-generated constructor stub
	}
	public Sala(String id_sala, String descripcion, int capacidad) {
		this.id_sala = id_sala;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
	}
	
	public String getId_sala() {
		return id_sala;
	}

	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sala [id_sala=");
		builder.append(id_sala);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", capacidad=");
		builder.append(capacidad);
		builder.append("]");
		return builder.toString();
	}



}
