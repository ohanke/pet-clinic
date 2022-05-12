package com.oscarhanke.petclinic.services.map;

import com.oscarhanke.petclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;
    final Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();

        petTypeMapService.save(PetType.builder().id(petTypeId).build());
    }

    @Test
    void findAll() {
        int actual = petTypeMapService.findAll().size();

        int expected = 1;

        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(petTypeId);

        int expected = 0;
        int actual = petTypeMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        petTypeMapService.delete(petTypeMapService.findById(petTypeId));

        int expected = 0;
        int actual = petTypeMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void save() {
        Long savedId = 2L;

        petTypeMapService.save(PetType.builder().id(savedId).build());

        int expected = 2;
        int actual = petTypeMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        Long expected = petTypeId;

        Long actual = petTypeMapService.findById(petTypeId).getId();

        assertEquals(expected, actual);
    }
}