/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Resources;

import EsameApp.Entity.Offerta;
import EsameApp.Services.OffertaManager;
import EsameApp.Services.TokenManager;
import EsameApp.Services.TokenNeeded;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tss
 */
@Stateless
@Path("Offerte")
@Produces({MediaType.APPLICATION_JSON})
public class OffertaResources {
     @Inject
    TokenManager tokenManager;

    @Inject
    OffertaManager offertaManager;

    @GET
    public List<Offerta> findAll() {
        return offertaManager.findAll();
    }

    @GET
    @Path("mieOfferte")
    @TokenNeeded
    public List<Offerta> findByUser() {
        long id = tokenManager.getCurrentUser().getIdUtente();
        return offertaManager.findByUser(id);
    }

    @POST
    @Path("nuova")
    @TokenNeeded
    public void crea(Offerta o) {
        System.out.println("******* nuova Offerta ********");
        o.setUtente(tokenManager.getCurrentUser());
        offertaManager.newOffer(o);
    }
}
