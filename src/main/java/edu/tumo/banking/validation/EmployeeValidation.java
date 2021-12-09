package edu.tumo.banking.validation;

import edu.tumo.banking.domain.employee.model.EmployeeModel;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidation {
    public static boolean validateEmployeeModel(EmployeeModel employeeModel) {
        if (employeeModel != null) {
            if (employeeModel.getFirstName()!=null && !employeeModel.getFirstName().isEmpty()
            && employeeModel.getLastName()!=null && !employeeModel.getLastName().isEmpty()
            && employeeModel.getAge()!=null && employeeModel.getAge()>0
            && employeeModel.getSalary()!=null && employeeModel.getSalary()>0
            && employeeModel.getAddress() !=null && !employeeModel.getAddress().isEmpty()
            && employeeModel.getDepartment() != null && !employeeModel.getDepartment().isEmpty()
            && employeeModel.getEmployeeStatus() != null && employeeModel.getBankId()!=null
                    && employeeModel.getBankId()>0) {
                return true;
            }
            return false;
        }
        return false;
    }
}
