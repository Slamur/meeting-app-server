package server.service;

import server.dao.ParticipantServiceDao;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

public class AuthService {
    public boolean authenticate(String authCredentials) {

        if (null == authCredentials)
            return false;

        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                + " ", "");

        String loginAndPassword = null;

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(
                    encodedUserPassword);
            loginAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        final StringTokenizer tokenizer = new StringTokenizer(
                loginAndPassword, ":");

        final String login = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        // we have fixed the userid and password as admin
        // call some UserService/LDAP here

        boolean authenticationStatus =
                ParticipantServiceDao.getInstance().getParticipant(login, password) != null;

        return authenticationStatus;
    }
}
