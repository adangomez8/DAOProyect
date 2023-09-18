package main.java.Repositories;

import javax.persistence.EntityManager;

import Entities.Carrera;

public class CarreraRepository implements CRUDRepository<Carrera>{
	
	private EntityManager em;
	
	public CarreraRepository(EntityManager em) {
		this.em=em;
	}
	
	public Carrera getById(int id) {
		return em.find(Carrera.class, id);
	}
	
	public void create(Carrera c) {
		em.getTransaction().begin();
		Carrera car= new Carrera(c.getNombre(),c.getDuracion());
		em.persist(car);
	}
	
	public void delete(Carrera c) {
		em.remove(c);
	}

}
