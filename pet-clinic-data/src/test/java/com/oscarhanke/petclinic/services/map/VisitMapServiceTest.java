package com.oscarhanke.petclinic.services.map;

import com.oscarhanke.petclinic.model.Owner;
import com.oscarhanke.petclinic.model.Pet;
import com.oscarhanke.petclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitMapServiceTest {

    VisitMapService visitMapService;
    final Long id = 1L;

    @BeforeEach
    void setUp() {
        visitMapService = new VisitMapService();

        Owner owner = Owner.builder().id(id).build();
        Pet pet = Pet.builder().id(id).owner(owner).build();
        Visit visit = Visit.builder().id(id).pet(pet).build();

        visitMapService.save(visit);
    }

    @Test
    void findAll() {
        int expected = 1;

        int actual = visitMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        int expected = 0;

        visitMapService.deleteById(id);
        int actual = visitMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        int expected = 0;

        visitMapService.delete(visitMapService.findById(id));
        int actual = visitMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void save() {
        int expected = 2;
        Long savedId = 2L;

        Owner owner = Owner.builder().id(savedId).build();
        Pet pet = Pet.builder().id(savedId).owner(owner).build();
        Visit visit = Visit.builder().id(savedId).pet(pet).build();

        visitMapService.save(visit);
        int actual = visitMapService.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        Long expected = id;

        Long actual = visitMapService.findById(id).getId();

        assertEquals(expected, actual);
    }
}