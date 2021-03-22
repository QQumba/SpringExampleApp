package nure.ua.restservice.services.implementations.mock;

import nure.ua.restservice.contract.Car;
import nure.ua.restservice.contract.CarFactory;
import nure.ua.restservice.entities.FactoryEntity;
import nure.ua.restservice.services.CarService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Scope("singleton")
public class MockCarService implements CarService {
    private final ConcurrentMap<Long, Car> cars = new ConcurrentHashMap();
    private Long currentId = 0L;

    @Override
    public Optional<Car> get(Long id) {
        Car car = cars.get(id);
        if(car == null){
            return Optional.empty();
        }
        return Optional.of(cars.get(id));
    }

    @Override
    public List<Car> getAll() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public Optional<Long> create(Car car) {
        car.setId(currentId++);
        cars.put(car.getId(), car);
        return Optional.of(car.getId());
    }

    @Override
    public List<Car> getFactoryCars(Long factoryId) {
        return null;
    }

    @Override
    public List<Car> getPersonCars(Long personId) {
        return null;
    }

    @Override
    public void addFactory(CarFactory carFactory) {

    }

    @Override
    public boolean update(Car car) {
        if(!cars.containsKey(car.getId())){
            return false;
        }

        cars.replace(car.getId(), car);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if(!cars.containsKey(id)){
            return false;
        }

        cars.remove(id);
        return true;
    }
}
