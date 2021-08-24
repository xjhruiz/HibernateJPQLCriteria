package com.jruizweb.repasohibernate.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.jruizweb.repasohibernate.dominio.Sala;

public class SalaDao extends AbstractDao<Sala> {

	public SalaDao() {
		setClassEntityManager(Sala.class);
	}

	public List<Sala> findSalasByCapacity(int num) {
		// atras jqpl
		String sqlQ = "FROM " + Sala.class.getName() + " WHERE capacidad >= ?1 ";
		Query query = getEntityManager().createQuery(sqlQ);
		query.setParameter(1, num);
		return query.getResultList();
	}

	// implementacion con criteriaQuery
	public List<Sala> findSalasByCapacityHb(int num) {
		// primero se genera un getCriteriaBuilder an entityManager
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		// Luego se necesita una querya de una criteriaQuery<> de nuestra clase Entidad
		// a buscar
		CriteriaQuery<Sala> criteriaQuery = cb.createQuery(Sala.class);
		// se genera un root que representa la entidad a buscar que necesitamos para
		// contruir los criterios por los que buscar en este caso solo necesitamos la
		// capacidad sea mayor o igual
		Root<Sala> root = criteriaQuery.from(Sala.class);
		// selecciona el root (representacion de nuestra clase ) donde el criterio
		// builder sea mayor o igual (a , b) a => la propiedad de nuestra clase a buscar
		// (root.get("capacidad"), b => el numero) // cb.ge (greater-than-or-equal
		// predicate)
		criteriaQuery.select(root).where(cb.ge(root.get("capacidad"), num));
		// ejecutame la sqlQ en base a la CriteriaQuery que se ha construido
		Query query = getEntityManager().createQuery(criteriaQuery);
		// dame la lista de resultados
		return query.getResultList();
	}

	public List<Sala> findSalasByCapacityBetweenBy2Criteria(int num) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Sala> criteriaQuery = cb.createQuery(Sala.class);
		Root<Sala> root = criteriaQuery.from(Sala.class);

		// predicate mirar a fondo de persistence y utils
		// entre 2 valores capacidadMin y maxima
		//corto 
		Predicate capacidadMin = cb.ge(root.get("capacidad"), num);
		//menor que o igual que largo
		Predicate capacidadMax = cb.lessThanOrEqualTo(root.get("capacidad"), num * 2);
		Predicate rangoCapacidad = cb.and(capacidadMin, capacidadMax);

		criteriaQuery.select(root).where(rangoCapacidad);
		Query query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();
	}
}
