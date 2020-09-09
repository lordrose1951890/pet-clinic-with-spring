package org.lordrose.petclinicwithspring.service;

import org.lordrose.petclinicwithspring.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Set<Owner> findAllByLastName(String lastName);

}
