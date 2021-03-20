package nure.ua.restservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "factory")
public class FactoryEntity extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @JsonIgnore
    @ManyToMany(mappedBy = "factories")
    private List<CarEntity> cars;
}
