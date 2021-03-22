package nure.ua.restservice.controllers;

import nure.ua.restservice.contract.Car;
import nure.ua.restservice.contract.PersonCar;
import nure.ua.restservice.entities.PersonEntity;
import nure.ua.restservice.services.PersonService;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonEntity> getPerson(@PathVariable Long id){
        return ResponseEntity.of(personService.get(id));
    }

    @GetMapping
    public List<PersonEntity> getAllPersons(){
        return personService.getAll();
    }

    @PostMapping
    public ResponseEntity<Long> createPerson(@RequestBody PersonEntity personEntity){
        return ResponseEntity.of(personService.create(personEntity));
    }

    @PostMapping("/car")
    public void addCar(@RequestBody PersonCar personCar){
        personService.addCar(personCar);
    }
}
