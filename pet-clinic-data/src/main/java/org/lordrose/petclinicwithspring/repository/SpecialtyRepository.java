package org.lordrose.petclinicwithspring.repository;

import org.lordrose.petclinicwithspring.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
