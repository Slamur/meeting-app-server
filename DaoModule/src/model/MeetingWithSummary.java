package model;

import model.dao.Item;

public class MeetingWithSummary extends Item {

    private final Meeting meeting;
    private final String summary;

    public MeetingWithSummary(Meeting meeting, String summary) {
        super(meeting.getId());

        this.meeting = meeting;
        this.summary = summary;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public String getSummary() {
        return summary;
    }
}
