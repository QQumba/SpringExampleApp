package nure.ua.restservice.repositories;

import nure.ua.restservice.entities.FactoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<FactoryEntity, Long> {
}
