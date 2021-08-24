package com.jruizweb.repasohibernate.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.jruizweb.repasohibernate.dominio.Acta;
import com.jruizweb.repasohibernate.dominio.Reunion;

public class ActaDao extends AbstractDao<Acta> {

	/****
		JQPL => SELECT, INSERT, UPDATE, DELETE
		     => SELECT ESTATICOS mismas condiciones
		     => SIN PAGINACION
			 => ES MAS RAPIDO
			 => DIFICIL PROTECCION DE LA INYECCION SQL CON COMILLAS 
			 
			 
		CRITERIA => SOLO SELECT 
				 => SELECT DINAMICOS nombre like %ParametroDinamico% => los criterios dependen del nombre del parametro que recibamos
				 => CON PAGINACION
				 => ES MAS LENTO
				 => PROTEGIDA DE LA INYECCION SQL
	
	
	***/
	
	public ActaDao() {
		setClassEntityManager(Acta.class);
	}
	
	public List<Acta> findActasReunionesAntiguas(){													//JPQL no tiene estas funciones => date_sub(now(), interval 1 day)
		//los inner join se hacen uniendo objetos no como sql normal a INNER JOIN Reunion r where r.fecha < :ayer
		//String sqlQ= "FROM " + Acta.class.getName()+ " a INNER JOIN " + Reunion.class.getName()+ " r  WHERE r.fecha < :ayer ";
		//jpql como si se manejaran por objetos=> From clase a where a.reunion.fecha
		String sqlQ= "FROM " + Acta.class.getName()+ " a WHERE a.reunion.fecha < :ayer";
		
		LocalDateTime ayer = LocalDateTime.now().minusDays(1);
		Query query = getEntityManager().createQuery(sqlQ);
		query.setParameter("ayer", ayer);
		return query.getResultList();
	}
	
	public List<Acta> findActasReunionesYesterdayHB(){
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Acta> criteriaQuery = cb.createQuery(Acta.class);
		Root<Acta> root = criteriaQuery.from(Acta.class);
		
		//Unir con join 2 tablas
		Join<Acta, Reunion> joinReunion = root.join("reunion",JoinType.INNER);
		
		//Predicado da la condicion a buscar debe de hacer referencia al atributo del campo a buscar del join
		Predicate fechaAyer = cb.lessThan(joinReunion.get("fecha"), LocalDateTime.now().minusDays(1));
		criteriaQuery.where(fechaAyer);
		Query query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();
		
	}
	
}
