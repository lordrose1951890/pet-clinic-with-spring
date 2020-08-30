package org.lordrose.petclinicwithspring.service.springjpa;

import org.lordrose.petclinicwithspring.model.PetType;
import org.lordrose.petclinicwithspring.repository.PetTypeRepository;
import org.lordrose.petclinicwithspring.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springJPA")
public class PetTypeServiceJPA implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceJPA(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> types = new HashSet<>();
        petTypeRepository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public PetType findById(Long id) {
        Optional<PetType> result = petTypeRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
