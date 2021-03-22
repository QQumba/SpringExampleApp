package nure.ua.restservice.repositories;

import nure.ua.restservice.contract.Car;
import nure.ua.restservice.entities.CarEntity;
import nure.ua.restservice.entities.FactoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactoryRepository extends JpaRepository<FactoryEntity, Long> {
}
