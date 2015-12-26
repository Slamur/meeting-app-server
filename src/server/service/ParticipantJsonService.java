package server.service;

import model.Participant;
import server.dao.ParticipantServiceDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/participants")
public class ParticipantJsonService extends ItemJsonService<Participant, ParticipantServiceDao> {

    @Override
    protected ParticipantServiceDao createDao() {
        return ParticipantServiceDao.getInstance();
    }

    // TODO maybe with string?
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{login}/{password}")
    public Participant getParticipant(@PathParam("login") String login, @PathParam("password") String password) {
        return dao.getParticipant(login, password);
    }

    @PUT
    @Path("/put/{login}/{password}/{fio}/{function}")
    public Response addParticipant(
            @PathParam("login") String login,
            @PathParam("password") String password,
            @PathParam("fio") String fio,
            @PathParam("function") String function) {
        dao.addParticipant(login, password, fio, function);

        return Response.ok().entity(ItemJsonService.ITEM_ADDED_RESPONSE).build();
    }
}
