package server.service;

import model.Meeting;
import model.Participant;
import server.dao.MeetingServiceDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/meetings")
public class MeetingJsonService extends ItemJsonService<Meeting, MeetingServiceDao> {

    private static final String ADD_PARTICIPANT_SUCCESS_RESPONSE = "Participant has been added to meeting";
    private static final String ADD_PARTICIPANT_ALREADY_RESPONSE = "Participant has been already added";

    @Override
    protected MeetingServiceDao createDao() {
        return MeetingServiceDao.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/filter/date/{startDate}/{endDate}")
    public List<Meeting> getMeetingsByDate(@PathParam("startDate") long startDate, @PathParam("endDate") long endDate) {
        return dao.filterMeetingsByDate(startDate, endDate);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/put/participant/{index}/{participant}")
    public Response addParticipant(@PathParam("index") int index, @PathParam("participant") String participantString) {
        Participant participant = gson.fromJson(participantString, Participant.class);
        boolean addResult = dao.getItem(index).addParticipant(participant);

        String response = (addResult ? ADD_PARTICIPANT_SUCCESS_RESPONSE : ADD_PARTICIPANT_ALREADY_RESPONSE);
        return Response.ok().entity(response).build();
    }
}
