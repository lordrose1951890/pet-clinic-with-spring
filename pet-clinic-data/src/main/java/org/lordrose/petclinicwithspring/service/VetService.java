package org.lordrose.petclinicwithspring.service;

import org.lordrose.petclinicwithspring.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();

}
