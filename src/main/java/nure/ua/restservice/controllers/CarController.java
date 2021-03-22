package nure.ua.restservice.controllers;

import nure.ua.restservice.contract.Car;
import nure.ua.restservice.contract.CarFactory;
import nure.ua.restservice.contract.Factory;
import nure.ua.restservice.entities.FactoryEntity;
import nure.ua.restservice.mappers.CarMapper;
import nure.ua.restservice.repositories.FactoryRepository;
import nure.ua.restservice.services.CarService;
import nure.ua.restservice.services.FactoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
public class CarController {
    private static final String INFO_RESPONSE_MESSAGE = "This is a REST API spring app. Mykyta Knysh 2021 &copy";

    private final CarService carService;
    private final FactoryService factoryService;

    public CarController(CarService carService, FactoryService factoryService) {
        this.carService = carService;
        this.factoryService = factoryService;
    }

    @GetMapping
    public String getInfo(){
        return INFO_RESPONSE_MESSAGE;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Long id){
        return ResponseEntity.of(carService.get(id));
    }

    @GetMapping("/all")
    public List<Car> getAllCars(){
        return carService.getAll();
    }

    @GetMapping("/factory/{id}")
    public List<Car> getFactoryCars(@PathVariable("id") Long factoryId){
        return carService.getFactoryCars(factoryId);
    }

    @GetMapping("/person/{id}")
    public List<Car> getPersonCars(@PathVariable("id") Long personId){
        return carService.getPersonCars(personId);
    }

    @PostMapping
    public ResponseEntity<Long> createCar(@RequestBody Car car){
        return ResponseEntity.of(carService.create(car));
    }

    @PostMapping("/assign")
    public void assignToFactory(@RequestBody CarFactory carFactory){
        carService.addFactory(carFactory);
    }
    
    @PutMapping
    public ResponseEntity updateCar(@RequestBody Car car){
        if(carService.update(car)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") Long id){
        if(carService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
