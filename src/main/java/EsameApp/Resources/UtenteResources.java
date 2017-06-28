/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Resources;

import EsameApp.Entity.Utente;
import EsameApp.Services.UtenteManager;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tss
 */
@Stateless
@Path("Utenti")
@Produces({MediaType.APPLICATION_JSON})
public class UtenteResources {
    @Inject
    UtenteManager utenteManager;

    @GET
    public List<Utente> all() {
        return utenteManager.findAll();
    }

    @GET
    @Path("{id}")
    public Utente find(@PathParam("id") long id) {
        return utenteManager.findById(id);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        utenteManager.remove(id);
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Utente create(Utente utente) {
        return utenteManager.save(utente);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void create(@FormParam("nome") String nome,
            @FormParam("cognome") String cognome,
            @FormParam("pwd") String password,
            @FormParam("mail") String email) {

        Utente utente = new Utente(nome, cognome, password, email);
        utenteManager.save(utente);
    }

    @POST
    @Path("login")
    public Response login(Utente u) {
        if (u == null) {
            return Response.serverError()
                    .header("caused-by", "nessun dato per effettuare la login")
                    .build();
        }

        Utente finded = utenteManager.login(u.getEmail(), u.getPassword());
        if (finded == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .header("caused-by", "login failed")
                    .build();
        }
        System.out.println(finded + " loggato...");
        JsonObject json = Json
                .createObjectBuilder()
//                .add("id_token", finded.getIdUtente())
                .build();
        return Response.ok(json).build();
    }
}
