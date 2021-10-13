package com.example.BibliotecaMongo.repositories;

import com.example.BibliotecaMongo.models.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRecurso extends MongoRepository<Recurso, String> {
}
