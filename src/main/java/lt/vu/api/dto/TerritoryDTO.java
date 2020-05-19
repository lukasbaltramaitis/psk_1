package lt.vu.api.dto;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Action;
import lt.vu.entities.Country;
import lt.vu.entities.Map;
import lt.vu.entities.Player;

import java.util.Collection;
@Getter @Setter
public class TerritoryDTO {
    private int id;
    private String type;
    private int countryId;
    private String name;
    private Integer state;
    private Integer playerId;
}
