package Microservicioadmin.Controllers;

import Microservicioadmin.Dto.DtoMonopatin;
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
    void updatePrecio() {
    }

    @Test
    void getEstadoMonopatin() throws Exception {
        MvcResult mockResult=mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/monopatin/reporte").accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(400,mockResult.getResponse().getStatus());
    }

    @Test
    void getRecaudacion() {
    }

    @Test
    void saveMonopatin() throws Exception {
        DtoMonopatin monopatin=new DtoMonopatin();
        monopatin.setEstado("EnUso");
        monopatin.setKilometros(30);
        monopatin.setLatitud("3000n");
        monopatin.setLongitud("333333s");
        monopatin.setIdParada(3);
        monopatin.setTiempoEnPausa(2);
        monopatin.setTiempoEnUso(10);
        MvcResult mockResult=mockMvc.perform((MockMvcRequestBuilders.post(BASE_URL+"/monopatin").accept((MediaType.APPLICATION_JSON)).contentType(MediaType.APPLICATION_JSON_VALUE).content(parseToJson(monopatin)))).andReturn();
        assertEquals(201,mockResult.getResponse().getStatus());
    }

    @Test
    void saveParada() {
    }

    @Test
    void saveCuenta() {
    }

    @Test
    void deleteMonopatin() {
    }

    @Test
    void deleteParada() {
    }

    @Test
    void deleteCuenta() {
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

    private String parseToJson(Object o) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(o);
    }
}