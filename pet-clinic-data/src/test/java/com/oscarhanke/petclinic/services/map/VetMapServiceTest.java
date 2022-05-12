package com.oscarhanke.petclinic.services.map;

import com.oscarhanke.petclinic.model.Specialty;
import com.oscarhanke.petclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VetMapServiceTest {

    VetMapService vetMapService;
    final Long id = 1L;

    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialtyMapService());

        Specialty specialty = Specialty.builder().id(id).build();
        Set<Specialty> specialties = Set.of(specialty);
        Vet vet = Vet.builder().id(id).specialities(specialties).build();

        vetMapService.save(vet);
    }

    @Test
    void findAll() {
        int expected = 1;

        int actual = vetMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        int expected = 0;

        vetMapService.deleteById(id);

        int actual = vetMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        int expected = 0;

        vetMapService.delete(vetMapService.findById(id));

        int actual = vetMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void save() {
        int expected = 2;
        Long savedId = 2L;

        Specialty specialty = Specialty.builder().id(savedId).build();
        Set<Specialty> specialties = Set.of(specialty);
        Vet vet = Vet.builder().id(savedId).specialities(specialties).build();

        vetMapService.save(vet);
        int actual = vetMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        Long expected = id;

        Long actual = vetMapService.findById(id).getId();

        assertEquals(expected, actual);
    }
}