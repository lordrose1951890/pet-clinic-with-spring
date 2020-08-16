package org.lordrose.petclinicwithspring.service.map;

import org.lordrose.petclinicwithspring.model.Pet;
import org.lordrose.petclinicwithspring.service.CrudService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long>
        implements CrudService<Pet, Long> {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
