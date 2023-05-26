package spring_basic_employee.service.dto;

import spring_basic_employee.domain.Employee;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link Employee} entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String name;

    @NotNull(message = "must not be null")
    private Long document;

    @NotNull(message = "must not be null")
    private Long salary;

    private String otherNames;

    private Long annualSalary;

    public EmployeeDTO() {
        // Json
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public Long getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(Long annualSalary) {
        this.annualSalary = annualSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeDTO)) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, employeeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", document=" + getDocument() +
            ", salary=" + getSalary() +
            ", otherNames='" + getOtherNames() + "'" +
            ", annualSalary=" + getAnnualSalary() +
            "}";
    }
}
