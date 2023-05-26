package spring_basic_employee.service.vm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseEmploysVm extends GenericVm {

    private List<EmployeesVm> data;

    public List<EmployeesVm> getData() {
        return data;
    }
}
