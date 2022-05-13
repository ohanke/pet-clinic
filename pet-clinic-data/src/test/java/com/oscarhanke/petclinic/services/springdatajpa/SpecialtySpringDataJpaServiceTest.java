package com.oscarhanke.petclinic.services.springdatajpa;

import com.oscarhanke.petclinic.model.Specialty;
import com.oscarhanke.repositories.SpecialtyRepository;
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
class SpecialtySpringDataJpaServiceTest {

    static final Long ID = 1L;

    Specialty returnSpeciality;

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialtySpringDataJpaService service;

    @BeforeEach
    void setUp() {
        returnSpeciality = Specialty.builder().id(ID).build();
    }

    @Test
    void findAll() {
        Set<Specialty> returnSpecialities = new HashSet<>();
        returnSpecialities.add(returnSpeciality);

        when(specialtyRepository.findAll()).thenReturn(returnSpecialities);

        Set<Specialty> specialties = service.findAll();

        assertNotNull(specialties);
        assertEquals(1, specialties.size());
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(returnSpeciality));

        Specialty specialty = service.findById(ID);

        assertNotNull(specialty);
        assertEquals(ID, specialty.getId());
    }

    @Test
    void findByIdNotFound() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());

        Specialty specialty = service.findById(ID);

        assertNull(specialty);
    }

    @Test
    void save() {
        Specialty specialtyToSave = returnSpeciality;

        when(specialtyRepository.save(any())).thenReturn(returnSpeciality);

        Specialty savedSpeciality = service.save(specialtyToSave);

        assertNotNull(savedSpeciality);
        verify(specialtyRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnSpeciality);

        verify(specialtyRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnSpeciality.getId());

        verify(specialtyRepository).deleteById(anyLong());
    }
}