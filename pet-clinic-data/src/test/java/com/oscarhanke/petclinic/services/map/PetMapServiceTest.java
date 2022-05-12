package com.oscarhanke.petclinic.services.map;

import com.oscarhanke.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetMapServiceTest {

    PetMapService petMapService;
    final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();

        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();

        int expected = 1;

        assertEquals(expected, petSet.size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);

        int expected = 0;

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(petId));

        int expected = 0;

        assertEquals(expected, petMapService.findAll().size());
    }

    @Test
    void save() {
        Long savedId = 2L;
        Pet savedPet = petMapService.save(Pet.builder().id(savedId).build());

        assertEquals(savedId, savedPet.getId());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);
        assertEquals(petId, pet.getId());
    }
}