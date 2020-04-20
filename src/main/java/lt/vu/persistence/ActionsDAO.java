package lt.vu.persistence;

import lt.vu.entities.Action;
import lt.vu.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ActionsDAO {
    @Inject
    private EntityManager em;

    public void persist(Action action){
        this.em.persist(action);
    }

    public Action findOne(Integer id){
        return em.find(Action.class, id);
    }

    public Action update(Action action){
        return em.merge(action);
    }

    public List<Action> selectByRound(int roundNr){
        try {
            TypedQuery<Action> query = em.createQuery("select a from Action a where a.roundNr = ?1", Action.class);
            return query.setParameter(1, roundNr).getResultList();
        }
        catch (NoResultException exe){
            return new ArrayList<>();
        }
    }
}
