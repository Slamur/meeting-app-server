package model;

import model.dao.Item;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Meeting extends Item {

    private final String title;
    private final Date startDate, endDate;
    private final Set<Participant> participants;
    private final MeetingPriority priority;

    public Meeting(long id, String title, Date startDate, Date endDate, MeetingPriority priority) {
        this(id, title, startDate, endDate, new HashSet<Participant>(), priority);
    }

    public Meeting(long id, String title, Date startDate, Date endDate, Set<Participant> participants, MeetingPriority priority) {
        super(id);

        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public MeetingPriority getPriority() {
        return priority;
    }

    public boolean addParticipant(Participant participant) {
        return participants.add(participant);
    }

    public boolean removeParticipant(Participant participant) {
        return participants.remove(participant);
    }
}
