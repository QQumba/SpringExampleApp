package nure.ua.restservice.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "car")
public class CarEntity extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private Integer maxSpeed;

    @Column(nullable = false)
    private Boolean hasAutomaticTransmission;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "car_factory",
            joinColumns = @JoinColumn(name = "car_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "factory_id",
                    referencedColumnName = "id"))
    private List<FactoryEntity> factories;
}
