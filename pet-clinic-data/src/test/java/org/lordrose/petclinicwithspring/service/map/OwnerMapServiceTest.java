package org.lordrose.petclinicwithspring.service.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lordrose.petclinicwithspring.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String josh = "Josh";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(
                new PetTypeMapService(), new PetMapService()
        );

        ownerMapService.save(
                Owner.builder()
                        .id(ownerId).lastName(josh)
                        .build()
        );
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveProvidedId() {
        Owner owner = Owner.builder().id(2L).build();

        Owner savedOwner = ownerMapService.save(owner);

        assertEquals(2L, savedOwner.getId());
    }

    @Test
    void saveUnprovidedId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        assertEquals(1, ownerMapService.findAll().size());

        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        assertEquals(1, ownerMapService.findAll().size());

        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Set<Owner> result = ownerMapService.findAllByLastName(josh);

        assertEquals(1, result.size());
        assertEquals(josh, result.stream().findFirst().get().getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Set<Owner> result = ownerMapService.findAllByLastName("foo");

        assertEquals(0, result.size());
    }
}
