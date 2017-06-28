/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Resources;

import EsameApp.Entity.Asta;
import EsameApp.Entity.Prodotto;
import EsameApp.Services.AstaManager;
import EsameApp.Services.ProdottoManager;
import EsameApp.Services.TokenManager;
import EsameApp.Services.TokenNeeded;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tss
 */
@Stateless
@Path("Prodotti")
@Produces({MediaType.APPLICATION_JSON})
public class ProdottoResources {
    @Inject
    TokenManager tokenManager;

    @Inject
    ProdottoManager prodottoManager;

    @GET
    public List<Prodotto> findAll() {
        return prodottoManager.findAll();
    }

    @GET
    @Path("mieiProdotti")
    @TokenNeeded
    public List<Prodotto> findByUser() {
        long id = tokenManager.getCurrentUser().getIdUtente();
        return prodottoManager.findByUser(id);
    }

    @POST
    @Path("nuovo")
    @TokenNeeded
    public void crea(Prodotto p) {
        System.out.println("******* nuova product ********");
        p.setUtente(tokenManager.getCurrentUser());
        prodottoManager.save(p);
    }

    @DELETE
    @Path("{id}")
    @TokenNeeded
    public void delete(@PathParam("id") long id) {
        System.out.println("******* delete ************");
        prodottoManager.remove(id);
    }

    @PUT
    @Path("{id}")
    @TokenNeeded
    public void update(@PathParam("id") long id, Prodotto p) {
        System.out.println("******* update **********");
        p.setUtente(tokenManager.getCurrentUser());
        p.setIdProduct(id);
        prodottoManager.save(p);
//        }
    }
}
