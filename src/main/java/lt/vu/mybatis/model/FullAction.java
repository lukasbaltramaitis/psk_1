package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

public class FullAction {
    @Getter @Setter
    private Action action;
    @Getter @Setter
    private Territory territoryA;
    @Getter @Setter
    private Country countryA;
    @Getter @Setter
    private Territory territoryB;
    @Getter @Setter
    private Country countryB;
    @Getter @Setter
    private Player player;
}
