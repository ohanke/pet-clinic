package com.oscarhanke.petclinic.services.springdatajpa;

import com.oscarhanke.petclinic.model.Visit;
import com.oscarhanke.repositories.VisitRepository;
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
class VisitSpringDataJPAServiceTest {

    static final Long ID = 1L;

    Visit returnVisit;

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSpringDataJPAService service;

    @BeforeEach
    void setUp() {
        returnVisit = Visit.builder().id(ID).build();
    }

    @Test
    void findAll() {
        Set<Visit> returnVisits = new HashSet<>();
        returnVisits.add(returnVisit);

        when(visitRepository.findAll()).thenReturn(returnVisits);

        Set<Visit> visits = service.findAll();

        assertNotNull(visits);
        assertEquals(1, visits.size());
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));

        Visit visit = service.findById(ID);

        assertNotNull(visit);
        assertEquals(ID, visit.getId());
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());

        Visit visit = service.findById(ID);

        assertNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = returnVisit;

        when(visitRepository.save(any())).thenReturn(returnVisit);

        Visit savedVisit = service.save(visitToSave);

        assertNotNull(savedVisit);

        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnVisit);

        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(visitRepository).deleteById(anyLong());
    }
}