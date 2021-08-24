package com.jruizweb.repasohibernate.dao;

import com.jruizweb.repasohibernate.dominio.Persona;

public class PersonaDao extends AbstractDao<Persona> {

	public PersonaDao() {
		setClassEntityManager(Persona.class);
	}
}
