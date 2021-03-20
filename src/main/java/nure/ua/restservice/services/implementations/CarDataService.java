package nure.ua.restservice.services.implementations;

import com.sun.istack.NotNull;
import nure.ua.restservice.contract.Car;
import nure.ua.restservice.entities.CarEntity;
import nure.ua.restservice.mappers.CarMapper;
import nure.ua.restservice.repositories.CarRepository;
import nure.ua.restservice.services.CarService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class CarDataService implements CarService {
    private final CarRepository carRepository;
    private final CarMapper mapper;

    public CarDataService(CarRepository carRepository, CarMapper mapper) {
        this.carRepository = carRepository;
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
