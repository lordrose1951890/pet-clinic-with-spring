package org.lordrose.petclinicwithspring.repository;

import org.lordrose.petclinicwithspring.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
