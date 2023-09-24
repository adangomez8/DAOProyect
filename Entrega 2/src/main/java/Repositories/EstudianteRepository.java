package Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Entities.Carrera;
import Entities.Estudiante;

public class EstudianteRepository implements CRUDRepository<Estudiante>{

	private EntityManagerFactory emf;

	public EstudianteRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	
	public Estudiante getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Estudiante.class,id);
	}

	
	public void create(Estudiante element) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(element);
		em.getTransaction().commit();
		em.close();
	}

	
	public void update(Estudiante element) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if(em.find(Estudiante.class,element.getNroDoc()) != null){
			em.merge(element);
		}else{
			// si no esta no quiero agregarlo
		}
		em.getTransaction().commit();
		em.close();
	}

	
	public void delete(Estudiante element) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(element);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Estudiante> getEstudianteOrderByDoc() {
		EntityManager em = emf.createEntityManager();
		String jpql= "SELECT e FROM Estudiante e ORDER BY e.nroDoc ASC";
		Query query=em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Estudiante>resultados= query.getResultList();
		return resultados;
	}

	public Estudiante getEstudianteByLibreta(int nroLibreta){
		EntityManager em = emf.createEntityManager();
		Query consulta2D = em.createQuery("SELECT e FROM Estudiante e WHERE e.nroLibreta = ?1");
		consulta2D.setParameter(1,nroLibreta);
		return (Estudiante) consulta2D.getSingleResult();
	}
	
	public List<Estudiante> getEstudianteByGenero(String genero) {
		EntityManager em= emf.createEntityManager();
		Query jpql= em.createQuery("SELECT e FROM Estudiante e WHERE e.genero =?1");
		jpql.setParameter(1, genero);
		@SuppressWarnings("unchecked")
		List<Estudiante>resultado= jpql.getResultList();
		return resultado;
	}
	
	public List<Estudiante> getEstudianteByCarreraAndLocalidad(String localidad, Carrera car){
		EntityManager em= emf.createEntityManager();
		Query jpql = em.createQuery("SELECT e FROM Estudiante e JOIN e.infoCarreras ic WHERE e.localidad = ?1 AND ic.carrera = ?2");
		jpql.setParameter(1,localidad);
		jpql.setParameter(2,car);
		@SuppressWarnings("unchecked")
		List<Estudiante>resultado= jpql.getResultList();
		return resultado;
	}
}
