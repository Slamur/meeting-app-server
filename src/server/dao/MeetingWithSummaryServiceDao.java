package server.dao;

import model.dao.ListDao;
import model.dao.provider.EmptyProvider;
import model.Meeting;
import model.MeetingWithSummary;

public class MeetingWithSummaryServiceDao extends ListDao<MeetingWithSummary> {

    private static final MeetingWithSummaryServiceDao instance;

    static {
        instance = new MeetingWithSummaryServiceDao();
    }

    public static MeetingWithSummaryServiceDao getInstance() {
        return instance;
    }

    public MeetingWithSummaryServiceDao() {
        super(new EmptyProvider<>());
    }

    @Override
    public Class<MeetingWithSummary> getItemClass() {
        return MeetingWithSummary.class;
    }

    public MeetingWithSummary addMeetingWithSummary(Meeting meeting, String summary) {
        MeetingWithSummary meetingWithSummary = new MeetingWithSummary(meeting, summary);
        addItem(meetingWithSummary);
        return meetingWithSummary;
    }

    public MeetingWithSummary filterMeetingBySubstring(String substring) {
        return filterItem(
                meetingWithSummary -> (
                        meetingWithSummary.getMeeting().getTitle().contains(substring)
                                || meetingWithSummary.getSummary().contains(substring)
                )
        );
    }
}
