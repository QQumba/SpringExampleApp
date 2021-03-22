package nure.ua.restservice.mappers;

import com.sun.istack.NotNull;
import nure.ua.restservice.contract.Factory;
import nure.ua.restservice.entities.FactoryEntity;
import org.springframework.stereotype.Component;

@Component
public class FactoryMapper {
    public Factory map(@NotNull FactoryEntity factoryEntity){
        return new Factory()
                .setId(factoryEntity.getId())
                .setLocation(factoryEntity.getLocation())
                .setName(factoryEntity.getName());
    }

    public FactoryEntity map(@NotNull Factory factory){
        return new FactoryEntity()
                .setId(factory.getId())
                .setLocation(factory.getLocation())
                .setName(factory.getName());
    }

    public FactoryEntity mapToEntity(@NotNull Factory source, FactoryEntity destination){
        if(destination == null){
            destination = new FactoryEntity();
        }
        return destination
                .setName(source.getName())
                .setLocation(source.getLocation());
    }
}
