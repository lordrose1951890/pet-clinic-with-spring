package org.lordrose.petclinicwithspring.repository;

import org.lordrose.petclinicwithspring.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
