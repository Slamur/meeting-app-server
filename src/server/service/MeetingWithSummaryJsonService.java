package server.service;

import model.Meeting;
import model.MeetingWithSummary;
import server.dao.MeetingServiceDao;
import server.dao.MeetingWithSummaryServiceDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Path("/summaries")
public class MeetingWithSummaryJsonService extends ItemJsonService<MeetingWithSummary, MeetingWithSummaryServiceDao> {

    private static final String DATE_ERROR_RESPONSE = "Error of date parsing";

    @Override
    protected MeetingWithSummaryServiceDao createDao() {
        return MeetingWithSummaryServiceDao.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/filter/substring/{substring}")
    public MeetingWithSummary getMeetingBySubstring(@PathParam("substring") String substring) {
        return dao.filterMeetingBySubstring(substring);
    }

    @PUT
    @Path("/put/{title}/{summary}/{startDate}/{endDate}/{priority}")
    public Response addMeeting(@PathParam("title") String title,
                               @PathParam("summary") String summary,
                               @PathParam("startDate") String startDate,
                               @PathParam("endDate") String endDate,
                               @PathParam("priority") int priority) {

        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        startDate = startDate.replace("-", "/");
        endDate = endDate.replace("-", "/");

        try {
            Meeting meeting = MeetingServiceDao.getInstance().addMeeting(title, sdf.parse(startDate), sdf.parse(endDate), priority);
            dao.addMeetingWithSummary(meeting, summary);
        } catch (ParseException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(DATE_ERROR_RESPONSE).build();
        }

        return Response.ok().entity(ITEM_ADDED_RESPONSE).build();
    }
}
