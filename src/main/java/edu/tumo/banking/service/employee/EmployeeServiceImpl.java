package edu.tumo.banking.service.employee;


import edu.tumo.banking.domain.employee.model.EmployeeModel;
import edu.tumo.banking.exception.NotFoundValueException;
import edu.tumo.banking.exception.ResourceNotValidException;
import edu.tumo.banking.repository.employee.EmployeeRepository;
import edu.tumo.banking.validation.EmployeeValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public EmployeeModel add(EmployeeModel employeeModel) {
        if(!EmployeeValidation.validateEmployeeModel(employeeModel)){
            logger.warn("Employee{} is not valid", employeeModel);
            throw new ResourceNotValidException("Employee is not valid");
        }
        logger.info("Employee{} is successfully added",employeeModel);
        return employeeRepository.add(employeeModel);
    }

    @Override
    @Transactional
    public EmployeeModel addImage(Long id, MultipartFile image) {
        Optional<EmployeeModel> employee = employeeRepository.findById(id);
        if(employee == null){
            logger.warn("Employee with following id {} is not found", id);
            throw new NotFoundValueException("Employee with following id" + id + "is not found");
        }
        logger.info("Image{} is added", image);
        return employeeRepository.addImage(id, image);
    }

    @Override
    @Transactional
    public List<EmployeeModel> findAll() {
        logger.info("Employees are found");
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public List<EmployeeModel> findStaffFromBank(Long id) {
        logger.info("Staff is found from bank{}", id);
        return employeeRepository.findStaffFromBank(id);
    }

    @Override
    public List<EmployeeModel> findEmployeesFromDepartment(Long id, String department) {
        logger.info("Employees from department{} from bank{} are found", department, id);
        return employeeRepository.findEmployeesFromDepartment(id, department);
    }


    @Override
    @Transactional
    public Optional<EmployeeModel> findById(Long id) {
        Optional<EmployeeModel> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            logger.warn("Employee with the following id {} doesn't exist", id);
            throw new ResourceNotValidException("Employee with the following id" + id + "doesn't exist");
        }

        return employee;
    }

    @Override
    @Transactional
    public EmployeeModel update(EmployeeModel employeeModel) {
        if(!EmployeeValidation.validateEmployeeModel(employeeModel)){
            logger.warn("Employee {} is not valid",employeeModel);
            throw  new ResourceNotValidException("Employee with id " + employeeModel.getEmployeeId() + "is not valid");
        }
        Optional<EmployeeModel> employee = employeeRepository.findById(employeeModel.getEmployeeId());
        if(employee == null){
            logger.warn("Employee with the following id {} is not found",employeeModel.getEmployeeId());
            throw new NotFoundValueException("Employee with the following id " +employeeModel.getEmployeeId()  + "is not found");

        }
        logger.info("Employee is updated",employeeModel);

        return employeeRepository.update(employeeModel).get();
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Long id) {
        EmployeeModel employee =  employeeRepository.findById(id).orElse(null);
        if(employee == null){
            logger.warn("Employee{} is not found", id);
            throw new NotFoundValueException("Employee"+ id +"is not found");
        }
        logger.info("Employee{} is deleted",id);
        employeeRepository.deleteEmployeeById(id);

    }

    @Override
    @Transactional
    public void deleteImageByEmployeeId(Long id) {
        EmployeeModel employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            logger.warn("Employee {} is not found", id);
            throw new NotFoundValueException("Bank"+ id +"is not found");
        }
        logger.info("Image of employee{} is deleted",id);
        employeeRepository.deleteImageByEmployeeId(id);
    }
}

