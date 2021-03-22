package nure.ua.restservice.controllers;

import nure.ua.restservice.contract.Factory;
import nure.ua.restservice.entities.FactoryEntity;
import nure.ua.restservice.repositories.FactoryRepository;
import nure.ua.restservice.services.FactoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/factory")
public class FactoryController {
    private final FactoryService factoryService;

    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factory> getFactory(@PathVariable("id") Long id){
        return ResponseEntity.of(factoryService.get(id));
    }

    @GetMapping("/all")
    public List<Factory> getAllFactories(){
        return factoryService.getAll();
    }

    @PostMapping
    public ResponseEntity<Long> createFactory(@RequestBody Factory factory){
        return ResponseEntity.of(factoryService.create(factory));
    }

    @PutMapping
    public void updateFactory(@RequestBody Factory factory){
        factoryService.update(factory);
    }

    @DeleteMapping("/{id}")
    public void deleteFactory(@PathVariable("id") Long id){
        factoryService.delete(id);
    }
}
