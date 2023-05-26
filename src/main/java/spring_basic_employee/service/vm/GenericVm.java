package spring_basic_employee.service.vm;

import lombok.Data;

@Data
public class GenericVm {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
