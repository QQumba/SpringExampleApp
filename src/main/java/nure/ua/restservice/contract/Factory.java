package nure.ua.restservice.contract;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Factory {
    private Long id;
    private String name;
    private String location;
}
