package Microservicioadmin.Controllers;

import Microservicioadmin.Dto.DtoCuenta;
import Microservicioadmin.Dto.DtoMonopatin;
import Microservicioadmin.Dto.DtoParada;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
class AdminControllerTest {

    private final static String BASE_URL="/api/admin";
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void updatePrecio() throws Exception {
        MvcResult mockResult=mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL+"/viaje/tarifa/{precio}/{fecha}").queryParam("precio","10").queryParam("fecha","22/10/2023").contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200,mockResult.getResponse().getStatus());
    }

    @Test
    void getEstadoMonopatin() throws Exception {
        MvcResult mockResult=mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/monopatin/reporte").accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200,mockResult.getResponse().getStatus());
    }

    @Test
    void getRecaudacion() throws Exception {
        MvcResult mockResult=mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/viaje/recaudacion").accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200,mockResult.getResponse().getStatus());
    }

    @Test
    void saveMonopatin() throws Exception {
        DtoMonopatin monopatin=crearMonopatin();
        MvcResult mockResult=mockMvc.perform((MockMvcRequestBuilders.post(BASE_URL+"/monopatin").contentType(MediaType.APPLICATION_JSON_VALUE).content(parseToJson(monopatin)))).andReturn();
        assertEquals(201,mockResult.getResponse().getStatus());
    }

    @Test
    void saveParada() throws Exception {
        DtoParada parada=crearParada();
        MvcResult mockResult=mockMvc.perform((MockMvcRequestBuilders.post(BASE_URL+"/parada").contentType(MediaType.APPLICATION_JSON_VALUE).content(parseToJson(parada)))).andReturn();
        assertEquals(201,mockResult.getResponse().getStatus());
    }

    @Test
    void saveCuenta() throws Exception{
        DtoCuenta cuenta=crearCuenta();
        MvcResult mockResult=mockMvc.perform((MockMvcRequestBuilders.post(BASE_URL+"/cuenta").contentType(MediaType.APPLICATION_JSON_VALUE).content(parseToJson(cuenta)))).andReturn();
        assertEquals(201,mockResult.getResponse().getStatus());
    }

    @Test
    void deleteMonopatin() throws Exception {
        MvcResult mockResult=mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL+"/monopatin/{id}").queryParam("id","1").contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200,mockResult.getResponse().getStatus());
    }

    @Test
    void deleteParada() throws Exception {
        MvcResult mockResult=mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL+"/parada/{id}").queryParam("id","1").contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200,mockResult.getResponse().getStatus());
    }

    @Test
    void deleteCuenta() throws Exception {
        MvcResult mockResult=mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL+"/cuenta/{id}").queryParam("id","1").contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200,mockResult.getResponse().getStatus());
    }

    @Test
    void getMonopatinByYear() {
    }

    @Test
    void login() {
    }

    @Test
    void register() {
    }


    private DtoCuenta crearCuenta(){
        DtoCuenta cuenta=new DtoCuenta();
        cuenta.setIdCuenta(1);;
        cuenta.setFechaAlta(LocalDate.now());
        cuenta.setSaldo(30.0);
        return cuenta;
    }
    private DtoParada crearParada(){
        DtoParada parada=new DtoParada();
        parada.setId(1);
        parada.setLatitud("300");
        parada.setLongitud("3000");
        parada.setMonopatines(3);
        return parada;
    }

    private DtoMonopatin crearMonopatin(){
        DtoMonopatin monopatin=new DtoMonopatin();
        monopatin.setId(1);
        monopatin.setEstado("Disponible");
        monopatin.setKilometros(5000);
        monopatin.setLatitud("30");
        monopatin.setLongitud("333");
        monopatin.setIdParada(3);
        monopatin.setTiempoEnPausa(2);
        monopatin.setTiempoEnUso(10);
        return monopatin;
    }

    private String parseToJson(Object o) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(o);
    }
}