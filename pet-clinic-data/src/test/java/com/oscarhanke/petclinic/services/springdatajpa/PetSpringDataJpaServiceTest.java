package com.oscarhanke.petclinic.services.springdatajpa;

import com.oscarhanke.petclinic.model.Pet;
import com.oscarhanke.repositories.PetRepository;
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
class PetSpringDataJpaServiceTest {

    public static final Long ID = 1L;

    Pet returnPet;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSpringDataJpaService service;



    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(ID).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPetSet = new HashSet<>();
        returnPetSet.add(returnPet);

        when(petRepository.findAll()).thenReturn(returnPetSet);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(1, pets.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet = service.findById(ID);

        assertNotNull(pet);
        assertEquals(ID, pet.getId());
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet pet = service.findById(ID);

        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(2L).build();

        when(petRepository.save(any())).thenReturn(returnPet);

        Pet savedPet = service.save(petToSave);

        assertNotNull(savedPet);

        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPet);

        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(petRepository).deleteById(anyLong());
    }
}