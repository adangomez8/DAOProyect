/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CrudJPA;

import Dtos.ReporteCarrera;
import Entities.Carrera;
import Repositories.CarreraRepository;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ilari
 */
public class Select {
    
    
    
    public static void main(String[] args){
    	Select s=new Select();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("ejemplito");
        CarreraRepository repoC=new CarreraRepository(emf);
        ArrayList<ReporteCarrera>reporte=new ArrayList(repoC.getReportCarrera());
        System.out.println(reporte);
        EntityManager em=emf.createEntityManager();
       
    }
    
}
