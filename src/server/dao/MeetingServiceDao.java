package server.dao;

import model.dao.ListDao;
import model.dao.provider.EmptyProvider;
import model.Meeting;
import model.MeetingPriority;

import java.util.Date;
import java.util.List;

public class MeetingServiceDao extends ListDao<Meeting> {

    private static final MeetingServiceDao instance;

    static {
        instance = new MeetingServiceDao();
    }

    public static MeetingServiceDao getInstance() {
        return instance;
    }

    protected MeetingServiceDao() {
        super(new EmptyProvider<>());
    }

    @Override
    public Class<Meeting> getItemClass() {
        return Meeting.class;
    }

    public Meeting addMeeting(String title, Date startDate, Date endDate, int priority) {
        Meeting meeting = new Meeting(getNewInstanceId(), title, startDate, endDate, MeetingPriority.values()[priority]);
        addItem(meeting);
        return meeting;
    }

    public List<Meeting> filterMeetingsByDate(long startDate, long endDate) {
        List<Meeting> filteredMeetings = filterItems(
                meeting -> (
                        meeting.getStartDate().getTime() >= startDate
                                && meeting.getEndDate().getTime() <= endDate
                )
        );

        return filteredMeetings;
    }
}
