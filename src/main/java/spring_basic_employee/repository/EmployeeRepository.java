package spring_basic_employee.repository;

import spring_basic_employee.domain.Employee;
import com.mashape.unirest.http.exceptions.UnirestException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface EmployeeRepository extends EmployeeRepositoryInternal {

    @Override
    Flux<Employee> findAllEmploys(Optional<Long> id) throws UnirestException;

    @Override
    Mono<Employee> findByOneId(Long id) throws UnirestException;
}

interface EmployeeRepositoryInternal {
    Flux<Employee> findAllEmploys(Optional<Long> id) throws UnirestException;
    Mono<Employee> findByOneId(Long id) throws UnirestException;
}
