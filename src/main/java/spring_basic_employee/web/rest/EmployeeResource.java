package spring_basic_employee.web.rest;

import spring_basic_employee.domain.Employee;
import spring_basic_employee.service.EmployeeService;

import java.util.Optional;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing {@link Employee}.
 */
@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private final EmployeeService employeeService;


    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * {@code GET  /employees} : get all the employees.
     *
     * @param request a {@link ServerHttpRequest} request.
     * @param id      the name parameter for searching employees (optional).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employees in body,
     * or an error response if no records are found or an internal error occurs.
     */
    @GetMapping("/employees")
    public Mono<ResponseEntity<?>> getAllEmployees(ServerHttpRequest request, @RequestParam Optional<Long> id) throws UnirestException {
        log.debug("REST request to get all Employees");
        return employeeService
            .findAll(id)
            .collectList()
            .flatMap(employees -> {
                if (employees.isEmpty()) {
                    return Mono.just(ResponseEntity.notFound().build());
                } else {
                    return Mono.just(ResponseEntity.ok().body(employees));
                }
            })
            .onErrorResume(throwable -> {
                log.error("Error retrieving employees", throwable);
                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Servidor externo supero el limite, intentalo mas tarde."));
            });
    }

    /**
     * {@code GET  /employees/:id} : get the "id" employee.
     *
     * @param id the id of the employeeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeeDTO,
     * or with status {@code 404 (Not Found)} if the employee is not found,
     * or with status {@code 500 (Internal Server Error)} if an error occurs.
     */
    @GetMapping("/employees/{id}")
    public Mono<ResponseEntity<Object>> getEmployee(@PathVariable Long id) throws UnirestException {
        log.debug("REST request to get Employee: {}", id);
        return employeeService
            .findOne(id)
            .map(employeeDTO -> ResponseEntity.ok().body(employeeDTO))
            .defaultIfEmpty(ResponseEntity.notFound().build())
            .onErrorResume(throwable -> {
                log.error("Error retrieving employee", throwable);
                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Servidor externo superó el límite, inténtalo de nuevo más tarde."));
            });
    }

}
