package spring_basic_employee.service.vm;


import lombok.Data;

@Data
public class EmployeesVm {

    private Long id;

    private String employee_name;

    private Long employee_salary;

    private Long employee_age;

    private String profile_image;

    public EmployeesVm(Long id, String employee_name, Long employee_salary, Long employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

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
