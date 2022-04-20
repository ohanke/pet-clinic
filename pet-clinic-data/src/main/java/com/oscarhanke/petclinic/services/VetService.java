package com.oscarhanke.petclinic.services;


import com.oscarhanke.petclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Vet Vet);
    Set<Vet> findAll();
}
