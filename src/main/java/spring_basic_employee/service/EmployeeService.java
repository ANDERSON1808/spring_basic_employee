package spring_basic_employee.service;

import spring_basic_employee.repository.EmployeeRepositoryInternalImpl;
import spring_basic_employee.service.dto.EmployeeDTO;
import spring_basic_employee.service.mapper.EmployeeMapper;
import spring_basic_employee.domain.Employee;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Employee}.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "employees")
public class EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeMapper employeeMapper;

    private final EmployeeRepositoryInternalImpl employeeRepository;

    public EmployeeService(EmployeeMapper employeeMapper, EmployeeRepositoryInternalImpl employeeRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    @Cacheable
    @Transactional(readOnly = true)
    public Flux<EmployeeDTO> findAll(Optional<Long> id) throws UnirestException {
        log.debug("Request to get all Employees: {}", id);
        return employeeRepository.findAllEmploys(id).map(employeeMapper::toDto);
    }

    @Cacheable(key = "#id")
    @Transactional(readOnly = true)
    public Mono<Object> findOne(Long id) throws UnirestException {
        log.debug("Request to find one id: {}", id);
        return employeeRepository.findByOneId(id).map(employeeMapper::toDto);
    }
}
