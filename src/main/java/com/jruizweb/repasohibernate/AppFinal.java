package com.jruizweb.repasohibernate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.NoResultException;

import com.jruizweb.repasohibernate.dao.ActaDao;
import com.jruizweb.repasohibernate.dao.ReunionDao;
import com.jruizweb.repasohibernate.dao.SalaDao;
import com.jruizweb.repasohibernate.dominio.Acta;
import com.jruizweb.repasohibernate.dominio.Persona;
import com.jruizweb.repasohibernate.dominio.Reunion;
import com.jruizweb.repasohibernate.dominio.Sala;

public class AppFinal {

	public static void main(String[] args) {

		ReunionDao reunionDao = new ReunionDao();
		ActaDao actaDao = new ActaDao();
		SalaDao salaDao = new SalaDao();
		
		Sala sala1 = new Sala("P543","Limpieza",2);
		Sala sala2 = new Sala("P532","Reunion Semanal",23);
		Sala sala3 = new Sala("P513","Reuniones Centro",454);
		Sala sala4 = new Sala("P54D3","Entrevistas",3);
		
		salaDao.save(sala1);
		salaDao.save(sala2);
		salaDao.save(sala3);
		salaDao.save(sala4);
		
		Persona p1 = new Persona("N34354","Francisco", "Perez Sanchez");
		Persona p2 = new Persona("N32354","Marta", "Sanchez Perez");
		Persona p3 = new Persona("N3T354","Hector", "Herdandez");
		Persona p4 = new Persona("N356D4","Julia", "Diaz Plaza");
		Persona p5 = new Persona("N343D4","Maria", "Pepon Plaza");
		
		Reunion r1 = new Reunion(LocalDateTime.now(),"Reunion Limpieza");
		Reunion r2 = new Reunion(LocalDateTime.now().plus(2,ChronoUnit.DAYS),"Reunion Semanal");
		Reunion r3 = new Reunion(LocalDateTime.now().plus(1,ChronoUnit.MONTHS),"Reunion Mensual");
		Reunion r4 = new Reunion(LocalDateTime.now().plus(3,ChronoUnit.WEEKS),"Directiva");
		Reunion r5 = new Reunion(LocalDateTime.now().minus(1,ChronoUnit.DAYS),"Reunion Diaria");
		
		r1.addParticipante(p2);
		r1.addParticipante(p3);
		r1.setSala(sala1);
		reunionDao.save(r1);
		System.out.println(" Reunines sin acta: "+reunionDao.getAll());
		Acta actaLimpieza = new Acta("Reuninon r1 Se reunen Marta y Hector para acordar la forma de limpiar las salas", r1);
		actaDao.save(actaLimpieza);
		reunionDao.update(r1);
		System.out.println("Reuniones con actas: "+reunionDao.getAll());
		
		r2.addParticipante(p1);
		r2.addParticipante(p2);
		r2.addParticipante(p3);
		r2.addParticipante(p4);
		r2.addParticipante(p5);
		r2.setSala(sala2);
		reunionDao.save(r2);
		System.out.println(" Reunines sin acta: "+reunionDao.getAll());
		
		r3.addParticipante(p5);
		r3.addParticipante(p4);
		r3.addParticipante(p1);
		r3.setSala(sala3);
		reunionDao.save(r3);
		Acta actaCentro = new Acta("Reunion r3 Se reunen Maria, Julia, Francisco para ver la forma de orgarizar centros", r3);
		actaDao.save(actaCentro);
		reunionDao.update(r3);
		System.out.println("Reuniones con actas: "+reunionDao.getAll());
		
		
		r5.addParticipante(p5);
		r5.addParticipante(p1);
		r5.setSala(sala4);
		reunionDao.save(r5);
		Acta actaMejoraCondiciones = new Acta("Reunion r5 Se reunen para mejorar las condiciones de los empleados", r5);
		actaDao.save(actaMejoraCondiciones);
		reunionDao.update(r5);
		System.out.println("Reuniones con actas: "+reunionDao.getAll());
		
		try {
			System.out.println("Su proxima reunion será : " + reunionDao.proximaReunion());
		} catch (NoResultException e) {
			System.out.println("No hay ninguna reunion ");
		}
		System.out.println(reunionDao.reunionesEntre2Fechas(LocalDateTime.parse("2021-08-31T08:03:00"), LocalDateTime.parse("2021-09-01T00:00:00")));
	}

}
