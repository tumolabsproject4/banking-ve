package edu.tumo.banking.controller;

import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.domain.employee.model.EmployeeModel;
import edu.tumo.banking.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    @PostMapping("/addEmployees")
    public String addEmployee(@RequestBody EmployeeModel employee,Model model) {
        EmployeeModel employeeModel = employeeService.add(employee);
        model.addAttribute("employee",employeeModel);
        return "employees";
    }

    @PostMapping ("/addEmployeeImage")
    public String addImage(@PathVariable Long id, @RequestParam("image") MultipartFile image, Model model) {
        EmployeeModel employeeModel = employeeService.addImage(id,image);
        model.addAttribute("employee",employeeModel);
        return "employees";
    }

    @GetMapping("/allEmployees")
    public String findEmployees(Model model) {
        List<EmployeeModel> employee = employeeService.findAll();
        model.addAttribute("employee",employee);
        return "employees";
    }

    @GetMapping("/{id}/staff")
    public String findStaff(@PathVariable Long id,Model model) {
        List<EmployeeModel> employees = employeeService.findStaffFromBank(id);
        model.addAttribute("employees",employees);
        return "employees";
    }


    @GetMapping("/{id}")
    public String findEmployeeById(@PathVariable Long id,Model model) {
        Optional<EmployeeModel> employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        return "oneEmployee";
    }

    @PutMapping("/updateEmployees")
    public String updateEmployee(@RequestBody EmployeeModel updatedEmployee, Model model) {
        EmployeeModel employeeModel=employeeService.update(updatedEmployee);
        model.addAttribute("employee",employeeModel);
        return "employees";
    }

    @DeleteMapping("/{id}/deleteEmployee")
    public String  deleteEmployeeById(@PathVariable Long id, Model model) {
        employeeService.deleteEmployeeById(id);
        model.addAttribute("employee",null);
        return "employees";
    }

    @DeleteMapping("/{id}/deleteImage")
    public String deleteImageById(@PathVariable Long id,Model model){
        employeeService.deleteEmployeeById(id);
        model.addAttribute("employee",null);
        return "employees";
    }

//    private BankModel getUpdateBankModel(final MultiValueMap<String, String> paramMap) {
//        final var address = paramMap.getFirst("address");
//        final var bankName = paramMap.getFirst("bankName");
//        return new BankModel(bankName, address);
//    }
//
//    private BankModel getCreateBankModel(final MultiValueMap<String, String> paramMap) {
//        final var address = paramMap.getFirst("address");
//        final var bankName = paramMap.getFirst("bankName");
//        return new BankModel(bankName, address);
//    }
}
