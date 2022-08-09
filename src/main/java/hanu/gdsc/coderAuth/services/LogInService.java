package hanu.gdsc.coderAuth.services;

import lombok.Builder;

public interface LogInService {

    @Builder
    public static class Output {
        public String username;
        public String coderId;
        public String token;
    }

    public Output logInService(String usernameOrEmail, String password);
}
