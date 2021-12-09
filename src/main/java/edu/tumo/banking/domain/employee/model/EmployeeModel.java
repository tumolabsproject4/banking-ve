package edu.tumo.banking.domain.employee.model;

import java.util.Arrays;
import java.util.Objects;

public class EmployeeModel {
    Long employeeId;
    String firstName;
    String lastName;
    Integer age;
    Float salary;
    String address;
    String department;
    Boolean employeeStatus;
    Long bankId;
    byte[] image;

    public EmployeeModel() {
    }
    public EmployeeModel(Long employeeId, String firstName, String lastName, Integer age, Float salary, String address, String department, Boolean employeeStatus, Long bankId, byte[] image) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.address = address;
        this.department = department;
        this.employeeStatus = employeeStatus;
        this.bankId = bankId;
        this.image = image;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(Boolean employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public byte[] getImage() {
        return image;
    }

    public EmployeeModel setImage(byte[] image) {
        this.image = image;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeModel that = (EmployeeModel) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(age, that.age) && Objects.equals(salary, that.salary) && Objects.equals(address, that.address) && Objects.equals(department, that.department) && Objects.equals(employeeStatus, that.employeeStatus) && Objects.equals(bankId, that.bankId) && Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(employeeId, firstName, lastName, age, salary, address, department, employeeStatus, bankId);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                ", employeeStatus=" + employeeStatus +
                ", bankId=" + bankId +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
