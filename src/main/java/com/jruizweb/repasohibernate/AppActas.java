package com.jruizweb.repasohibernate;

import java.util.List;

import com.jruizweb.repasohibernate.dao.ActaDao;
import com.jruizweb.repasohibernate.dominio.Acta;

public class AppActas {

	public static void main(String[] args) {

		ActaDao actaDao = new  ActaDao();
//		List<Acta> listaActas = actaDao.findActasReunionesAntiguas();
		List<Acta> listaActas = actaDao.findActasReunionesYesterdayHB();
		System.out.println("Listas Antiguas " +listaActas);
	}

}
