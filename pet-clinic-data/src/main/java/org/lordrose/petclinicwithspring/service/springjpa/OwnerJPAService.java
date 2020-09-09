package org.lordrose.petclinicwithspring.service.springjpa;

import org.lordrose.petclinicwithspring.model.Owner;
import org.lordrose.petclinicwithspring.repository.OwnerRepository;
import org.lordrose.petclinicwithspring.repository.PetRepository;
import org.lordrose.petclinicwithspring.repository.PetTypeRepository;
import org.lordrose.petclinicwithspring.service.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springJPA")
public class OwnerJPAService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerJPAService(OwnerRepository ownerRepository, PetRepository petRepository,
                           PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<Owner> findAllByLastName(String lastName) {
        return ownerRepository.findAllByLastNameContaining(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        Optional<Owner> result = ownerRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
