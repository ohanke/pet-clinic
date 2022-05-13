package com.oscarhanke.petclinic.services.springdatajpa;

import com.oscarhanke.petclinic.model.PetType;
import com.oscarhanke.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetTypeSpringDataJpaServiceTest {

    static final Long ID = 1L;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSpringDataJpaService service;

    PetType returnPetType;

    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(ID).build();
    }

    @Test
    void findAll() {
        Set<PetType> returnPetTypeSet = new HashSet<>();
        returnPetTypeSet.add(returnPetType);

        when(petTypeRepository.findAll()).thenReturn(returnPetTypeSet);

        Set<PetType> petTypes = service.findAll();

        assertNotNull(petTypes);
        assertEquals(1, petTypes.size());
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));

        PetType petType = service.findById(ID);

        assertNotNull(petType);
        assertEquals(ID, petType.getId());
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        PetType petType = service.findById(ID);

        assertNull(petType);
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().id(ID).build();

        when(petTypeRepository.save(any())).thenReturn(returnPetType);

        PetType savedPetType = service.save(petTypeToSave);

        assertNotNull(savedPetType);
        assertEquals(ID, savedPetType.getId());
        verify(petTypeRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPetType);

        verify(petTypeRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnPetType.getId());

        verify(petTypeRepository).deleteById(anyLong());
    }
}