import Entities.Carrera;
import Entities.Estudiante;
import Entities.InfoCarrera;
import Repositories.CarreraRepository;
import Repositories.EstudianteRepository;
import Repositories.InfoCarreraRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplito");

        CarreraRepository carreraRepository = new CarreraRepository(emf);
        EstudianteRepository estudianteRepository = new EstudianteRepository(emf);
        InfoCarreraRepository infoCarreraRepository = new InfoCarreraRepository(emf);

        // PASO 1: CREO CARRERAS Y ESTUDIANTES
        /*Estudiante jose = new Estudiante(44416737,"Jose","Guidi",251738,"Tandil","Masculino");
        Estudiante nico=new Estudiante(42542399,"Nico","ilari",250425,"Tandil","Masculino");
        Estudiante tomi=new Estudiante(43542399,"Tomi","ilari",250435,"Tandil","Masculino");
        estudianteRepository.create(jose);
        estudianteRepository.create(nico);
        estudianteRepository.create(tomi);

        Carrera tudai = new Carrera("TUDAI",2);
        Carrera sistemas = new Carrera("Sistemas",5);
        Carrera tuari = new Carrera("TUARI",2);

        carreraRepository.create(tudai);
        carreraRepository.create(sistemas);
        carreraRepository.create(tuari);*/

        // PASO 2: Agrego estudiantes a carreras y agrego infoCarrera con estudiantes
        /*Carrera tudai = carreraRepository.getById(1);
        Carrera sistemas = carreraRepository.getById(2);
        Estudiante jose = estudianteRepository.getById(44416737);
        Estudiante nico = estudianteRepository.getById(42542399);
        Estudiante tomi = estudianteRepository.getById(43542399);
        tudai.addEstudiante(jose);
        tudai.addEstudiante(nico);
        sistemas.addEstudiante(tomi);

        carreraRepository.update(tudai);
        carreraRepository.update(sistemas);


        InfoCarrera infoCarreraJose = new InfoCarrera(tudai,false,1,null);
        InfoCarrera infoCarreraNico = new InfoCarrera(tudai,false,1,null);
        InfoCarrera infoCarreraTomi = new InfoCarrera(sistemas,true,5,LocalDate.of(2012,5,10));

        infoCarreraRepository.create(infoCarreraJose);
        infoCarreraRepository.create(infoCarreraNico);
        infoCarreraRepository.create(infoCarreraTomi);

        jose.addInfoCarrera(infoCarreraJose);
        nico.addInfoCarrera(infoCarreraNico);
        tomi.addInfoCarrera(infoCarreraTomi);

        estudianteRepository.update(jose);
        estudianteRepository.update(nico);
        estudianteRepository.update(tomi);*/

        // PASO 3: agrego una nueva carrera y un nuevo estudiante (estudiante que ya existe en otra carrera)
        // NO FUNCIONA, un alumno no puede estudiar en dos carreras
        /*Carrera tuari = carreraRepository.getById(3);
        Estudiante jose = estudianteRepository.getById(44416737);
        tuari.addEstudiante(jose);
        carreraRepository.update(tuari);
        InfoCarrera infoCarreraJoseTuari = new InfoCarrera(tuari,true,10,LocalDate.of(2012,10,12));
        infoCarreraRepository.create(infoCarreraJoseTuari);

        jose.addInfoCarrera(infoCarreraJoseTuari);
        estudianteRepository.update(jose);*/


        // Ejercicios:

        System.out.println("Estudiantes ordenados por DNI: ");
        System.out.println(estudianteRepository.getEstudianteOrderByDoc());

        System.out.println("\nEstudiante con N° Libreta: ");
        System.out.println(estudianteRepository.getEstudianteByLibreta(251738));

        System.out.println("\nEstudiante con genero: ");
        System.out.println(estudianteRepository.getEstudianteByGenero("Masculino"));

        System.out.println("\nCarreras con inscriptos ordenadas por cantidad: ");
        System.out.println(carreraRepository.getCarreraByInscriptos());

        System.out.println("\nEstudiantes de localidad que estudien carrera: ");
        Carrera tudai = carreraRepository.getById(1);
        System.out.println(estudianteRepository.getEstudianteByCarreraAndLocalidad("Tandil",tudai));

        System.out.println("\nInscriptos y graduados de cada carrera agrupadas por año");
        System.out.println(carreraRepository.getReportCarrera());




    }
}
