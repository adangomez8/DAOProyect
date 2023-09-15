
package CrudJPA;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.protobuf.Timestamp;

import Entities.Estudiante;

/**
 *
 * @author ilari
 */


public class Insert {
	
	public static void insertEstudiante(Estudiante e,EntityManager em) {
		 em.getTransaction().begin();
		 
	}
	
    public static void main(String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("ejemplito");
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
