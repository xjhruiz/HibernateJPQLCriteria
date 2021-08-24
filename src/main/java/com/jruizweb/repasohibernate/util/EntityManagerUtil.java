package com.jruizweb.repasohibernate.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	public static EntityManager getEntityManagerUtil() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RepasoHibernate");
		EntityManager entityManager = factory.createEntityManager();
		return entityManager;
	}
	
	public static void main(String[] args) {
		EntityManager entityManager = getEntityManagerUtil();
		System.out.println("Clase Entity => " +entityManager.getClass().getCanonicalName());
	}

}
