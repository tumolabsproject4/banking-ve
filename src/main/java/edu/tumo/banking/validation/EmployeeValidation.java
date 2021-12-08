package edu.tumo.banking.validation;

import edu.tumo.banking.domain.employee.model.EmployeeModel;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidation {
    public boolean validateForNull(EmployeeModel employeeModel) {
        if (employeeModel.getEmployeeId() == null) {
            System.out.println("ID of employee is null  ");
            return false;
        }
        if (employeeModel.getFirstName() == null) {
            System.out.println("Firstname of employee is null ");
            return false;
        }
        if (employeeModel.getLastName()==null){
            System.out.println("Lastname of employee is null ");
            return false;
        }
        if (employeeModel.getAge()==null){
            System.out.println("Age of employee is null ");
            return false;
        }
        if (employeeModel.getSalary()==null){
            System.out.println("Salary of employee is null ");
            return false;
        }
        if (employeeModel.getAddress()==null){
            System.out.println("Address of employee is null ");
            return false;
        }
        if (employeeModel.getDepartment()==null){
            System.out.println("Department of employee is null ");
            return false;
        }
        if (employeeModel.getEmployeeStatus()==null){
            System.out.println("EmployeeStatus of employee is null ");
            return false;
        }
        if (employeeModel.getBankId()==null){
            System.out.println("BankId of employee is null ");
            return false;
        }
        return true;
    }
}
