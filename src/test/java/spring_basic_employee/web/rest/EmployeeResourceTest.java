package spring_basic_employee.web.rest;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring_basic_employee.IntegrationTest;
import spring_basic_employee.domain.Employee;
import spring_basic_employee.service.EmployeeService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:test.properties")
@IntegrationTest
@AutoConfigureWebTestClient
@WithMockUser
class EmployeeResourceTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private ServerHttpRequest serverHttpRequest;

    private EmployeeResource employeeResource;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        employeeResource = new EmployeeResource(employeeService);
    }

    @Test
    void testGetAllEmployees() throws UnirestException {
        // Mocking employeeService.findAll()
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        when(employeeService.findAll(any(Optional.class))).thenReturn(Flux.just(employee));

        // Test the endpoint
        Mono<ResponseEntity<?>> response = employeeResource.getAllEmployees(serverHttpRequest, Optional.empty());

        // Verify the response
        response.subscribe(result -> {
            assert result.getStatusCode() == HttpStatus.OK;
            assert result.getBody() != null;
            assert ((Iterable<?>) result.getBody()).iterator().hasNext();
            assert ((Iterable<?>) result.getBody()).iterator().next() instanceof Employee;
        });
    }

    @Test
    void testGetEmployee() throws UnirestException {
        // Mocking employeeService.findOne()
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        when(employeeService.findOne(1L)).thenReturn(Mono.just(employee));

        // Test the endpoint
        Mono<ResponseEntity<Object>> response = employeeResource.getEmployee(1L);

        // Verify the response
        response.subscribe(result -> {
            assert result.getStatusCode() == HttpStatus.OK;
            assert result.getBody() != null;
            assert result.getBody() instanceof Employee;
        });
    }

    // Test case for getAllEmployees when no records are found
    @Test
    void testGetAllEmployeesNoRecordsFound() throws UnirestException {
        // Mocking employeeService.findAll()
        when(employeeService.findAll(any(Optional.class))).thenReturn(Flux.empty());

        // Test the endpoint
        Mono<ResponseEntity<?>> response = employeeResource.getAllEmployees(serverHttpRequest, Optional.empty());

        // Verify the response
        response.subscribe(result -> {
            assert result.getStatusCode() == HttpStatus.NOT_FOUND;
        });
    }

    // Test case for getEmployee when employee is not found
    @Test
    void testGetEmployeeNotFound() throws UnirestException {
        // Mocking employeeService.findOne()
        when(employeeService.findOne(1L)).thenReturn(Mono.empty());

        // Test the endpoint
        Mono<ResponseEntity<Object>> response = employeeResource.getEmployee(1L);

        // Verify the response
        response.subscribe(result -> {
            assert result.getStatusCode() == HttpStatus.NOT_FOUND;
        });
    }

    // Test case for getAllEmployees when an internal server error occurs
    @Test
    void testGetAllEmployeesInternalServerError() throws UnirestException {
        // Mocking employeeService.findAll()
        when(employeeService.findAll(any(Optional.class))).thenReturn(Flux.error(new RuntimeException("Internal Server Error")));

        // Test the endpoint
        Mono<ResponseEntity<?>> response = employeeResource.getAllEmployees(serverHttpRequest, Optional.empty());

        // Verify the response
        response.subscribe(result -> {
            assert result.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;
        });
    }

    // Test case for getEmployee when an internal server error occurs
    @Test
    void testGetEmployeeInternalServerError() throws UnirestException {
        // Mocking employeeService.findOne()
        when(employeeService.findOne(1L)).thenReturn(Mono.error(new RuntimeException("Internal Server Error")));

        // Test the endpoint
        Mono<ResponseEntity<Object>> response = employeeResource.getEmployee(1L);

        // Verify the response
        response.subscribe(result -> {
            assert result.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;
        });
    }
}
