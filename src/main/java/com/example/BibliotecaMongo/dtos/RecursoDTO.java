package com.example.BibliotecaMongo.dtos;

import java.time.LocalDate;

public class RecursoDTO {
    private String id;
    private String titulo;
    private String tipoRecurso;
    private String tematica;
    private String disponible;
    private LocalDate fechaprestamo;
    private Long nroEjemplares;
    private Long nroEjemplaresPrestados;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public LocalDate getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(LocalDate fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Long getNroEjemplares() {
        return nroEjemplares;
    }

    public void setNroEjemplares(Long nroEjemplares) {
        this.nroEjemplares = nroEjemplares;
    }

    public Long getNroEjemplaresPrestados() {
        return nroEjemplaresPrestados;
    }

    public void setNroEjemplaresPrestados(Long nroEjemplaresPrestados) {
        this.nroEjemplaresPrestados = nroEjemplaresPrestados;
    }
}
