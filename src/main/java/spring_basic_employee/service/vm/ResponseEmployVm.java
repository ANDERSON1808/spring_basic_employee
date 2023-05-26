package spring_basic_employee.service.vm;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseEmployVm extends GenericVm {

    private EmployeesVm data;

    public EmployeesVm getData() {
        return data;
    }

}
