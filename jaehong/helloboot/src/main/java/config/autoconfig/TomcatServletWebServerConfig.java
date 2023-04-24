package config.autoconfig;

import config.ConditionalMyOnClass;
import config.EnableMyConfigurationProperties;
import config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@EnableMyConfigurationProperties(value = ServerProperties.class)
public class TomcatServletWebServerConfig {
    @Bean("TomcatServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(ServerProperties serverProperties) {

        System.out.println(serverProperties.getContextPath());
        System.out.println(serverProperties.getPort());
        return new TomcatServletWebServerFactory();
    }
}
