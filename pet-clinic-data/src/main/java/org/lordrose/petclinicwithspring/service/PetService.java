package org.lordrose.petclinicwithspring.service;

import org.lordrose.petclinicwithspring.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
