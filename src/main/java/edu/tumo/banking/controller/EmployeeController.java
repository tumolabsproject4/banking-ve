package edu.tumo.banking.controller;

import edu.tumo.banking.domain.employee.model.EmployeeModel;
import edu.tumo.banking.validation.EmployeeValidation;
import edu.tumo.banking.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeValidation employeeValidation;

    @Autowired
    public EmployeeController(EmployeeService employeeService,EmployeeValidation employeeValidation){
        this.employeeService = employeeService;
        this.employeeValidation=employeeValidation;
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> addEmployee(@Valid @RequestBody EmployeeModel employee){
        if(!(employeeValidation.validateForNull(employee)))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeService.add(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeModel>> findemployees(){
        List<EmployeeModel> employee = employeeService.findAll();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> findEmployeeById(@PathVariable Long id) {
        Optional<EmployeeModel> model=employeeService.findById(id);
        return new ResponseEntity<>(model.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmployeeModel> updateEmployee(@Valid @RequestBody EmployeeModel updatedEmployee){
        if(!(employeeValidation.validateForNull(updatedEmployee)))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeService.update(updatedEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeModelById(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
