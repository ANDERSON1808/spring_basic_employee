package spring_basic_employee.repository.rowmapper;


import spring_basic_employee.service.UnirestService;
import spring_basic_employee.domain.Employee;
import spring_basic_employee.service.vm.EmployeesVm;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
public class EmployeeRowMapper implements Consult {
    private final UnirestService unirestService;

    public EmployeeRowMapper(UnirestService unirestService) {
        this.unirestService = unirestService;
    }

    @Override
    public Flux<Employee> apply(Optional<Long> id) throws UnirestException {
        return retrieveEmployees(id)
            .map(this::mapToEmployee);
    }

    @Override
    public Mono<Employee> apply(Long id) throws UnirestException {
        return retrieveEmployee(id)
            .map(this::mapToEmployee);
    }

    private Flux<EmployeesVm> retrieveEmployees(Optional<Long> id) throws UnirestException {
        return unirestService.sendGetAll(id);
    }

    private Mono<EmployeesVm> retrieveEmployee(Long id) throws UnirestException {
        return unirestService.sendGetById(id);
    }

    private Employee mapToEmployee(EmployeesVm employeesVm) {
        return new Employee(
            employeesVm.getId(),
            employeesVm.getEmployee_name(),
            employeesVm.getEmployee_salary(),
            salaryAnnual(employeesVm.getEmployee_salary())
        );
    }

    private Long salaryAnnual(Long salary) {
        return salary != null ? salary * 12 : 0;
    }
}
