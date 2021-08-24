package com.jruizweb.repasohibernate.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.jruizweb.repasohibernate.util.EntityManagerUtil;

public abstract class AbstractDao<T> implements IDao<T> {
	private EntityManager em = EntityManagerUtil.getEntityManagerUtil();
	private Class<T> classEntityManager;

	@Override
	public Optional<T> getById(Integer id) {
		// optional en caso de no encontrar nada siga funcionando evita los npe
		return Optional.ofNullable(em.find(classEntityManager, id));
	}

	@Override
	public List<T> getAll() {
		// distinto a jqpl
		Query query = em.createQuery("FROM " + classEntityManager.getName());
		return query.getResultList();
	}

	@Override
	public void save(T t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
	}

	@Override
	public void update(T t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));

	}

	@Override
	public void delete(T t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));

	}

	public void setClassEntityManager(Class<T> t) {
		this.classEntityManager = t;

	}

	// Consumer accion que se va a realizar por el entityManager mirar mas a fondo
	public void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			action.accept(em);
			et.commit();
		} catch (RuntimeException e) {
			et.rollback();
			throw e;
		}
	}

	public EntityManager getEntityManager() {
		return em;
	}

}
