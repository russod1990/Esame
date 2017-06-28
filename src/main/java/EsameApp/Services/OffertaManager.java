/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Services;

import EsameApp.Entity.Offerta;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tss
 */
@Stateless
public class OffertaManager {

    @PersistenceContext
    EntityManager em;

    public Offerta findById(long id) {
        return em.find(Offerta.class, id);
    }

    public Offerta save(Offerta tosave) {
        return em.merge(tosave);
    }

    public void remove(long id) {
        Offerta find = em.find(Offerta.class, id);
        em.remove(find);
    }

    public List<Offerta> findAll() {
        return em.createNamedQuery(Offerta.FIND_ALL).getResultList();
    }
    
    public List<Offerta> findByUser(long id){
        return em.createNamedQuery(Offerta.FIND_BY_USER).getResultList();
    }

    public Offerta newOffer(Offerta o) {
        BigDecimal price=o.getImporto();
        return null;
    }
}
