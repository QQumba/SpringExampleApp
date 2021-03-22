package nure.ua.restservice.mappers;

import com.sun.istack.NotNull;
import nure.ua.restservice.contract.Car;
import nure.ua.restservice.entities.CarEntity;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car map(@NotNull CarEntity carEntity){
        return new Car()
                .setId(carEntity.getId())
                .setModelName(carEntity.getModelName())
                .setMaxSpeed(carEntity.getMaxSpeed())
                .setHasAutomaticTransmission(carEntity.getHasAutomaticTransmission());
    }

    public CarEntity map(@NotNull Car car){
        return new CarEntity()
                .setId(car.getId())
                .setModelName(car.getModelName())
                .setMaxSpeed(car.getMaxSpeed())
                .setHasAutomaticTransmission(car.getHasAutomaticTransmission());
    }

    public CarEntity mapToEntity(@NotNull Car source, CarEntity destination){
        if(destination == null){
            destination = new CarEntity();
        }
        return destination
                .setModelName(source.getModelName())
                .setMaxSpeed(source.getMaxSpeed())
                .setHasAutomaticTransmission(source.getHasAutomaticTransmission());
    }
}
