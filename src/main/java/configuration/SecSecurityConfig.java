package configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:bean/webSecurityConfig.xml" })
public class SecSecurityConfig {
    public SecSecurityConfig() {
        super();
    }
}
