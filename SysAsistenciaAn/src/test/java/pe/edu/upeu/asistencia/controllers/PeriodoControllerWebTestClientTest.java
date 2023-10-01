/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import org.springframework.test.web.reactive.server.WebTestClient;
import pe.edu.upeu.asistencia.dtos.CredencialesDto;

/**
 *
 * @author DELL
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class PeriodoControllerWebTestClientTest {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private WebTestClient webTestClient;
    
    private String token;

    @BeforeEach
    public void setUp() {
        System.out.println("Puerto:"+this.port);
        webTestClient.post()
                .uri("/asis/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new CredencialesDto("davidmp@upeu.edu.pe", "Da12345*".toCharArray()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                
                .value(tokenx -> {
            try {
                JSONObject jsonObj = new JSONObject(tokenx);
                token=jsonObj.getString("token");
            } catch (JSONException ex) {
                Logger.getLogger(PeriodoControllerWebTestClientTest.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
                });
        System.out.println("VerWeb:" + token);
    }

    @AfterEach
    public void tearDown() {
    }

    /*@Test
    @Order(1)
    public void testListarPeriodo() {
        webTestClient.get().uri("http://localhost:"+this.port+"/asis/periodo/list")
                .header("Authorization", "Bearer "+token)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[1].nombre").isEqualTo("2023-2")
                .jsonPath("$[1].estado").isEqualTo("Activo")
                .jsonPath("$").isArray()
                .jsonPath("$").value(Matchers.hasSize(5));
    }*/

}
