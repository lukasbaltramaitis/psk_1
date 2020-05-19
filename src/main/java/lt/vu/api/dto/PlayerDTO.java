package lt.vu.api.dto;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Action;
import lt.vu.entities.Territory;

import java.util.Collection;
@Getter @Setter
public class PlayerDTO {
    private int id;
    private String name;
    private String password;
    private Integer money;
}
