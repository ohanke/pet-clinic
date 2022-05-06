package com.oscarhanke.petclinic.services.springdatajpa;

import com.oscarhanke.petclinic.model.Vet;
import com.oscarhanke.petclinic.services.VetService;
import com.oscarhanke.repositories.VetRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetSpringDataJPAService implements VetService {

    private final VetRepository vetRepository;

    public VetSpringDataJPAService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long Id) {
        return vetRepository.findById(Id).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long Id) {
        vetRepository.deleteById(Id);
    }
}
