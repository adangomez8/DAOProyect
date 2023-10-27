package com.app.monopatin;

import com.app.monopatin.services.MonopatinService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroservicioMonopatinApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioMonopatinApplication.class, args);

        MonopatinService monopatinService = new MonopatinService();
//        CargaDeDatos  cg = new CargaDeDatos(monopatinService);
//        cg.readCSV();
    }

}
