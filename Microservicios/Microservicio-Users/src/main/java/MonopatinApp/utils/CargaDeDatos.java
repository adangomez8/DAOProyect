package MonopatinApp.utils;

import MonopatinApp.entities.Cuenta;
import MonopatinApp.entities.Usuario;
import MonopatinApp.repositories.CuentaRepository;
import MonopatinApp.repositories.UsuarioRepository;
import MonopatinApp.services.CuentaService;
import MonopatinApp.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Component
public class CargaDeDatos {
    @Autowired
   private final CuentaRepository cuentaRepository;
    @Autowired
    private CuentaService cuentaService;
    public CargaDeDatos(CuentaRepository n ) {
        this.cuentaRepository = n;
    }
    @PostConstruct
    public void readCsvFile() {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\joseg\\IdeaProjects\\Arquitecturas\\DAOProyect\\Microservicios\\Microservicio-Users\\src\\main\\resources\\cuenta.csv");

            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : csvParser) {
                // Accede a las columnas por nombre o índice
                String column1 = record.get("saldo");
                String column2 = record.get("fechaAlta");

               Cuenta c = new Cuenta(Double.parseDouble(column1),LocalDate.parse(column2));
                cuentaRepository.save(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
