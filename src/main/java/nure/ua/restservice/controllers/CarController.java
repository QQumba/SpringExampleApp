package nure.ua.restservice.controllers;

import nure.ua.restservice.contract.Car;
import nure.ua.restservice.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private static final String INFO_RESPONSE_MESSAGE = "This is a REST API spring app. Mykyta Knysh 2020 &copy";

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
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

    @PostMapping
    public ResponseEntity<Long> createCar(@RequestBody Car car){
        return ResponseEntity.of(carService.create(car));
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
