package spring_basic_employee.repository;

import spring_basic_employee.domain.Employee;
import spring_basic_employee.repository.rowmapper.EmployeeRowMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


@SuppressWarnings("unused")
@Repository
public class EmployeeRepositoryInternalImpl implements EmployeeRepository {

    private final EmployeeRowMapper employeeMapper;

    public EmployeeRepositoryInternalImpl(EmployeeRowMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }


    @Override
    public Flux<Employee> findAllEmploys(Optional<Long> id) throws UnirestException {
        return process(id);
    }

    @Override
    public Mono<Employee> findByOneId(Long id) throws UnirestException {
        return process(id);
    }


    private Flux<Employee> process(Optional<Long> id) throws UnirestException {
        return employeeMapper.apply(id);
    }

    private Mono<Employee> process(Long id) throws UnirestException {
        return employeeMapper.apply(id);
    }
}
