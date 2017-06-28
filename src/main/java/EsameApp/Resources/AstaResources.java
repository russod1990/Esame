/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Resources;

import EsameApp.Entity.Asta;
import EsameApp.Services.AstaManager;
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
@Path("Aste")
@Produces({MediaType.APPLICATION_JSON})
public class AstaResources {

    @Inject
    TokenManager tokenManager;

    @Inject
    AstaManager astaManager;

    @GET
    public List<Asta> findAll() {
        return astaManager.findAll();
    }

    @GET
    @Path("mieAste")
    @TokenNeeded
    public List<Asta> findByUser() {
        long id = tokenManager.getCurrentUser().getIdUtente();
        return astaManager.findByUser(id);
    }

    @GET
    @Path("Aste")
    @TokenNeeded
    public List<Asta> findByOtherUser() {
        long id = tokenManager.getCurrentUser().getIdUtente();
        return astaManager.findByOtherUser(id);
    }

    @POST
    @Path("nuova")
    @TokenNeeded
    public void crea(Asta a) {
        System.out.println("******* nuova asta ********");
        a.setUtente(tokenManager.getCurrentUser());
        astaManager.save(a);
    }

    @DELETE
    @Path("{id}")
    @TokenNeeded
    public void delete(@PathParam("id") long id) {
        System.out.println("******* delete ************");
        astaManager.remove(id);
    }

    @PUT
    @Path("{id}")
    @TokenNeeded
    public void update(@PathParam("id") long id, Asta a) {
        System.out.println("******* update **********");
        a.setUtente(tokenManager.getCurrentUser());
        a.setIdAsta(id);
        astaManager.save(a);
//        }
    }
}
