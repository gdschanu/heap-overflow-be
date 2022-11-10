package hanu.gdsc.infrastructure.systemAdminAuth.config;

import hanu.gdsc.domain.systemAdminAuth.config.SessionSystemAdminConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SessionSystemAdminConfigImpl implements SessionSystemAdminConfig {
    public static String tokenSecret = "fake-secret-key";

    public SessionSystemAdminConfigImpl(Environment environment) {
        if (environment.getProperty("systemadminauth.sessionsystemadmin.tokensecret") != null) {
            tokenSecret = environment.getProperty("systemadminauth.sessionsystemadmin.tokensecret");
        }
    }

    @Override
    public String getTokenSecret() {
        return tokenSecret;
    }
}
