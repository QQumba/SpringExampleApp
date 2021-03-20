package nure.ua.restservice.contract;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Car{
    private Long id;
    private String modelName;
    private Integer maxSpeed;
    private Boolean hasAutomaticTransmission;
}
