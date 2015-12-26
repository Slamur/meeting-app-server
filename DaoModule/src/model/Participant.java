package model;

import model.dao.Item;

public class Participant extends Item {

    private final String login, password;

    private final String fio;
    private final String function;

    public Participant(long id, String login, String password, String fio, String function) {
        super(id);

        this.login = login;
        this.password = password;

        this.fio = fio;
        this.function = function;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFio() {
        return fio;
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return getFio() + " " + getFunction();
    }
}
