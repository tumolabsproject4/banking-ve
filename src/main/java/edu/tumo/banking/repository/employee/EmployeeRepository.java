package edu.tumo.banking.repository.employee;

import edu.tumo.banking.domain.employee.model.EmployeeModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository<EmployeeModel, Long>{

    EmployeeModel add(EmployeeModel employeeModel);

    EmployeeModel addImage(Long id, MultipartFile image);

    List<EmployeeModel> findAll();

    List<EmployeeModel> findStaffFromBank(Long id);

    List<EmployeeModel> findEmployeesFromDepartment(Long id,String department);

    Optional<EmployeeModel> findById(Long id);

    Optional<EmployeeModel> update(EmployeeModel employeeModel);

    void deleteEmployeeById(Long id );

    void deleteImageByEmployeeId(Long id);
}
