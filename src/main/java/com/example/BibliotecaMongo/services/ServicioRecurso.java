package com.example.BibliotecaMongo.services;

import com.example.BibliotecaMongo.dtos.RecursoDTO;
import com.example.BibliotecaMongo.mappers.RecursoMapper;
import com.example.BibliotecaMongo.models.Recurso;
import com.example.BibliotecaMongo.repositories.RepositorioRecurso;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioRecurso {
    @Autowired
    RepositorioRecurso repositorioRecurso;
    RecursoMapper mapper = new RecursoMapper();
    //crud
    public List<RecursoDTO> obtenerTodos() {
        List<Recurso> recursos = (List<Recurso>) repositorioRecurso.findAll();
        return mapper.fromCollectionList(recursos);
    }
    public RecursoDTO obtenerPorId(String id) {
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(recurso);
    }
    public RecursoDTO crear(RecursoDTO recursoDTO) {
        Recurso recurso = mapper.fromDTO(recursoDTO);
        return mapper.fromCollection(repositorioRecurso.save(recurso));
    }
    public RecursoDTO modificar(RecursoDTO recursoDTO) {
        Recurso recurso = mapper.fromDTO(recursoDTO);
        repositorioRecurso.findById(recurso.getId()).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(repositorioRecurso.save(recurso));
    }
    public void borrar(String id) {
        repositorioRecurso.deleteById(id);
    }
    //consultas
    public JSONObject consultarDisponibilidad(String id){
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        if(recurso.getNroEjemplaresPrestados()<recurso.getNroEjemplares()){
            JSONObject json = new JSONObject();
            json.put("Mensaje", "Esta disponible el recurso");
            return json;
        }
        else {
            JSONObject json = new JSONObject();
            json.put("Mensaje", "Esta disponible el recurso");
            json.put("Fecha de prestamo", recurso.getFechaprestamo());
            return json;
        }
    }
    public JSONObject prestarRecurso(String id) {
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        if(recurso.getNroEjemplaresPrestados()<recurso.getNroEjemplares()) {
            recurso.setNroEjemplaresPrestados(recurso.getNroEjemplaresPrestados() + 1);
            LocalDate fecha=LocalDate.now();
            recurso.setFechaprestamo(fecha);
            JSONObject json = new JSONObject();
            json.put("Mensaje", "Si se pudo realizar el prestamo");
            return  json;
        }
        else {
            JSONObject json = new JSONObject();
            json.put("Mensaje", "No hay Ejemplares suficientes para realizar el prestamo");
            return json;
        }
    }
    public List<RecursoDTO> recomendarTipoRecurso(String tiporecurso) {
        List<Recurso> recursos = (List<Recurso>) repositorioRecurso.findAll()
                .stream()
                .filter(recurso -> recurso.getTipoRecurso().equals(tiporecurso))
                .collect(Collectors.toList());
        return mapper.fromCollectionList(recursos);
    }
    public List<RecursoDTO> recomendarTematica(String tematica) {
        List<Recurso> recursos = (List<Recurso>) repositorioRecurso.findAll().stream()
                .filter(recurso -> recurso.getTematica().equals(tematica))
                .collect(Collectors.toList());
        return mapper.fromCollectionList(recursos);
    }
    public List<RecursoDTO> recomendarTipoRecursoTematica(String tiporecurso,String tematica) {
        List<Recurso> recursos = (List<Recurso>) repositorioRecurso.findAll().stream()
                .filter(recurso -> recurso.getTematica().equals(tematica))
                .filter(recurso -> recurso.getTipoRecurso().equals(tiporecurso))
                .collect(Collectors.toList());
        return mapper.fromCollectionList(recursos);
    }
    public JSONObject devolverRecurso(String id) {
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        if(recurso.getNroEjemplaresPrestados()>=1) {
            recurso.setNroEjemplaresPrestados(recurso.getNroEjemplaresPrestados() - 1);
            JSONObject json = new JSONObject();
            json.put("Mensaje", "El recurso fue devuelto satisfactoriamente");
            return  json;
        }
        else {
            JSONObject json = new JSONObject();
            json.put("Mensaje", "Recurso no puede ser devuelto por que no fue prestado");
            return json;
        }
    }
}
