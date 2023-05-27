package spring_basic_employee.config;


import spring_basic_employee.service.config.ConfigExternal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalApiConfiguration {
    private final Logger log = LoggerFactory.getLogger(ExternalApiConfiguration.class);

    @Value("${employs.get-all-url}")
    String routeGetAllEmploys;

    @Value("${employs.get-id-url}")
    String routeGetByIdEmploys;

    @Bean
    public ConfigExternal startConfigExternalEmploys() {
        log.debug("Start config external data");
        return new ConfigExternal(routeGetAllEmploys, routeGetByIdEmploys);
    }

    public String getRouteGetAllEmploys() {
        return routeGetAllEmploys;
    }

    public String getRouteGetByIdEmploys() {
        return routeGetByIdEmploys;
    }
}
