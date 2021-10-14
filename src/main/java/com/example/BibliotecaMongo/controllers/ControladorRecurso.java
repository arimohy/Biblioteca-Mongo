package com.example.BibliotecaMongo.controllers;

import com.example.BibliotecaMongo.dtos.RecursoDTO;
import com.example.BibliotecaMongo.services.ServicioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class ControladorRecurso {
    @Autowired
    ServicioRecurso servicioRecurso;

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RecursoDTO>> findAll() {
        return new ResponseEntity(servicioRecurso.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<RecursoDTO> create(@RequestBody RecursoDTO recursoDTO) {
        return new ResponseEntity(servicioRecurso.crear(recursoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<RecursoDTO> update(@RequestBody RecursoDTO recursoDTO) {
        if (recursoDTO.getId() != null) {
            return new ResponseEntity(servicioRecurso.modificar(recursoDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioRecurso.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    //otras consultas si esta disponible
    @GetMapping("disponible/{id}")
    public ResponseEntity consultarDisponiblibilidad(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.consultarDisponibilidad(id), HttpStatus.OK);
    }
    @GetMapping("prestar/{id}")
    public ResponseEntity prestarRecurso(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.prestarRecurso(id), HttpStatus.OK);
    }
    @GetMapping("recomendar/{tiporecurso}/{tematica}")
    public ResponseEntity<List<RecursoDTO>> recomendarTipoRecursoTematica(@PathVariable("tiporecurso") String tiporecurso,
                                                            @PathVariable("tematica") String tematica) {
        return new ResponseEntity(servicioRecurso.recomendarTipoRecursoTematica(tiporecurso,tematica), HttpStatus.OK);
    }
    @GetMapping("recomendar/tiporecurso/{tiporecurso}")
    public ResponseEntity<List<RecursoDTO>> recomendarTipoRecurso(@PathVariable("tiporecurso") String tiporecurso){
        return new ResponseEntity(servicioRecurso.recomendarTipoRecurso(tiporecurso), HttpStatus.OK);
    }
    @GetMapping("recomendar/tematica/{tematica}")
    public ResponseEntity<List<RecursoDTO>> recomendarTematica(@PathVariable("tematica") String tematica){
        return new ResponseEntity(servicioRecurso.recomendarTematica(tematica), HttpStatus.OK);
    }
    @GetMapping("devolver/{id}")
    public ResponseEntity devolverRecurso(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.devolverRecurso(id), HttpStatus.OK);
    }
}
