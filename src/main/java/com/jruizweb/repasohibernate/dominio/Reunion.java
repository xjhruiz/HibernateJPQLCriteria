package com.jruizweb.repasohibernate.dominio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
//import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reunion")
public class Reunion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "id_reunion")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	private Date fecha;
	private LocalDateTime fecha;
	private String asunto;

	// si quiero que recupere todos los datos de la salas eager si no quiero Lazy
	@ManyToOne(fetch = FetchType.LAZY)
	private Sala sala;

	@OneToOne(mappedBy = "reunion") // mapeada por el atributo reunion de la clase acta
	private Acta acta;

	public Reunion() {
		participantes = new HashSet();
	}
	
	//Necesario para que hb guarde con criterias los datos que va recibiendo en un reunion
	public Reunion(Reunion r) {
		this.fecha = r.fecha;
		this.asunto = r.asunto;
	}
	

	// conjunto de personas lista sin ordenar
	@ManyToMany(mappedBy = "reuniones", cascade = CascadeType.ALL)
	private Set<Persona> participantes;

	public Reunion(LocalDateTime fecha, String asunto) {
		this();
		this.fecha = fecha;
		this.asunto = asunto;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asundo) {
		this.asunto = asundo;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Acta getActa() {
		return acta;
	}

	public void setActa(Acta acta) {
		this.acta = acta;
	}

	public Set<Persona> getParticipantes() {
		return participantes;
	}

	public void addParticipante(Persona persona) {
		participantes.add(persona);
		if (!persona.getReuniones().contains(this)) {
			persona.addReunion(this);
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reunion [id=");
		builder.append(this.id);
		builder.append(", fecha=");
//		builder.append(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(this.fecha));
		builder.append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(this.fecha));
		builder.append(", asundo=");
		builder.append(this.asunto);
		builder.append("]");
		return builder.toString();
	}

}
