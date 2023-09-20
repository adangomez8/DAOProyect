package Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

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
	
	public List<Carrera> getCarreraByInscriptos(){
		EntityManager em= emf.createEntityManager();
		String jpql= "SELECT c FROM Carrera c WHERE SIZE(c.estudiantes) >0 ORDER BY SIZE(c.estudiantes)";
		Query query=em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Carrera>resultado= query.getResultList();
		return resultado;
		
	}
}
