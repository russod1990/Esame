/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Services;

import EsameApp.Entity.Utente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tss
 */
@Stateless
public class UtenteManager {

    @PersistenceContext
    EntityManager em;

    public Utente findById(long id) {
        return em.find(Utente.class, id);
    }

    public Utente save(Utente tosave) {
        return em.merge(tosave);
    }

    public void remove(long id) {
        Utente find = em.find(Utente.class, id);
        em.remove(find);
    }

    public List<Utente> findAll() {
        return em.createNamedQuery(Utente.FIND_ALL).getResultList();
    }

    //Login
    public Utente login(String email, String pwd) {
        Utente result = null;
        try {
            result = em.createNamedQuery(Utente.FIND_BY_EMAIL_AND_PWD, Utente.class).setParameter("email", email).setParameter("password", pwd).getSingleResult();
        } catch (NoResultException e) {

        }
        return result;
    }

    //Restituisce l'utente loggato
    public Utente getLogged(long id) {
        return em.find(Utente.class, id);
    }
}
