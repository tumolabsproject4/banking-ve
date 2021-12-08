package edu.tumo.banking.repository.employee;

import edu.tumo.banking.domain.employee.model.EmployeeModel;
import edu.tumo.banking.repository.mappers.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class EmployeeRepositoryImpl implements EmployeeRepository<EmployeeModel, Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeModel> findAll() {
        String sql = "SELECT * FROM employee";
        List<EmployeeModel> employees=jdbcTemplate.query(sql, new EmployeeRowMapper());
        return employees;
    }

    @Override
    public EmployeeModel add(EmployeeModel employeeModel) {
        //bankidi mas@ petqa harcnenq
        String sql= "INSERT INTO employee('first_name','last_name','age','salary','address','department','employees_status') VALUES(?,?,?,?,?,?,?,?)";
        int inserted =jdbcTemplate.update(sql,
                employeeModel.getEmployeeId(),
                employeeModel.getFirstName(),
                employeeModel.getLastName(),
                employeeModel.getAge(),
                employeeModel.getSalary(),
                employeeModel.getAddress(),
                employeeModel.getDepartment(),
                employeeModel.getEmployeeStatus(),
                employeeModel.getBankId());
        if (inserted == 1) {
            return employeeModel;
        }
        return null;
    }

    @Override
    public EmployeeModel update(EmployeeModel employee) {
        String sql = "UPDATE employee SET first_name=?, last_name=?, age=?, salary=?, address=?,department=?,employees_status=?,bank_id=?";
        int status = jdbcTemplate.update(sql, employee.getEmployeeId(),employee.getFirstName(),
                employee.getLastName(),employee.getAge(),employee.getSalary(),employee.getAddress(),employee.getDepartment(),
                employee.getEmployeeStatus(),employee.getBankId());
        if(status != 0){
            System.out.println("Employee data updated for ID " + employee.getEmployeeId());
        }else{
            System.out.println("No Employee found with ID " + employee.getEmployeeId());
        }
        return employee;
    }

    @Override
    public Optional<EmployeeModel> findById(Long id) {
        String sql = "SELECT * FROM employee WHERE employee_id= ?";
        EmployeeModel employeeModel =null;
        try {
            employeeModel = jdbcTemplate.queryForObject(sql,
                    new EmployeeRowMapper(), id);
        }catch(DataAccessException ex){
            System.err.println("Employee not found with id "+ id);
        }
        return Optional.ofNullable(employeeModel);
    }

    @Override
    public void deleteEmployeeModelById(Long id) {
        String sql ="DELETE FROM employee WHERE employee_id = ?";
        int status = jdbcTemplate.update(sql,id);
        if(status != 0){
            System.out.println("Employee data deleted for ID " + id);
        }else{
            System.out.println("No Employee found with ID " + id);
        }
    }

}
