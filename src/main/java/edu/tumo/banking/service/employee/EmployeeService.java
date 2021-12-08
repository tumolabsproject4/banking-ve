package edu.tumo.banking.service.employee;

import edu.tumo.banking.domain.employee.model.EmployeeModel;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeModel> findAll();
    EmployeeModel add(EmployeeModel employeeModel);
    EmployeeModel update(EmployeeModel employeeModel);
    Optional<EmployeeModel> findById(Long id);
    void deleteEmployeeModelById(Long id );
}
