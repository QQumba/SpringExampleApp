package nure.ua.restservice.services.implementations;

import nure.ua.restservice.contract.PersonCar;
import nure.ua.restservice.entities.CarEntity;
import nure.ua.restservice.entities.PersonEntity;
import nure.ua.restservice.repositories.CarRepository;
import nure.ua.restservice.repositories.PersonRepository;
import nure.ua.restservice.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO: replace factory domain entity with contract entity
@Service
public class PersonDataService implements PersonService {
    private final PersonRepository personRepository;
    private final CarRepository carRepository;

    public PersonDataService(PersonRepository personRepository, CarRepository carRepository) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Optional<PersonEntity> get(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<PersonEntity> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Long> create(PersonEntity personEntity) {
        return Optional.of(personRepository.save(personEntity).getId());
    }
    @Override
    public void addCar(PersonCar personCar) {
        Optional<CarEntity> carEntity = carRepository.findById(personCar.getCarId());
        Optional<PersonEntity> personEntity = personRepository.findById(personCar.getPersonId());
        if(carEntity.isEmpty() || personEntity.isEmpty()){
            throw new IllegalArgumentException("Car or person with specified id does not exist.");
        }

        personEntity.get().getCars().add(carEntity.get());
        personRepository.save(personEntity.get());
    }

    @Override
    public boolean update(PersonEntity personEntity) {
        Optional<PersonEntity> person = personRepository.findById(personEntity.getId());
        if(person.isEmpty()){
            return false;
        }

        person.get()
                .setName(personEntity.getName())
                .setEmail(personEntity.getEmail());

        personRepository.save(person.get());
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if(!personRepository.existsById(id)){
            return false;
        }

        personRepository.deleteById(id);
        return true;
    }
}
