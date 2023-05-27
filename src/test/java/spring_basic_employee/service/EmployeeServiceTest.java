package spring_basic_employee.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Flux;
import spring_basic_employee.IntegrationTest;
import spring_basic_employee.domain.Employee;
import spring_basic_employee.service.dto.EmployeeDTO;

import java.util.Optional;

@TestPropertySource(locations = "classpath:test.properties")
@IntegrationTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void init() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("Tiger Nixon");
        employee.setSalary(125000L);
    }

    @Test
    void testGetAllEmploysSuccess() throws UnirestException {
        Flux<EmployeeDTO> empleados = employeeService.findAll(Optional.empty());
        Assertions.assertNotNull(empleados);
        empleados.map(employeeDTO -> {
            Assertions.assertNotNull(employeeDTO);
            Assertions.assertNotNull(employeeDTO.getId());
            Assertions.assertNotNull(employeeDTO.getName());
            Assertions.assertNotNull(employeeDTO.getSalary());
            return employeeDTO;
        }).subscribe();
    }

    @Test
    void testFindByIDEmploySuccess() throws UnirestException {
        EmployeeDTO employ = (EmployeeDTO) employeeService.findOne(1L).block();
        Assertions.assertNotNull(employ);
        Assertions.assertEquals("Tiger Nixon", employ.getName(), "Validate the name of the employee");
        Assertions.assertEquals(1L, employ.getId(), "Validate the id of the employee");
        Assertions.assertEquals(320800L, employ.getSalary(), "Validate the salary of the employee");
    }

    @Test
    void testValidateAnualSalary() throws UnirestException {
        EmployeeDTO employ = (EmployeeDTO) employeeService.findOne(1L).block();
        Assertions.assertNotNull(employ);
        Assertions.assertEquals(3849600L, employ.getAnnualSalary(), "Validate the annual salary calculation");
    }
}
