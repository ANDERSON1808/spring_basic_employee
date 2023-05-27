package spring_basic_employee.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import spring_basic_employee.service.config.ConfigExternal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class ExternalApiConfigurationTest {

    @Value("${employs.get-all-url}")
    private String routeGetAllEmploys;

    @Value("${employs.get-id-url}")
    private String routeGetByIdEmploys;

    private ExternalApiConfiguration externalApiConfiguration;

    @BeforeEach
    void setup() {
        externalApiConfiguration = new ExternalApiConfiguration();
    }

    @Test
    void testStartConfigExternalEmploys() {
        // Set up the configuration values
        externalApiConfiguration.routeGetAllEmploys = routeGetAllEmploys;
        externalApiConfiguration.routeGetByIdEmploys = routeGetByIdEmploys;

        // Create the ConfigExternal instance
        ConfigExternal configExternal = externalApiConfiguration.startConfigExternalEmploys();

        // Verify the values in the ConfigExternal instance
        assertEquals(routeGetAllEmploys, configExternal.getAll());
        assertEquals(routeGetByIdEmploys, configExternal.getById());
    }

    @Test
    void testGetRouteGetAllEmploys() {
        // Set up the configuration value
        externalApiConfiguration.routeGetAllEmploys = routeGetAllEmploys;

        // Verify the value returned by the getter method
        assertEquals(routeGetAllEmploys, externalApiConfiguration.getRouteGetAllEmploys());
    }

    @Test
    void testGetRouteGetByIdEmploys() {
        // Set up the configuration value
        externalApiConfiguration.routeGetByIdEmploys = routeGetByIdEmploys;

        // Verify the value returned by the getter method
        assertEquals(routeGetByIdEmploys, externalApiConfiguration.getRouteGetByIdEmploys());
    }
}
