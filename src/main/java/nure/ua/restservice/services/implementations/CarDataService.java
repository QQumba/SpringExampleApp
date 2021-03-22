package nure.ua.restservice.services.implementations;

import com.sun.istack.NotNull;
import nure.ua.restservice.contract.Car;
import nure.ua.restservice.contract.CarFactory;
import nure.ua.restservice.entities.CarEntity;
import nure.ua.restservice.entities.FactoryEntity;
import nure.ua.restservice.entities.PersonEntity;
import nure.ua.restservice.mappers.CarMapper;
import nure.ua.restservice.repositories.CarRepository;
import nure.ua.restservice.repositories.FactoryRepository;
import nure.ua.restservice.repositories.PersonRepository;
import nure.ua.restservice.services.CarService;
import nure.ua.restservice.services.PersonService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class CarDataService implements CarService {
    private final CarRepository carRepository;
    private final FactoryRepository factoryRepository;
    private final PersonRepository personRepository;
    private final CarMapper mapper;

    public CarDataService(CarRepository carRepository, FactoryRepository factoryRepository, PersonRepository personRepository, CarMapper mapper) {
        this.carRepository = carRepository;
        this.factoryRepository = factoryRepository;
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Car> get(@NotNull Long id) {
        Optional<CarEntity> carEntity = carRepository.findById(id);
        if(carEntity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.map(carEntity.get()));
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll()
                .stream().map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Long> create(@NotNull Car car) {
        return Optional.of(carRepository.save(mapper.map(car)).getId());
    }

    @Override
    public List<Car> getFactoryCars(Long factoryId) {
        Optional<FactoryEntity> factory = factoryRepository.findById(factoryId);
        if(factory.isEmpty()){
            return new ArrayList<>();
        }

        return factory.get().getCars().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public List<Car> getPersonCars(Long personId) {
        Optional<PersonEntity> person = personRepository.findById(personId);
        if(person.isEmpty()){
            return new ArrayList<>();
        }

        return person.get().getCars().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public void addFactory(CarFactory carFactory) {
        Optional<CarEntity> carEntity = carRepository.findById(carFactory.getCarId());
        Optional<FactoryEntity> factoryEntity = factoryRepository.findById(carFactory.getFactoryId());
        if(carEntity.isEmpty() || factoryEntity.isEmpty()){
            throw new IllegalArgumentException("Car or factory with specified id does not exist.");
        }

        carEntity.get().getFactories().add(factoryEntity.get());
        carRepository.save(carEntity.get());
    }

    @Override
    public boolean update(@NotNull Car car) {
        Optional<CarEntity> carEntity = carRepository.findById(car.getId());
        if(carEntity.isEmpty()){
            return false;
        }
        carRepository.save(mapper.mapToEntity(car, carEntity.get()));
        return true;
    }

    @Override
    public boolean delete(@NotNull Long id) {
        if(!carRepository.existsById(id)){
            return false;
        }

        carRepository.deleteById(id);
        return true;
    }
}
