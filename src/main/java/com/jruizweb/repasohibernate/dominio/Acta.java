package com.jruizweb.repasohibernate.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
//@Table(name="acta") no es necesaria esta anotacion ya que hibernate con entity ya la crea 
public class Acta {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_acta;
	private String contenido;
	@OneToOne
	@JoinColumn
	private Reunion reunion;
	
	public Acta() {
	}

	public Acta(String contenido, Reunion reunion) {
		this.contenido = contenido;
		this.reunion = reunion;
		reunion.setActa(this);
	}

	public int getId_acta() {
		return id_acta;
	}

	public void setId_acta(int id_acta) {
		this.id_acta = id_acta;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Acta [id_acta=");
		builder.append(id_acta);
		builder.append(", contenido=");
		builder.append(contenido);
		builder.append("]");
		return builder.toString();
	}
	
}
