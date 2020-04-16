package lt.vu.entities;

import javax.persistence.*;

@Entity
@IdClass(MapPK.class)
public class Map {
    private int territoryId;
    private int territoryId1;
    private Territories territoriesByTerritoryId;
    private Territories territoriesByTerritoryId1;

    @Id
    @Column(name = "territory_id")
    public int getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(int territoryId) {
        this.territoryId = territoryId;
    }

    @Id
    @Column(name = "territory_id1")
    public int getTerritoryId1() {
        return territoryId1;
    }

    public void setTerritoryId1(int territoryId1) {
        this.territoryId1 = territoryId1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Map map = (Map) o;

        if (territoryId != map.territoryId) return false;
        if (territoryId1 != map.territoryId1) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = territoryId;
        result = 31 * result + territoryId1;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "territory_id", referencedColumnName = "id", nullable = false)
    public Territories getTerritoriesByTerritoryId() {
        return territoriesByTerritoryId;
    }

    public void setTerritoriesByTerritoryId(Territory territoriesByTerritoryId) {
        this.territoriesByTerritoryId = territoriesByTerritoryId;
    }

    public void setTerritoriesByTerritoryId(Territories territoriesByTerritoryId) {
        this.territoriesByTerritoryId = territoriesByTerritoryId;
    }

    @ManyToOne
    @JoinColumn(name = "territory_id1", referencedColumnName = "id", nullable = false)
    public Territories getTerritoriesByTerritoryId1() {
        return territoriesByTerritoryId1;
    }

    public void setTerritoriesByTerritoryId1(Territory territoriesByTerritoryId1) {
        this.territoriesByTerritoryId1 = territoriesByTerritoryId1;
    }

    public void setTerritoriesByTerritoryId1(Territories territoriesByTerritoryId1) {
        this.territoriesByTerritoryId1 = territoriesByTerritoryId1;
    }
}
