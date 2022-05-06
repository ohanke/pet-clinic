package com.oscarhanke.petclinic.services.springdatajpa;

import com.oscarhanke.petclinic.model.Pet;
import com.oscarhanke.petclinic.services.PetService;
import com.oscarhanke.repositories.PetRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSpringDataJpaService implements PetService {

    private final PetRepository petRepository;

    public PetSpringDataJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets
    }

    @Override
    public Pet findById(Long Id) {
        return petRepository.findById(Id).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

    @Override
    public void deleteById(Long Id) {
        petRepository.deleteById(Id);
    }
}