package org.lordrose.petclinicwithspring.repository;

import org.lordrose.petclinicwithspring.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
