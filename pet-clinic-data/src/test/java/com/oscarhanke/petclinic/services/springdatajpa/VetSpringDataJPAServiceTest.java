package com.oscarhanke.petclinic.services.springdatajpa;

import com.oscarhanke.petclinic.model.Vet;
import com.oscarhanke.repositories.VetRepository;
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
class VetSpringDataJPAServiceTest {

    static final Long ID = 1L;

    Vet returnVet;

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSpringDataJPAService service;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().id(ID).build();
    }

    @Test
    void findAll() {
        Set<Vet> returnVetSet = new HashSet<>();
        returnVetSet.add(returnVet);

        when(vetRepository.findAll()).thenReturn(returnVetSet);

        Set<Vet> vets = service.findAll();

        assertNotNull(vets);
        assertEquals(1, vets.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));

        Vet vet = service.findById(ID);

        assertNotNull(vet);
        assertEquals(ID, vet.getId());
    }

    @Test
    void findByIdNotFound() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Vet vet = service.findById(ID);

        assertNull(vet);
    }

    @Test
    void save() {
        Vet vetToSave = returnVet;

        when(vetRepository.save(any())).thenReturn(returnVet);

        Vet savedVet = service.save(vetToSave);

        assertNotNull(savedVet);
        assertEquals(ID, savedVet.getId());
        verify(vetRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnVet);

        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(vetRepository).deleteById(anyLong());
    }
}