package nure.ua.restservice.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "person")
public class PersonEntity extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    @OneToMany(mappedBy = "person")
    private List<CarEntity> cars;
}
