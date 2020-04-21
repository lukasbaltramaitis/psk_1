package lt.vu.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "territories", schema = "ww2myboardgamedb", catalog = "")
public class Territory {
    private int id;
    private String type;
    private int countryId;
    private String name;
    private Integer state;
    private Integer playerId;
    private Collection<Action> actionsById;
    private Collection<Action> actionsById_0;
    private Collection<Map> mapsById;
    private Collection<Map> mapsById_0;
    private Country countriesByCountryId;
    private Player playersByPlayerId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "countryId")
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "playerId")
    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Territory territory = (Territory) o;

        if (id != territory.id) return false;
        if (countryId != territory.countryId) return false;
        if (type != null ? !type.equals(territory.type) : territory.type != null) return false;
        if (name != null ? !name.equals(territory.name) : territory.name != null) return false;
        if (state != null ? !state.equals(territory.state) : territory.state != null) return false;
        if (playerId != null ? !playerId.equals(territory.playerId) : territory.playerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + countryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (playerId != null ? playerId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "territoriesByTerritoryAId")
    public Collection<Action> getActionsById() {
        return actionsById;
    }

    public void setActionsById(Collection<Action> actionsById) {
        this.actionsById = actionsById;
    }

    @OneToMany(mappedBy = "territoriesByTerritoryBId")
    public Collection<Action> getActionsById_0() {
        return actionsById_0;
    }

    public void setActionsById_0(Collection<Action> actionsById_0) {
        this.actionsById_0 = actionsById_0;
    }

    @OneToMany(mappedBy = "territoriesByTerritoryId")
    public Collection<Map> getMapsById() {
        return mapsById;
    }

    public void setMapsById(Collection<Map> mapsById) {
        this.mapsById = mapsById;
    }

    @OneToMany(mappedBy = "territoriesByTerritoryId1")
    public Collection<Map> getMapsById_0() {
        return mapsById_0;
    }

    public void setMapsById_0(Collection<Map> mapsById_0) {
        this.mapsById_0 = mapsById_0;
    }

    @ManyToOne
    @JoinColumn(name = "countryId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Country getCountriesByCountryId() {
        return countriesByCountryId;
    }

    public void setCountriesByCountryId(Country countriesByCountryId) {
        this.countriesByCountryId = countriesByCountryId;
    }

    @ManyToOne
    @JoinColumn(name = "playerId", referencedColumnName = "id", insertable = false, updatable = false)
    public Player getPlayersByPlayerId() {
        return playersByPlayerId;
    }

    public void setPlayersByPlayerId(Player playersByPlayerId) {
        this.playersByPlayerId = playersByPlayerId;
    }
}
