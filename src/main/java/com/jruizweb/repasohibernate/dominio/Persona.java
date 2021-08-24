package com.jruizweb.repasohibernate.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_persona;
	@Column(unique = true)
	private String num_empleado;
	private String nombre;
	private String apellido;
	
	@ManyToMany
	private Set<Reunion> reuniones;
	
	
	public Persona() {
		reuniones = new HashSet();
	}
	public Persona(String num_empleado, String nombre, String apellido) {
		this();
		this.num_empleado = num_empleado;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public Integer getId_persona() {
		return id_persona;
	}
	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}
	public String getNum_empleado() {
		return num_empleado;
	}
	public void setNum_empleado(String num_empleado) {
		this.num_empleado = num_empleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Set<Reunion> getReuniones() {
		return reuniones;
	}
	
	public void addReunion( Reunion reunion) {
		reuniones.add(reunion);
		if(!reunion.getParticipantes().contains(this)) {
			reunion.addParticipante(this);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Persona [id_persona=");
		builder.append(id_persona);
		builder.append(", num_empleado=");
		builder.append(num_empleado);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append("]");
		return builder.toString();
	}
	
}
