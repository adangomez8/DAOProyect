package main.java.Repositories;

import javax.persistence.EntityManager;

import Entities.InfoCarrera;

public class InfoCarreraRepository implements CRUDRepository<InfoCarrera>{
	
	private EntityManager em;
	
	public InfoCarreraRepository(EntityManager em) {
		this.em=em;
	}
	
	public InfoCarrera getById(int id) {
		return em.find(InfoCarrera.class, id);
	}
	
	public void create(InfoCarrera inf) {
		em.getTransaction();
		InfoCarrera i= new InfoCarrera(inf.getCarrera(),inf.isGraduado(),inf.getAntiguedad());
		em.persist(i);
	}
	
	public void delete(InfoCarrera inf) {
		em.remove(inf);
	}

}
