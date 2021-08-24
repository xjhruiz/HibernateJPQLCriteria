package com.jruizweb.repasohibernate;

import java.util.List;

import com.jruizweb.repasohibernate.dao.ReunionDao;
import com.jruizweb.repasohibernate.dominio.Reunion;

public class AppReuniones {

	
	//Avanzado Mirar Spring Data
	
	public static void main(String[] args) {
		ReunionDao reunionDao = new  ReunionDao();
		System.out.println(reunionDao.reunionesParticipantes("N34354"));
	}

}
