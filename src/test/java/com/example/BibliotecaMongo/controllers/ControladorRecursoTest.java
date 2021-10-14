package com.example.BibliotecaMongo.controllers;

import com.example.BibliotecaMongo.dtos.RecursoDTO;
import com.example.BibliotecaMongo.services.ServicioRecurso;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControladorRecursoTest {
    @MockBean
    private ServicioRecurso servicioRecurso;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /recurso success")
    public void findAll() throws Exception {
        //setup mock service
        var dato1 = new RecursoDTO();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());

        var dato2 = new RecursoDTO();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(0L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<RecursoDTO>();
        lista.add(dato1);
        lista.add(dato2);
        Mockito.when(servicioRecurso.obtenerTodos()).thenReturn(lista);

        //execute Get request
        mockMvc.perform(get("/recursos"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value("1111"))
                .andExpect(jsonPath("$[0].titulo").value("los gatos"))
                .andExpect(jsonPath("$[0].tipoRecurso").value("libro"))
                .andExpect(jsonPath("$[0].tematica").value("gatos"))
                .andExpect(jsonPath("$[0].nroEjemplares").value(2))
                .andExpect(jsonPath("$[0].nroEjemplaresPrestados").value(0))
                .andExpect(jsonPath("$[1].id").value("2222"))
                .andExpect(jsonPath("$[1].titulo").value("los perros"))
                .andExpect(jsonPath("$[1].tipoRecurso").value("libro"))
                .andExpect(jsonPath("$[1].tematica").value("gatos"))
                .andExpect(jsonPath("$[1].nroEjemplares").value(2))
                .andExpect(jsonPath("$[1].nroEjemplaresPrestados").value(0));
    }

    @Test
    @DisplayName("POST /recursos/crear success")
    public void create() throws Exception {
        // Setup our mocked service
        var datoPost = new RecursoDTO();
        datoPost.setTitulo("los gatos");
        datoPost.setTipoRecurso("libro");
        datoPost.setTematica("gatos");
        datoPost.setNroEjemplares(2L);
        datoPost.setNroEjemplaresPrestados(0L);
        datoPost.setDisponible("esta disponible");


        var datoReturn = new RecursoDTO();
        datoReturn.setId("2222");
        datoReturn.setTitulo("los perros");
        datoReturn.setTipoRecurso("libro");
        datoReturn.setTematica("gatos");
        datoReturn.setNroEjemplares(2L);
        datoReturn.setNroEjemplaresPrestados(0L);
        datoReturn.setDisponible("esta disponible");

        Mockito.when(servicioRecurso.crear(Mockito.any())).thenReturn(datoReturn);

        // Execute the POST request
        mockMvc.perform(post("/recursos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(datoPost)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id").value("2222"))
                .andExpect(jsonPath("$.titulo").value("los perros"))
                .andExpect(jsonPath("$.tipoRecurso").value("libro"))
                .andExpect(jsonPath("$.tematica").value("gatos"))
                .andExpect(jsonPath("$.nroEjemplares").value(2))
                .andExpect(jsonPath("$.nroEjemplaresPrestados").value(0));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}