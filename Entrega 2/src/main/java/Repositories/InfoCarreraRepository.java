package Repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import Entities.Carrera;
import Entities.InfoCarrera;

public class InfoCarreraRepository implements CRUDRepository<InfoCarrera>{

	private EntityManagerFactory emf;
	
	public InfoCarreraRepository(EntityManagerFactory emf) {
		this.emf=emf;
	}
	
	public InfoCarrera getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(InfoCarrera.class, id);
	}
	
	public void create(InfoCarrera inf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(inf);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(InfoCarrera inf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(inf);
		em.getTransaction().commit();
		em.close();
	}

	
	public void update(InfoCarrera element) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if(em.find(InfoCarrera.class,element.getId()) != null){
			em.merge(element);
		}else{
			// si no esta no quiero agregarlo
		}
		em.getTransaction().commit();
		em.close();
	}
}
