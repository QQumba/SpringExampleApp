package nure.ua.restservice.services;

import nure.ua.restservice.contract.Car;
import nure.ua.restservice.contract.CarFactory;
import nure.ua.restservice.entities.FactoryEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.jsf.FacesContextUtils;

import java.util.List;
import java.util.Optional;

@Service
public interface CarService extends CRUDService<Car>{
    List<Car> getFactoryCars(Long factoryId);
    List<Car> getPersonCars(Long personId);
    void addFactory(CarFactory carFactory);
}
