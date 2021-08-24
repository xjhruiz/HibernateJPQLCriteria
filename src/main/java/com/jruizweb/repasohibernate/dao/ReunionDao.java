package com.jruizweb.repasohibernate.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.jruizweb.repasohibernate.dominio.Persona;
import com.jruizweb.repasohibernate.dominio.Reunion;

public class ReunionDao extends AbstractDao<Reunion> {
	
	public ReunionDao() {
		setClassEntityManager(Reunion.class);
		
	}
	
	//Metodos especificado para cada dao de cada entidad
	
	
	public Reunion proximaReunion() {
		String sqlQ = "FROM " + Reunion.class.getName() + " WHERE fecha > now() order by fecha";
		//como maximo quiero un resultado
		Query query = getEntityManager().createQuery(sqlQ).setMaxResults(1);
		// y que me retornes un resultado simple
		return (Reunion) query.getSingleResult();
	}
	
	public List<Reunion> reunionesEntre2Fechas(LocalDateTime fechaIni, LocalDateTime fechaFin){
		String sqlQ = "FROM "+ Reunion.class.getName() + " WHERE fecha between ?1 and ?2";
		Query query = getEntityManager().createQuery(sqlQ);
//		query.setParameter(1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(fechaIni));
		query.setParameter(1,  fechaIni);
//		query.setParameter(2,  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(fechaIni));
		query.setParameter(2,  fechaFin);
		return query.getResultList();
	}
	
	public List<Reunion> reunionesParticipantes(String numEmpleado){
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Reunion> criteriaQuery = cb.createQuery(Reunion.class);
		//Como se quiere filtrar por los participantes, se crea el root sobre persona
		Root<Persona> rootP =criteriaQuery.from(Persona.class);
		criteriaQuery.where(cb.equal(rootP.get("num_empleado"), numEmpleado));
		
		Join<Persona, Reunion> joinReunion = rootP.join("reuniones", JoinType.INNER);
		CriteriaQuery<Reunion> cq = criteriaQuery.multiselect(joinReunion);
		TypedQuery<Reunion> query = getEntityManager().createQuery(cq);
		return query.getResultList();
	}
}
