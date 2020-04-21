package lt.vu.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "players", schema = "ww2myboardgamedb", catalog = "")
public class Player {
    private int id;
    private byte isAi;
    private String name;
    private Integer money;
    private String password;
    private Collection<Action> actionsById;
    private Collection<Territory> territoriesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "isAI")
    public byte getIsAi() {
        return isAi;
    }

    public void setIsAi(byte isAi) {
        this.isAi = isAi;
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
    @Column(name = "money")
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        if (isAi != player.isAi) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (money != null ? !money.equals(player.money) : player.money != null) return false;
        if (password != null ? !password.equals(player.password) : player.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) isAi;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "playersByPlayerId")
    public Collection<Action> getActionsById() {
        return actionsById;
    }

    public void setActionsById(Collection<Action> actionsById) {
        this.actionsById = actionsById;
    }

    @OneToMany(mappedBy = "playersByPlayerId")
    public Collection<Territory> getTerritoriesById() {
        return territoriesById;
    }

    public void setTerritoriesById(Collection<Territory> territoriesById) {
        this.territoriesById = territoriesById;
    }
}
