package lt.vu.persistence;

import lt.vu.entities.Player;
import lt.vu.entities.Territory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

@ApplicationScoped
public class TerritoriesDAO {
    @Inject
    private EntityManager em;

    public void persist(Territory territory){
        this.em.persist(territory);
    }

    public Territory findOne(Integer id){
        return em.find(Territory.class, id);
    }

    public Territory update(Territory territory){
        return em.merge(territory);
    }

    public void flush(){ em.flush();}

    public EntityTransaction getTransaction(){ return em.getTransaction(); }

}
