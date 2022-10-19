package hanu.gdsc.coderAuth.config;

import org.springframework.core.env.Environment;

public class SessionConfig {
    public static String TOKEN_SECRET = "fake-secret-key";

    public SessionConfig(Environment environment) {
        if (environment.getProperty("coderauth.session.tokensecret") != null) {
            TOKEN_SECRET = environment.getProperty("coderauth.session.tokensecret");
        }
    }
}
