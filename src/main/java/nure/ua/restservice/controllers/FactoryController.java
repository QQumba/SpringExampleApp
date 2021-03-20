package nure.ua.restservice.controllers;

import nure.ua.restservice.entities.FactoryEntity;
import nure.ua.restservice.repositories.FactoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/factory")
public class FactoryController {
    private final FactoryRepository factoryRepository;

    public FactoryController(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactoryEntity> get(@PathVariable("id") Long id){
        return ResponseEntity.of(factoryRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody FactoryEntity factoryEntity){
        return ResponseEntity.of(Optional.of(factoryRepository.save(factoryEntity).getId()));
    }
}
