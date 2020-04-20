package lt.vu.persistence;

import lt.vu.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class PlayersDAO {

    @Inject
    private EntityManager em;

    public void persist(Player player){
        this.em.persist(player);
    }

    public Player findOne(Integer id){
        return em.find(Player.class, id);
    }

    public Player findByName(String player_name){
        try {
            TypedQuery<Player> query = em.createQuery("select p from Player p where p.name = ?1", Player.class);
            return query.setParameter(1, player_name).getSingleResult();
        }
        catch(NoResultException exe){
            return null;
        }
    }

    public Player update(Player player){
        return em.merge(player);
    }

    public int getCount(){
        try {
            TypedQuery<Long> query = em.createQuery("select count(p.id) from Player p", Long.class);
            long number = query.getSingleResult();
            return (int)number;
        }
        catch (NoResultException exe){
            return 0;
        }
    }
}
