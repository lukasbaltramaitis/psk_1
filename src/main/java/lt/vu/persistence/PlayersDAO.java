package lt.vu.persistence;

import lt.vu.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
        TypedQuery<Player> query = em.createQuery("select p from Player p where p.name = ?1", Player.class);
        return query.setParameter(1, player_name).getSingleResult();
    }

    public Player update(Player player){
        return em.merge(player);
    }

    public int getCount(){
        return (int) em.createQuery("select count(p) from Player p").getSingleResult();
    }
}
