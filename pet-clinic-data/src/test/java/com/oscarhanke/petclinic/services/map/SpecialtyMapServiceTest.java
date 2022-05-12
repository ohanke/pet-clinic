package com.oscarhanke.petclinic.services.map;

import com.oscarhanke.petclinic.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialtyMapServiceTest {

    SpecialtyMapService specialtyMapService;
    final Long specialityId = 1L;

    @BeforeEach
    void setUp() {
        specialtyMapService = new SpecialtyMapService();

        specialtyMapService.save(Specialty.builder().id(specialityId).build());
    }

    @Test
    void findAll() {
        int expected = 1;

        int actual = specialtyMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        int expected = 0;

        specialtyMapService.deleteById(specialityId);

        int actual = specialtyMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        int expected = 0;

        specialtyMapService.delete(specialtyMapService.findById(specialityId));

        int actual = specialtyMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void save() {
        Long savedId = 2L;
        int expected = 2;

        specialtyMapService.save(Specialty.builder().id(savedId).build());

        int actual = specialtyMapService.findAll().size();

        assertEquals(expected, actual);

    }

    @Test
    void findById() {
        Long expected = specialityId;

        Long actual = specialtyMapService.findById(specialityId).getId();

        assertEquals(expected, actual);
    }
}