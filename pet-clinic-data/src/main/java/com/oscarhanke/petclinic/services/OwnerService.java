package com.oscarhanke.petclinic.services;

import com.oscarhanke.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
