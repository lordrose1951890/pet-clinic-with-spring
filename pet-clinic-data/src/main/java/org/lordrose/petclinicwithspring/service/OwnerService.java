package org.lordrose.petclinicwithspring.service;

import org.lordrose.petclinicwithspring.model.Owner;

import java.util.Set;

public interface OwnerService {

    Set<Owner> findByLastName(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
