package com.jruizweb.repasohibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.NoResultException;

import com.jruizweb.repasohibernate.dao.ActaDao;
import com.jruizweb.repasohibernate.dao.ReunionDao;
import com.jruizweb.repasohibernate.dao.SalaDao;
import com.jruizweb.repasohibernate.dominio.Acta;
import com.jruizweb.repasohibernate.dominio.Persona;
import com.jruizweb.repasohibernate.dominio.Reunion;
import com.jruizweb.repasohibernate.dominio.Sala;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ReunionDao reunionDao = new ReunionDao();
		List<Reunion> reuniones = reunionDao.getAll();
		System.out.println("*** " + reuniones);

		Persona p1 = new Persona("M347O", "Julio", "Cesar");
		Persona p2 = new Persona("M324O", "Sara", "Hetal");

		Reunion reunion = new Reunion(LocalDateTime.now(), "Reunion 1º con personas de una en una ");
		System.out.println(reunion);
		reunion.addParticipante(p1);
		reunion.addParticipante(p2);
		reunionDao.save(reunion);
		System.out.println(reunion);
		
		Reunion r2 = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "Reunion diferente con otras personas");
		p1.addReunion(r2);
		reunionDao.save(r2);
		
		/*
		 * Set<Reunion> reunionesPepe = new HashSet(); reunionesPepe.add(r2);
		 * reunionesPepe.add(reunion); p1.setReuniones(reunionesPepe);
		 * 
		 * Set<Reunion> reunionesJulia = new HashSet(); reunionesJulia.add(r2);
		 * p2.setReuniones(reunionesJulia); // Optional<Reunion> reunionAEliminar =
		 * reunionDao.getById(3); // System.err.println(reunionAEliminar); //
		 * reunionDao.delete(reunionAEliminar.get());
		 * 
		 * // try { // // Mejor usar localDate o LocalDateTime que date o calender // /*
		 * // * LocalDate myDate =LocalDate.parse("2014-02-14"); // or LocalDate myDate2
		 * = // * new LocalDate(2014, 2, 14); // or, in JDK 8+ Time LocalDate myDate3 =
		 * // * LocalDate.of(2014, 2, 14); //
		 */
////			reunionAEliminar.get().setFecha(new SimpleDateFormat("yyyy-MM-DD hh:mm:ss").parse("2021-08-23 20:30:00"));
//		} catch (ParseException e) {e.printStackTrace();}
//		reunionAEliminar.get().setAsundo("Cena modificada2");
//		reunionAEliminar.get().setFecha(LocalDateTime.of(2021,8,23,20,30,00));
//		reunionAEliminar.get().setFecha(LocalDateTime.now().plus(2,ChronoUnit.DAYS));
//		reunionDao.update(reunionAEliminar.get());
//		System.out.println("*** " + reuniones);
//		try {
//			System.out.println("Su proxima reunion será : " + reunionDao.proximaReunion());
//		} catch (NoResultException e) {
//			System.out.println("No hay ninguna reunion ");
//		}

//		reunionDao.save(new Reunion(LocalDateTime.parse("2021-08-30T10:30"),"Reunion Especial"));
//		reunionDao.save(new Reunion(LocalDateTime.parse("2021-08-31T10:30"),"Reunion Especial dia 2"));
//		reunionDao.save(new Reunion(LocalDateTime.parse("2021-08-31T10:30",DateTimeFormatter.ISO_LOCAL_DATE_TIME),"Reunion Especial dia 2"));
//		System.out.println(reunionDao.reunionesEntre2Fechas(LocalDateTime.parse("2021-08-31T08:03:00"), LocalDateTime.parse("2021-09-01T00:00:00")));

		SalaDao salaDao = new SalaDao();
		/*
		 * Sala sala = new Sala("Z251","Sala grande", 234); salaDao.save(sala );
		 * System.out.println("insetar "+ salaDao.getAll());
		 * sala.setDescripcion("Sala descripcion modificada"); salaDao.update(sala);
		 * System.out.println("Actualizada "+ salaDao.getAll()); sala = new
		 * Sala("Z21","Limpieza", 2); salaDao.save(sala );
		 * System.out.println("Insetada sala limpieza "+ salaDao.getAll());
		 * salaDao.delete(sala); System.out.println("Eliminada sala limpieza "+
		 * salaDao.getAll());
		 */
		/*
		 * Reunion reunion2 = new Reunion(LocalDateTime.now(),"Reunion temporal");
		 * reunionDao.save(reunion2); System.out.println("Reuniones "+
		 * reunionDao.getAll()); ActaDao actaDao = new ActaDao(); Acta acta = new
		 * Acta("Reunion temporal",reunion2); actaDao.save(acta);
		 * System.out.println("Lista Acta "+actaDao.getAll());
		 * acta.setContenido("Reunion Acta modificada"); actaDao.update(acta);
		 */
//		System.out.println("Lista Acta "+actaDao.getAll());

	}
}
