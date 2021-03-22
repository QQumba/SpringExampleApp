package nure.ua.restservice.services.implementations;

import nure.ua.restservice.contract.Factory;
import nure.ua.restservice.entities.FactoryEntity;
import nure.ua.restservice.mappers.FactoryMapper;
import nure.ua.restservice.repositories.FactoryRepository;
import nure.ua.restservice.services.FactoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactoryDataService implements FactoryService {
    private final FactoryRepository factoryRepository;
    private final FactoryMapper mapper;

    public FactoryDataService(FactoryRepository factoryRepository, FactoryMapper mapper) {
        this.factoryRepository = factoryRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Factory> get(Long id) {
        Optional<FactoryEntity> optional = factoryRepository.findById(id);
        if(optional.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.map(optional.get()));
    }

    @Override
    public List<Factory> getAll() {
        return factoryRepository
                .findAll()
                .stream().map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Long> create(Factory factory) {
        return Optional.of(factoryRepository.save(mapper.map(factory)).getId());
    }

    @Override
    public boolean update(Factory factory) {
        Optional<FactoryEntity> factoryEntity = factoryRepository.findById(factory.getId());
        if(factoryEntity.isEmpty()){
            return false;
        }
        factoryRepository.save(mapper.mapToEntity(factory, factoryEntity.get()));
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if(!factoryRepository.existsById(id)){
            return false;
        }

        factoryRepository.deleteById(id);
        return true;
    }
}
