package lt.vu.persistence;

import lt.vu.entities.Action;
import lt.vu.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
}
