package server.dao;

import model.dao.ListDao;
import model.dao.provider.EmptyProvider;
import model.Participant;

public class ParticipantServiceDao extends ListDao<Participant> {

    public static final Participant ADMIN;

    private static final ParticipantServiceDao instance;

    static {
        instance = new ParticipantServiceDao();
        ADMIN = instance.addParticipant("admin", "dmina", "Dmin Dminych", "Admin");
    }

    public static ParticipantServiceDao getInstance() {
        return instance;
    }

    public ParticipantServiceDao() {
        super(new EmptyProvider<>());
    }

    @Override
    public Class<Participant> getItemClass() {
        return Participant.class;
    }

    public Participant addParticipant(String login, String password, String fio, String function) {
        Participant participant = new Participant(getNewInstanceId(), login, password, fio, function);
        addItem(participant);
        return participant;
    }

    public Participant getParticipant(String login, String password) {
        Participant filtered = filterItem(
                participant -> (
                        participant.getLogin().equals(login)
                                && participant.getPassword().equals(password)
                )
        );

        return filtered;
    }
}
