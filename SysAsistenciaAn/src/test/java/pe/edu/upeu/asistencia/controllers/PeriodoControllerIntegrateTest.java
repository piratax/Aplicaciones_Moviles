/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.web.server.LocalServerPort;


import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import pe.edu.upeu.asistencia.dtos.CredencialesDto;
import org.apache.http.HttpStatus;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 *
 * @author DELL
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class PeriodoControllerIntegrateTest {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeriodoService periodoService;*/

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;
    
    private String token;

    @BeforeEach
    public void setUp() {
        RestAssured.port = this.port;

        token = given()
                .contentType(ContentType.JSON)
                .body(new CredencialesDto("davidmp@upeu.edu.pe", "Da12345*".toCharArray()))
                .when().post("/asis/login")
                .andReturn().jsonPath().getString("token");
        System.out.println("Ver:" + token);

    }

    @Test
    public void testListPeriodo() throws Exception {
        //@formatter:off
        /*given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer "+token)
                .when()
                .get("/asis/periodo/list")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);*/
        //@formatter:on

        /*//given
        List<PeriodoDto> listarPeriodo = new ArrayList<>();
        listarPeriodo.add(PeriodoDto.builder().nombre("2021-1").estado("Activo").build());
        listarPeriodo.add(PeriodoDto.builder().nombre("2021-2").estado("Desactivo").build());
        listarPeriodo.add(PeriodoDto.builder().nombre("2022-1").estado("Desactivo").build());
        given(periodoService.findAll()).willReturn(listarPeriodo);
        //when
        ResultActions response = mockMvc.perform(get("/asis/periodo/list"));
        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(listarPeriodo.size())));*/
    }
    /*
    @Test
    public void testCreatePeriodo() {
        System.out.println("createPeriodo");
        PeriodoDto.PeriodoCrearDto periodo = null;
        PeriodoController instance = new PeriodoController();
        ResponseEntity<PeriodoDto> expResult = null;
        ResponseEntity<PeriodoDto> result = instance.createPeriodo(periodo);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPeriodoById() {
        System.out.println("getPeriodoById");
        Long id = null;
        PeriodoController instance = new PeriodoController();
        ResponseEntity<PeriodoDto> expResult = null;
        ResponseEntity<PeriodoDto> result = instance.getPeriodoById(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeletePeriodo() {
        System.out.println("deletePeriodo");
        Long id = null;
        PeriodoController instance = new PeriodoController();
        ResponseEntity<Map<String, Boolean>> expResult = null;
        ResponseEntity<Map<String, Boolean>> result = instance.deletePeriodo(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdatePeriodo() {
        System.out.println("updatePeriodo");
        Long id = null;
        PeriodoDto periodoDetails = null;
        PeriodoController instance = new PeriodoController();
        ResponseEntity<PeriodoDto> expResult = null;
        ResponseEntity<PeriodoDto> result = instance.updatePeriodo(id, periodoDetails);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }*/
}
