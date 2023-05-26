package spring_basic_employee.service.mapper;

import spring_basic_employee.service.dto.EmployeeDTO;
import spring_basic_employee.domain.Employee;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {}
