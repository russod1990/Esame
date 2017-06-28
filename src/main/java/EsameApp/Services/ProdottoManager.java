/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Services;

import EsameApp.Entity.Prodotto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tss
 */
@Stateless
public class ProdottoManager {

    @PersistenceContext
    EntityManager em;

    public Prodotto findById(long id) {
        return em.find(Prodotto.class, id);
    }

    public Prodotto save(Prodotto tosave) {
        return em.merge(tosave);
    }

    public void remove(long id) {
        Prodotto find = em.find(Prodotto.class, id);
        em.remove(find);
    }

    public List<Prodotto> findAll() {
        return em.createNamedQuery(Prodotto.FIND_ALL).getResultList();
    }
}
