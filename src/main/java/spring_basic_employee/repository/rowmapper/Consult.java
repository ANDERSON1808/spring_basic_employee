package spring_basic_employee.repository.rowmapper;


import spring_basic_employee.domain.Employee;
import com.mashape.unirest.http.exceptions.UnirestException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface Consult {
    Flux<Employee> apply(Optional<Long> id) throws UnirestException;
    Mono<Employee> apply(Long id)throws UnirestException;
}
