package spring_basic_employee.domain;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;


public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull(message = "must not be null")
    @Column("name")
    private String name;

    @NotNull(message = "must not be null")
    @Column("document")
    private Long document;

    @NotNull(message = "must not be null")
    @Column("salary")
    private Long salary;

    @Column("other_names")
    private String otherNames;

    @Column("annual_salary")
    private Long annualSalary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDocument() {
        return this.document;
    }

    public Employee document(Long document) {
        this.document = document;
        return this;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public Long getSalary() {
        return this.salary;
    }

    public Employee salary(Long salary) {
        this.salary = salary;
        return this;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getOtherNames() {
        return this.otherNames;
    }

    public Employee otherNames(String otherNames) {
        this.otherNames = otherNames;
        return this;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public Long getAnnualSalary() {
        return this.annualSalary;
    }

    public Employee annualSalary(Long annualSalary) {
        this.annualSalary = annualSalary;
        return this;
    }

    public void setAnnualSalary(Long annualSalary) {
        this.annualSalary = annualSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Employee(Long id, String name, Long salary, Long annualSalary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.annualSalary = annualSalary;
    }

    public Employee() {
        // Json
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", document=" + getDocument() +
            ", salary=" + getSalary() +
            ", otherNames='" + getOtherNames() + "'" +
            ", annualSalary=" + getAnnualSalary() +
            "}";
    }
}
