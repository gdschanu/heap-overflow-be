package hanu.gdsc.infrastructure.coderAuth.config;

import hanu.gdsc.domain.coderAuth.config.SessionConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SessionConfigImpl implements SessionConfig {
    public static String tokenSecret = "fake-secret-key";

    public SessionConfigImpl(Environment environment) {
        if (environment.getProperty("coderauth.session.tokensecret") != null) {
            tokenSecret = environment.getProperty("coderauth.session.tokensecret");
        }
    }

    @Override
    public String getTokenSecret() {
        return tokenSecret;
    }
}
