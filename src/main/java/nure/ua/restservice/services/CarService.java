package nure.ua.restservice.services;

import nure.ua.restservice.contract.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarService{
    Optional<Car> get(Long id);
    List<Car> getAll();
    Optional<Long> create(Car car);
    boolean update(Car car);
    boolean delete(Long id);
}
