package edu.tumo.banking.service.employee;

import edu.tumo.banking.domain.employee.model.EmployeeModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeModel add(EmployeeModel employeeModel);

    EmployeeModel addImage(Long id, MultipartFile image);

    List<EmployeeModel> findAll();

    List<EmployeeModel> findStaffFromBank(Long id);

    Optional<EmployeeModel> findById(Long id);

    EmployeeModel update(EmployeeModel employeeModel);

    void deleteEmployeeById(Long id);

    void deleteImageByEmployeeId(Long id);
}
