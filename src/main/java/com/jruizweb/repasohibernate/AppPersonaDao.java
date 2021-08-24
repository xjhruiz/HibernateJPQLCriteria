package com.jruizweb.repasohibernate;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.jruizweb.repasohibernate.dao.PersonaDao;
import com.jruizweb.repasohibernate.dominio.Acta;
import com.jruizweb.repasohibernate.dominio.Persona;
import com.jruizweb.repasohibernate.dominio.Reunion;
import com.jruizweb.repasohibernate.dominio.Sala;

public class AppPersonaDao {

	public static void main(String[] args) {
		PersonaDao personaDao = new PersonaDao();
		Optional<Persona> optPersona = personaDao.getById(4);
		if (optPersona.isPresent()) {
			Persona p = optPersona.get();
//			System.out.println("Persona : 4 " + p);

			Set<Reunion> reuniones = p.getReuniones();
//			System.out.println("Reuniones" + reuniones);

			Set<Sala> salas = new HashSet();
			Set<Persona> personas = new HashSet();
			Set<Acta> actas = new HashSet();
			for (Reunion reunion : reuniones) {
				salas.add(reunion.getSala());
				personas.addAll(reunion.getParticipantes());
				actas.add(reunion.getActa());
			}
//			System.out.println("Salas :"+ salas);
//			System.out.println("Compañeros: "+ personas);
//			System.out.println("Actas: "+ actas);
		}
	}

}
