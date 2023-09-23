
package CrudJPA;


import Entities.Carrera;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.protobuf.Timestamp;

import Entities.Estudiante;
import Entities.InfoCarrera;
import Repositories.CarreraRepository;
import Repositories.EstudianteRepository;
import Repositories.InfoCarreraRepository;

/**
 *
 * @author ilari
 */


public class Insert {
	
	public static void insertEstudiante(Estudiante e,EntityManager em) {
		 em.getTransaction().begin();
		 
	}
	
    public static void main(String[] args){
        /*
    }
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("ejemplito");
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
       
        EstudianteRepository repoE=new EstudianteRepository(emf);
        CarreraRepository repoC=new CarreraRepository(emf);
        InfoCarreraRepository repoI=new InfoCarreraRepository(emf);
        /*Estudiante nico=new Estudiante(42542399,"Nico","ilari",250425,"Tandil");
        repoE.create(nico);
        Estudiante tomi=new Estudiante(43542399,"Tomi","ilari",250435,"Tandil");
        repoE.create(tomi);
        
        Carrera sistemas=new Carrera("Sistemas",5);
        repoC.create(sistemas);
        Carrera tudai=new Carrera("Tudai",3);
        repoC.create(tudai);
        
        Estudiante nico=repoE.getById(42542399);
        Estudiante tomi=repoE.getById(43542399);
        Carrera tudai=repoC.getById(2);
        Carrera sistemas=repoC.getById(1);
        
        InfoCarrera iNico=repoI.getById(1);
        InfoCarrera iTomi=repoI.getById(2);
        nico.addInfoCarrera(iNico);
        tomi.addInfoCarrera(iTomi);
        
        em.merge(nico);
        em.merge(tomi);
        
        em.getTransaction().commit();
        em.close();
        emf.close();*/
    }
}
