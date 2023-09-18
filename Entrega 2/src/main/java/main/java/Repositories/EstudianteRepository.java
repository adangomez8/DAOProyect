package main.java.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entities.Estudiante;

public class EstudianteRepository implements CRUDRepository<Estudiante>{
	
	private EntityManager em;
	
	public EstudianteRepository(EntityManager em) {
		this.em=em;	
	}
	
	public Estudiante getById(int id) {
		return em.find(Estudiante.class,id);
	}
	
	public void create(Estudiante e) {
		em.getTransaction().begin();
		Estudiante est= new Estudiante(e.getNroDoc(),e.getNombre(),e.getApellido(),e.getNroLibreta(),e.getLocalidad());
		em.persist(est);
	}
	
	public void delete(Estudiante e) {
		em.getTransaction();
		em.remove(e);
	}
	
	public List<Estudiante> getEstudianteOrderByDoc() {
		em.getTransaction().begin();
		String jpql= "SELECT e FROM Estudiante e ORDER BY e.nroDoc ASC";
		Query query=em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Estudiante>resultados= query.getResultList();
		return resultados;
	}
	
}
