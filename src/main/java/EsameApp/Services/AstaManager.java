/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Services;

import EsameApp.Entity.Asta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tss
 */
@Stateless
public class AstaManager {

    @PersistenceContext
    EntityManager em;

    public Asta findById(long id) {
        return em.find(Asta.class, id);
    }

    public Asta save(Asta tosave) {
        return em.merge(tosave);
    }

    public void remove(long id) {
        Asta find = em.find(Asta.class, id);
        em.remove(find);
    }

    public List<Asta> findAll() {
        return em.createNamedQuery(Asta.FIND_ALL).getResultList();
    }
    
    public List<Asta> findByUser(long id){
        return em.createNamedQuery(Asta.FIND_BY_USER).setParameter("idUtente", id).getResultList();
    }
    
    public List<Asta> findByOtherUser(long id){
        return em.createNamedQuery(Asta.FIND_BY_OTHER_USER).setParameter("idUtente", id).getResultList();
    }
}
