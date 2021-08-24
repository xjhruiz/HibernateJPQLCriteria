package com.jruizweb.repasohibernate;

import java.util.List;

import com.jruizweb.repasohibernate.dao.SalaDao;
import com.jruizweb.repasohibernate.dominio.Sala;

public class AppQuerysForDao {

	public static void main(String[] args) {
		SalaDao salaDao = new SalaDao();
//		List<Sala> salasParaDos = salaDao.findSalasByCapacity(2);
		List<Sala> salasParaDos = salaDao.findSalasByCapacityHb(2);
		System.out.println("Salas para mas de 2 personas "+  salasParaDos);
		
		
//		List<Sala> salasParaMasDe23 = salaDao.findSalasByCapacity(23);
		List<Sala> salasParaMasDe23 = salaDao.findSalasByCapacityHb(23);
		System.out.println("Salas para mas de 23 personas "+  salasParaMasDe23);
		
		List<Sala> salasEntreUnNumeroYElDoble = salaDao.findSalasByCapacityBetweenBy2Criteria(2);
		System.out.println("Salas para mas de un numero de personas y su doble"+  salasEntreUnNumeroYElDoble);
	}

}
