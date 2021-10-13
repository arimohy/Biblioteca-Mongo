package com.example.BibliotecaMongo.mappers;

import com.example.BibliotecaMongo.dtos.RecursoDTO;
import com.example.BibliotecaMongo.models.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {
    public Recurso fromDTO(RecursoDTO dto) {
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setTitulo(dto.getTitulo());
        recurso.setTipoRecurso(dto.getTipoRecurso());
        recurso.setTematica(dto.getTematica());
        recurso.setDisponible(dto.getDisponible());
        recurso.setFechaprestamo(dto.getFechaprestamo());
        recurso.setNroEjemplares(dto.getNroEjemplares());
        recurso.setNroEjemplaresPrestados(dto.getNroEjemplaresPrestados());
        return recurso;
    }

    public RecursoDTO fromCollection(Recurso collection) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setTitulo(collection.getTitulo());
        recursoDTO.setTipoRecurso(collection.getTipoRecurso());
        recursoDTO.setTematica(collection.getTematica());
        recursoDTO.setDisponible(collection.getDisponible());
        recursoDTO.setFechaprestamo(collection.getFechaprestamo());
        recursoDTO.setNroEjemplares(collection.getNroEjemplares());
        recursoDTO.setNroEjemplaresPrestados(collection.getNroEjemplaresPrestados());
        return recursoDTO;
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection) {
        if (collection == null) {
            return null;

        }
        List<RecursoDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Recurso recurso = (Recurso) listTracks.next();
            list.add(fromCollection(recurso));
        }

        return list;
    }
}
