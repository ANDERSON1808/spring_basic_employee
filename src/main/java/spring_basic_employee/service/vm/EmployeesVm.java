package spring_basic_employee.service.vm;


import lombok.Data;

@Data
public class EmployeesVm {

    private Long id;

    private String employee_name;

    private Long employee_salary;

    private Long employee_age;

    private String profile_image;

    public EmployeesVm() {
        // Json
    }

    public Long getId() {
        return id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public Long getEmployee_salary() {
        return employee_salary;
    }

    public Long getEmployee_age() {
        return employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

}
