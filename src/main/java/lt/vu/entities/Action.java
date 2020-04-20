package lt.vu.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "actions", schema = "ww2myboardgamedb", catalog = "")
public class Action {
    private int id;
    private Integer playerId;
    private Integer priority;
    private String action;
    private Integer territoryAId;
    private Integer territoryBId;
    private Timestamp creationDate;
    private Integer roundNr;
    private Player playersByPlayerId;
    private Territory territoriesByTerritoryAId;
    private Territory territoriesByTerritoryBId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "playerId")
    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "priority")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Basic
    @Column(name = "territoryAId")
    public Integer getTerritoryAId() {
        return territoryAId;
    }

    public void setTerritoryAId(Integer territoryAId) {
        this.territoryAId = territoryAId;
    }

    @Basic
    @Column(name = "territoryBId")
    public Integer getTerritoryBId() {
        return territoryBId;
    }

    public void setTerritoryBId(Integer territoryBId) {
        this.territoryBId = territoryBId;
    }

    @Basic
    @Column(name = "creationDate")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "roundNr")
    public Integer getRoundNr() {
        return roundNr;
    }

    public void setRoundNr(Integer roundNr) {
        this.roundNr = roundNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action1 = (Action) o;

        if (id != action1.id) return false;
        if (playerId != null ? !playerId.equals(action1.playerId) : action1.playerId != null) return false;
        if (priority != null ? !priority.equals(action1.priority) : action1.priority != null) return false;
        if (action != null ? !action.equals(action1.action) : action1.action != null) return false;
        if (territoryAId != null ? !territoryAId.equals(action1.territoryAId) : action1.territoryAId != null)
            return false;
        if (territoryBId != null ? !territoryBId.equals(action1.territoryBId) : action1.territoryBId != null)
            return false;
        if (creationDate != null ? !creationDate.equals(action1.creationDate) : action1.creationDate != null)
            return false;
        if (roundNr != null ? !roundNr.equals(action1.roundNr) : action1.roundNr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (playerId != null ? playerId.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (territoryAId != null ? territoryAId.hashCode() : 0);
        result = 31 * result + (territoryBId != null ? territoryBId.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (roundNr != null ? roundNr.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "playerId", referencedColumnName = "id", insertable = false, updatable = false)
    public Player getPlayersByPlayerId() {
        return playersByPlayerId;
    }

    public void setPlayersByPlayerId(Player playersByPlayerId) {
        this.playersByPlayerId = playersByPlayerId;
    }

    @ManyToOne
    @JoinColumn(name = "territoryAId", referencedColumnName = "id",  insertable = false, updatable = false)
    public Territory getTerritoriesByTerritoryAId() {
        return territoriesByTerritoryAId;
    }

    public void setTerritoriesByTerritoryAId(Territory territoriesByTerritoryAId) {
        this.territoriesByTerritoryAId = territoriesByTerritoryAId;
    }

    @ManyToOne
    @JoinColumn(name = "territoryBId", referencedColumnName = "id",  insertable = false, updatable = false)
    public Territory getTerritoriesByTerritoryBId() {
        return territoriesByTerritoryBId;
    }

    public void setTerritoriesByTerritoryBId(Territory territoriesByTerritoryBId) {
        this.territoriesByTerritoryBId = territoriesByTerritoryBId;
    }
}
