package org.lordrose.petclinicwithspring.repository;

import org.lordrose.petclinicwithspring.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
