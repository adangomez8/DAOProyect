package Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Entities.Estudiante;

public class EstudianteRepository implements CRUDRepository<Estudiante>{

	private EntityManagerFactory emf;

	public EstudianteRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Estudiante getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Estudiante.class,id);
	}

	@Override
	public void create(Estudiante element) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(element);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update(Estudiante element) {
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

	@Override
	public void delete(Estudiante element) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(element);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Estudiante> getEstudianteOrderByDoc() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String jpql= "SELECT e FROM Estudiante e ORDER BY e.nroDoc ASC";
		Query query=em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Estudiante>resultados= query.getResultList();
		return resultados;
	}
	
}
