package edu.tumo.banking.service.employee;


import edu.tumo.banking.domain.employee.model.EmployeeModel;
import edu.tumo.banking.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeModel> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeModel add(EmployeeModel employeeModel) {
        return (EmployeeModel) employeeRepository.add(employeeModel);
    }

    @Override
    public EmployeeModel update(EmployeeModel employeeModel) {
        return (EmployeeModel) employeeRepository.update(employeeModel);
    }

    @Override
    public Optional<EmployeeModel> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteEmployeeModelById(Long id) {
        employeeRepository.deleteEmployeeModelById(id);
    }
}
