package lt.vu.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MapPK implements Serializable {
    private int territoryId;
    private int territoryId1;

    @Column(name = "territory_id")
    @Id
    public int getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(int territoryId) {
        this.territoryId = territoryId;
    }

    @Column(name = "territory_id1")
    @Id
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

        MapPK mapPK = (MapPK) o;

        if (territoryId != mapPK.territoryId) return false;
        if (territoryId1 != mapPK.territoryId1) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = territoryId;
        result = 31 * result + territoryId1;
        return result;
    }
}
