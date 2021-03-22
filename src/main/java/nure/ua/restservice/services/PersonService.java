package nure.ua.restservice.services;

import nure.ua.restservice.contract.PersonCar;
import nure.ua.restservice.entities.PersonEntity;
import org.springframework.stereotype.Service;

@Service
public interface PersonService extends CRUDService<PersonEntity>{
    void addCar(PersonCar personCar);
}
