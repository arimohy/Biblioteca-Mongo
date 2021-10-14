package com.example.BibliotecaMongo.services;

import com.example.BibliotecaMongo.dtos.RecursoDTO;
import com.example.BibliotecaMongo.mappers.RecursoMapper;
import com.example.BibliotecaMongo.models.Recurso;
import com.example.BibliotecaMongo.repositories.RepositorioRecurso;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicioRecursoTest {
    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private ServicioRecurso servicioRecurso;

    @Test
    @DisplayName("Test findAll Success")
    void obtenerTodos() {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(0L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        Mockito.when(repositorioRecurso.findAll()).thenReturn(lista);

        var resultado = servicioRecurso.obtenerTodos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(dato1.getTitulo(), resultado.get(0).getTitulo());
        Assertions.assertEquals(dato1.getTipoRecurso(), resultado.get(0).getTipoRecurso());
        Assertions.assertEquals(dato1.getTematica(), resultado.get(0).getTematica());
        Assertions.assertEquals(dato1.getNroEjemplares(), resultado.get(0).getNroEjemplares());
        Assertions.assertEquals(dato2.getTitulo(), resultado.get(1).getTitulo());
        Assertions.assertEquals(dato2.getTipoRecurso(), resultado.get(1).getTipoRecurso());
        Assertions.assertEquals(dato2.getTematica(), resultado.get(1).getTematica());
        Assertions.assertEquals(dato2.getNroEjemplares(), resultado.get(1).getNroEjemplares());
    }

    @Test
    void obtenerPorId() {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(0L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);

        Mockito.when(repositorioRecurso.findById(Mockito.any())).thenReturn(lista.stream().findFirst());
        var resultado = servicioRecurso.obtenerPorId("1111");

        Assertions.assertEquals(dato1.getTitulo(), resultado.getTitulo());
        Assertions.assertEquals(dato1.getTipoRecurso(), resultado.getTipoRecurso());
        Assertions.assertEquals(dato1.getTematica(), resultado.getTematica());
        Assertions.assertEquals(dato1.getNroEjemplares(), resultado.getNroEjemplares());
    }

    @Test
    void crear() {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());

        var dato2 = new RecursoDTO();
        dato2.setTitulo("los gatos");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(0L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());

        Mockito.when(repositorioRecurso.save(any())).thenReturn(dato1);

        var resultado = servicioRecurso.crear(dato2);

        Assertions.assertNotNull(resultado, "el valor guardado no debe ser nulo");
        Assertions.assertEquals(dato1.getTitulo(), resultado.getTitulo(), "el nombre no corresponde");
        Assertions.assertEquals(dato1.getTipoRecurso(), resultado.getTipoRecurso(), "el rol no corresponde");
        Assertions.assertEquals(dato1.getTematica(), resultado.getTematica(), "el rol no corresponde");
        Assertions.assertEquals(dato1.getNroEjemplares(), resultado.getNroEjemplares(), "el rol no corresponde");
        Assertions.assertEquals(dato1.getNroEjemplaresPrestados(), resultado.getNroEjemplaresPrestados(), "el rol no corresponde");
        Assertions.assertEquals(dato1.getDisponible(), resultado.getDisponible(), "el rol no corresponde");
    }

    @Test
    void modificar() {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());

        var lista = new ArrayList<Recurso>();
        lista.add(dato1);

        var dato2 = new RecursoDTO();
        dato2.setId("1111");
        dato2.setTitulo("los gatos modificado");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(0L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());

        Mockito.when(repositorioRecurso.save(Mockito.any())).thenReturn(new RecursoMapper().fromDTO(dato2));
        Mockito.when(repositorioRecurso.findById(dato2.getId())).thenReturn(lista.stream().findFirst());
        var resultado = servicioRecurso.modificar(dato2);
        Assertions.assertNotNull(resultado, "el valor guardado no debe ser nulo");
        Assertions.assertEquals(dato2.getTitulo(), resultado.getTitulo(), "el nombre no corresponde");
    }

    @Test
    void consultarDisponibilidadFavorable() throws JSONException {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(2L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        String id1="1111";

        Mockito.when(repositorioRecurso.findById(dato1.getId())).thenReturn(lista.stream().findFirst());

        var resultado = servicioRecurso.consultarDisponibilidad(id1);

        JSONObject json = new JSONObject();
        json.put("mensaje", "Esta disponible el recurso");
        Assertions.assertEquals(json.toString(), resultado);

    }
    @Test
    void consultarDisponibilidadDesfavorable() throws JSONException {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(2L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(2L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        String id1="1111";

        Mockito.when(repositorioRecurso.findById(dato1.getId())).thenReturn(lista.stream().findFirst());

        var resultado = servicioRecurso.consultarDisponibilidad(id1);
        JSONObject json = new JSONObject();
        json.put("mensaje", "Este recurso no esta disponible");
        json.put("Fechadeprestamo", dato2.getFechaprestamo());
        Assertions.assertEquals(json.toString(), resultado);
    }
    @Test
    void prestarRecursoFavorable() throws JSONException {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(2L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        String id1="1111";

        var dato3 = new RecursoDTO();
        dato3.setId("1111");
        dato3.setTitulo("los gatos");
        dato3.setTipoRecurso("libro");
        dato3.setTematica("gatos");
        dato3.setNroEjemplares(2L);
        dato3.setNroEjemplaresPrestados(1L);
        dato3.setDisponible("esta disponible");
        dato3.setFechaprestamo(LocalDate.now());

        Mockito.when(repositorioRecurso.findById(dato1.getId())).thenReturn(lista.stream().findFirst());
        Mockito.when(repositorioRecurso.save(Mockito.any())).thenReturn(new RecursoMapper().fromDTO(dato3));
        var resultado = servicioRecurso.prestarRecurso(id1);
        JSONObject json = new JSONObject();
        json.put("Mensaje", "Si se pudo realizar el prestamo");
        Assertions.assertEquals(json.toString(), resultado);

    }
    @Test
    void prestarRecursoDesfavorable() throws JSONException {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(2L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(2L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        String id1="1111";

        var dato3 = new RecursoDTO();
        dato3.setId("1111");
        dato3.setTitulo("los gatos");
        dato3.setTipoRecurso("libro");
        dato3.setTematica("gatos");
        dato3.setNroEjemplares(2L);
        dato3.setNroEjemplaresPrestados(1L);
        dato3.setDisponible("esta disponible");
        dato3.setFechaprestamo(LocalDate.now());

        Mockito.when(repositorioRecurso.findById(dato1.getId())).thenReturn(lista.stream().findFirst());
        Mockito.when(repositorioRecurso.save(Mockito.any())).thenReturn(new RecursoMapper().fromDTO(dato3));
        var resultado = servicioRecurso.prestarRecurso(id1);
        JSONObject json = new JSONObject();
        json.put("Mensaje", "No hay Ejemplares suficientes para realizar el prestamo");
        Assertions.assertEquals(json.toString(), resultado);

    }
    @Test
    void devolverRecursoFavorable() throws JSONException {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(1L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(2L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        String id1="1111";

        var dato3 = new RecursoDTO();
        dato3.setId("1111");
        dato3.setTitulo("los gatos");
        dato3.setTipoRecurso("libro");
        dato3.setTematica("gatos");
        dato3.setNroEjemplares(2L);
        dato3.setNroEjemplaresPrestados(1L);
        dato3.setDisponible("esta disponible");
        dato3.setFechaprestamo(LocalDate.now());

        Mockito.when(repositorioRecurso.findById(dato1.getId())).thenReturn(lista.stream().findFirst());
        Mockito.when(repositorioRecurso.save(Mockito.any())).thenReturn(new RecursoMapper().fromDTO(dato3));
        var resultado = servicioRecurso.devolverRecurso(id1);
        JSONObject json = new JSONObject();
        json.put("Mensaje", "El recurso fue devuelto satisfactoriamente");
        Assertions.assertEquals(json.toString(), resultado);

    }
    @Test
    void devolverRecursoDesfavorable() throws JSONException {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("libro");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(2L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        String id1="1111";

        var dato3 = new RecursoDTO();
        dato3.setId("1111");
        dato3.setTitulo("los gatos");
        dato3.setTipoRecurso("libro");
        dato3.setTematica("gatos");
        dato3.setNroEjemplares(2L);
        dato3.setNroEjemplaresPrestados(1L);
        dato3.setDisponible("esta disponible");
        dato3.setFechaprestamo(LocalDate.now());

        Mockito.when(repositorioRecurso.findById(dato1.getId())).thenReturn(lista.stream().findFirst());
        Mockito.when(repositorioRecurso.save(Mockito.any())).thenReturn(new RecursoMapper().fromDTO(dato3));
        var resultado = servicioRecurso.devolverRecurso(id1);
        JSONObject json = new JSONObject();
        json.put("Mensaje", "Recurso no puede ser devuelto por que no fue prestado");
        Assertions.assertEquals(json.toString(), resultado);

    }
    @Test
    void recomendarTipoRecurso(){
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("revista");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(0L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());

        var dato3 = new Recurso();
        dato3.setId("1111");
        dato3.setTitulo("yhomi");
        dato3.setTipoRecurso("libro");
        dato3.setTematica("yhomi");
        dato3.setNroEjemplares(2L);
        dato3.setNroEjemplaresPrestados(0L);
        dato3.setDisponible("esta disponible");
        dato3.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        lista.add(dato3);

        Mockito.when(repositorioRecurso.findAll()).thenReturn(lista);
        var resultado = servicioRecurso.recomendarTipoRecurso("libro");

        Assertions.assertEquals(dato1.getTitulo(), resultado.get(0).getTitulo());
        Assertions.assertEquals(dato3.getTitulo(), resultado.get(1).getTitulo());
    }
    @Test
    void recomendarTematica(){
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("los gatos");
        dato1.setTipoRecurso("libro");
        dato1.setTematica("gatos");
        dato1.setNroEjemplares(2L);
        dato1.setNroEjemplaresPrestados(0L);
        dato1.setDisponible("esta disponible");
        dato1.setFechaprestamo(LocalDate.now());
        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setTitulo("los perros");
        dato2.setTipoRecurso("revista");
        dato2.setTematica("gatos");
        dato2.setNroEjemplares(2L);
        dato2.setNroEjemplaresPrestados(0L);
        dato2.setDisponible("esta disponible");
        dato2.setFechaprestamo(LocalDate.now());

        var dato3 = new Recurso();
        dato3.setId("1111");
        dato3.setTitulo("yhomi");
        dato3.setTipoRecurso("libro");
        dato3.setTematica("yhomi");
        dato3.setNroEjemplares(2L);
        dato3.setNroEjemplaresPrestados(0L);
        dato3.setDisponible("esta disponible");
        dato3.setFechaprestamo(LocalDate.now());
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        lista.add(dato3);

        Mockito.when(repositorioRecurso.findAll()).thenReturn(lista);
        var resultado = servicioRecurso.recomendarTematica("gatos");

        Assertions.assertEquals(dato1.getTitulo(), resultado.get(0).getTitulo());
        Assertions.assertEquals(dato2.getTitulo(), resultado.get(1).getTitulo());
    }
}