package edu.tumo.banking.controller;

import edu.tumo.banking.domain.employee.model.EmployeeModel;
import edu.tumo.banking.validation.EmployeeValidation;
import edu.tumo.banking.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeValidation employeeValidation) {
        this.employeeService = employeeService;
    }

    @PostMapping("/addemployee")
    public ResponseEntity<EmployeeModel> addEmployee(@RequestBody EmployeeModel employee) {
        return new ResponseEntity<>(employeeService.add(employee), HttpStatus.CREATED);
    }

    @PutMapping("/addimage")
    public ResponseEntity<EmployeeModel> addImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        return new ResponseEntity<>(employeeService.addImage(id, image), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeModel>> findEmployees() {
        List<EmployeeModel> employee = employeeService.findAll();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("{id/staff}")
    public ResponseEntity<List<EmployeeModel>> findStaff(@PathVariable Long id) {
        List<EmployeeModel> employee = employeeService.findStaffFromBank(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> findEmployeeById(@PathVariable Long id) {
        Optional<EmployeeModel> model = employeeService.findById(id);
        return new ResponseEntity<>(model.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmployeeModel> updateEmployee(@RequestBody EmployeeModel updatedEmployee) {
        return new ResponseEntity<>(employeeService.update(updatedEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}/image")
    public ResponseEntity<?> deleteImageById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
