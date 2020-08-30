package org.lordrose.petclinicwithspring.repository;

import org.lordrose.petclinicwithspring.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
