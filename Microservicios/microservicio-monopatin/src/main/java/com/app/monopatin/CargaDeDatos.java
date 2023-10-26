package com.app.monopatin;

import com.app.monopatin.entitys.Monopatin;
import com.app.monopatin.services.MonopatinService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.util.Map;

@Component
public class CargaDeDatos {

    private final MonopatinService monopatinService;

    public CargaDeDatos(MonopatinService monopatinService) {
        this.monopatinService = monopatinService;
    }

    public void readCSV() {
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("C:/Users/Usuario/IdeaProjects/DAOProyect/Microservicios/microservicio-monopatin/src/main/java/com/app/monopatin/csv/monopatines.csv"));

            Map<String, Integer> header = parser.getHeaderMap();
            System.out.println("Header: " + header);

            for (CSVRecord row : parser) {
                int idMonopatin = Integer.parseInt(row.get("id"));
                Monopatin aux = new Monopatin(idMonopatin, row.get("estado"), row.get("longitud"), row.get("latitud"));
                //monopatinService.create(aux);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}