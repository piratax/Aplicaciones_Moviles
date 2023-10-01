/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import pe.edu.upeu.asistencia.services.PeriodoService;
import pe.edu.upeu.asistencia.services.UsuarioService;

/**
 *
 * @author DELL
 */
@WebMvcTest(PeriodoController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeriodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeriodoService service;

    @MockBean
    private UsuarioService userService;

    @Autowired
    private ObjectMapper objectMapper;
    
    private String token;

    @BeforeEach
    public void setUp() {
        
        
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testListarPeriodo() throws Exception {
        //given
       /* List<PeriodoDto> listarPeriodo = new ArrayList<>();
        listarPeriodo.add(PeriodoDto.builder().nombre("2021-1").estado("Activo").build());
        listarPeriodo.add(PeriodoDto.builder().nombre("2021-2").estado("Desactivo").build());
        listarPeriodo.add(PeriodoDto.builder().nombre("2022-1").estado("Desactivo").build());
        given(service.findAll()).willReturn(listarPeriodo);
        //when
        ResultActions response = mockMvc.perform(get("/asis/periodo/list"));
        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(listarPeriodo.size())));*/
    }

}
