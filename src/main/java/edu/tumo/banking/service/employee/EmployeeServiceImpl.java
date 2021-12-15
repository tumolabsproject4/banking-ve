package edu.tumo.banking.service.employee;


import com.google.gson.Gson;
import edu.tumo.banking.domain.employee.model.EmployeeModel;
import edu.tumo.banking.exception.NotFoundValueException;
import edu.tumo.banking.exception.ResourceNotValidException;
import edu.tumo.banking.repository.employee.EmployeeRepository;
import edu.tumo.banking.validation.EmployeeValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    Gson gson = new Gson();

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public EmployeeModel add(EmployeeModel employeeModel) {
        String json = gson.toJson(employeeModel);
        if (!EmployeeValidation.validateEmployeeModel(employeeModel)) {
            logger.info("Employee{} is not valid", json);
            throw new ResourceNotValidException("Employee is not valid");
        }
        logger.info("Employee{} is successfully added", json);
        return employeeRepository.add(employeeModel);
    }

    @Override
    @Transactional
    public EmployeeModel addImage(Long id, MultipartFile image) {
        Optional<EmployeeModel> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            logger.info("Employee with following id {} is not found", id);
            throw new NotFoundValueException("Employee with following id" + id + "is not found");
        }
        logger.info("Image{} is added", image);
        return employeeRepository.addImage(id, image);
    }

    @Override
    public List<EmployeeModel> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeModel> findStaffFromBank(Long id) {
        logger.info("Staff is found from bank{}", id);
        return employeeRepository.findStaffFromBank(id);
    }


    @Override
    public Optional<EmployeeModel> findById(Long id) {
        Optional<EmployeeModel> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            logger.info("Employee with the following id {} doesn't exist", id);
            throw new ResourceNotValidException("Employee with the following id" + id + "doesn't exist");
        }

        return employee;
    }

    @Override
    @Transactional
    public EmployeeModel update(EmployeeModel employeeModel) {
        String json = gson.toJson(employeeModel);
        if (!EmployeeValidation.validateEmployeeModel(employeeModel)) {
            logger.info("Employee {} is not valid", json);
            throw new ResourceNotValidException("Employee with id " + employeeModel.getEmployeeId() + "is not valid");
        }
        Optional<EmployeeModel> employee = employeeRepository.findById(employeeModel.getEmployeeId());
        if (employee.isEmpty()) {
            logger.info("Employee with the following id {} is not found", employeeModel.getEmployeeId());
            throw new NotFoundValueException("Employee with the following id " + employeeModel.getEmployeeId() + "is not found");

        }
        logger.info("Employee{} is updated", json);

        return employeeRepository.update(employeeModel).get();
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Long id) {
        EmployeeModel employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            logger.info("Employee{} is not found", id);
            throw new NotFoundValueException("Employee" + id + "is not found");
        }

        employeeRepository.deleteEmployeeById(id);
        logger.info("Employee{} is deleted", id);

    }

    @Override
    @Transactional
    public void deleteImageByEmployeeId(Long id) {
        EmployeeModel employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            logger.info("Employee {} is not found", id);
            throw new NotFoundValueException("Bank" + id + "is not found");
        }

        employeeRepository.deleteImageByEmployeeId(id);
        logger.info("Image of employee{} is deleted", id);
    }
}

