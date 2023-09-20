package Repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import Entities.Carrera;

public class CarreraRepository implements CRUDRepository<Carrera>{
	
	private EntityManagerFactory emf;
	
	public CarreraRepository(EntityManagerFactory emf) {
		this.emf=emf;
	}
	
	public Carrera getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Carrera.class, id);
	}
	
	public void create(Carrera c) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(Carrera c) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update(Carrera element) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if(em.contains(element)){
			em.merge(element);
		}else{
			// si no esta no quiero agregarlo
		}
		em.getTransaction().commit();
		em.close();
	}
}
