package org.lordrose.petclinicwithspring.service.springjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lordrose.petclinicwithspring.model.Owner;
import org.lordrose.petclinicwithspring.repository.OwnerRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJPAServiceTest {

    public static final String LAST_NAME = "Josh";
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJPAService ownerService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        Set<Owner> returnOwners = new HashSet<>();
        returnOwners.add(Owner.builder().id(1L).lastName(LAST_NAME).build());

        when(ownerRepository.findAllByLastNameContaining(any())).thenReturn(returnOwners);

        Set<Owner> result = ownerService.findAllByLastName(LAST_NAME);
        Optional<Owner> josh = result.stream().findFirst();

        assertTrue(josh.isPresent());
        assertEquals(LAST_NAME, result.stream().findFirst().get().getLastName());
        verify(ownerRepository).findAllByLastNameContaining(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwners = new HashSet<>();
        returnOwners.add(Owner.builder().id(1L).build());
        returnOwners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwners);

        Set<Owner> result = ownerService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void findAllNotFound() {
        Set<Owner> returnOwners = new HashSet<>();

        when(ownerRepository.findAll()).thenReturn(returnOwners);

        Set<Owner> result = ownerService.findAll();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void findById() {
        Optional<Owner> returnOwner = Optional.of(Owner.builder().id(1L).build());

        when(ownerRepository.findById(eq(1L))).thenReturn(returnOwner);

        Owner result = ownerService.findById(1L);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void findByIdNotFound() {
        Optional<Owner> returnOwner = Optional.empty();

        when(ownerRepository.findById(eq(1L))).thenReturn(returnOwner);

        Owner result = ownerService.findById(1L);

        assertNull(result);
    }

    @Test
    void save() {
        Owner returnOwner = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = ownerService.save(returnOwner);

        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        ownerService.delete(Owner.builder().id(1L).build());

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(1L);

        verify(ownerRepository).deleteById(eq(1L));
    }
}
