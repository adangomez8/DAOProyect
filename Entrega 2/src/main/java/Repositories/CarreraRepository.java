package Repositories;

import Dtos.ReporteCarrera;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.LocalDate;
import Entities.Carrera;
import Entities.InfoCarrera;
import java.util.ArrayList;
import java.util.Date;

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
	
        public List<ReporteCarrera> getReportCarrera(){//ejercicio 3
            EntityManager em= emf.createEntityManager();
            String jpql="SELECT c FROM Carrera c ORDER BY c.nombre";
            String jpqlEstudianteCarrera="SELECT e FROM InfoCarrera e ORDER BY e.antiguedad, e.fechaGraduacion";
            Query queryEs=em.createQuery(jpqlEstudianteCarrera);
            List<InfoCarrera>info=queryEs.getResultList();
            
            Query query=em.createQuery(jpql);
            List<Carrera>carreras=query.getResultList();
            List<ReporteCarrera>r=new ArrayList(returnReporte(new ArrayList(info),new ArrayList(carreras)));
            return r;
        }
        
        
        private List<ReporteCarrera> returnReporte(ArrayList<InfoCarrera> info,ArrayList<Carrera>carreras){
            ArrayList<ReporteCarrera>reportes=new ArrayList();
            for(Carrera c:carreras){
                ReporteCarrera re=new ReporteCarrera(c.getNombre());
                for(InfoCarrera i:info){
                   if(i.getCarrera().equals(c)){
                       LocalDate hoy = LocalDate.now();
                       int año=hoy.getYear()-i.getAntiguedad();
                       re.getDatos().addInscripto(año);
                       if(i.isGraduado()){
                           int añoGra=i.getFechaGraduacion().getYear();
                           re.getDatos().addEgresado(añoGra);
                       }
                       reportes.add(re);
                    }
                }
            }
            return reportes;
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
