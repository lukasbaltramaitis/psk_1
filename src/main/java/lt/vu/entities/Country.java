package lt.vu.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "countries", schema = "ww2myboardgamedb", catalog = "")
public class Country {
    private int id;
    private String name;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (id != country.id) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "countriesByCountryId")
    public Collection<Territory> getTerritoriesById() {
        return territoriesById;
    }

    public void setTerritoriesById(Collection<Territory> territoriesById) {
        this.territoriesById = territoriesById;
    }
}
