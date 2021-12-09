package edu.tumo.banking.repository.employee;

import edu.tumo.banking.domain.employee.model.EmployeeModel;
import edu.tumo.banking.repository.mappers.EmployeeRowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class EmployeeRepositoryImpl implements EmployeeRepository<EmployeeModel, Long> {

    private final JdbcTemplate jdbcTemplate;

    private final Logger logger = LogManager.getLogger(EmployeeRepositoryImpl.class);

    @Autowired
    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public EmployeeModel add(EmployeeModel employeeModel) {
        String sql= "INSERT INTO employee('first_name','last_name','age','salary','address','department','employees_status','bank_id') VALUES(?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int inserted = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"employee_id"});
            ps.setString(1,employeeModel.getFirstName());
            ps.setString(2,employeeModel.getLastName());
            ps.setInt(3,employeeModel.getAge());
            ps.setFloat(4,employeeModel.getSalary());
            ps.setString(5,employeeModel.getAddress());
            ps.setBoolean(6,employeeModel.getEmployeeStatus());
            ps.setLong(7,employeeModel.getBankId());
            return ps;
        },keyHolder);
        if (inserted == 1) {
            Number keyNumber=keyHolder.getKey();
            employeeModel.setEmployeeId(keyNumber.longValue());
            return employeeModel;
        }
        logger.warn("Employee {} id is not added ",employeeModel);
        return null;
    }

    @Override
    public EmployeeModel addImage(Long id, MultipartFile image) {
        Optional<EmployeeModel> employee=findById(id);
        String sql = "UPDATE employee SET image = ? where employee_id = ?";
        byte[] employeeImageBytes= new byte[0];
        try {
            employeeImageBytes = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int update = jdbcTemplate.update(sql, employeeImageBytes,id);
        if(update==1)
        {
            return employee.get().setImage(employeeImageBytes);
        }
        logger.warn("This image not added to employee id {}",id);
        return null;
    }

    @Override
    public List<EmployeeModel> findAll() {
        String sql = "SELECT * FROM employee";
        List<EmployeeModel> employees=jdbcTemplate.query(sql, new EmployeeRowMapper());
        logger.info("Employees are found");
        return employees;
    }

    @Override
    public Optional<EmployeeModel> findById(Long id) {
        String sql = "SELECT * FROM employee WHERE employee_id= ?";
        EmployeeModel employeeModel =null;
        try {
            employeeModel = jdbcTemplate.queryForObject(sql,
                    new EmployeeRowMapper(), id);
        }catch(DataAccessException ex){
            logger.error("Employee not found with id {}",id);
        }
        logger.info("Employee {} is found",id);
        return Optional.ofNullable(employeeModel);
    }

    @Override
    public Optional<EmployeeModel> update(EmployeeModel employee) {
        String sql = "UPDATE employee SET first_name=?, last_name=?, age=?, salary=?, address=?,department=?,employees_status=?,bank_id=? WHERE employee_id=?";
        int status = jdbcTemplate.update(sql, employee.getEmployeeId(),employee.getFirstName(),
                employee.getLastName(),employee.getAge(),employee.getSalary(),employee.getAddress(),employee.getDepartment(),
                employee.getEmployeeStatus(),employee.getBankId());
        if(status ==1 ){
            return findById(employee.getEmployeeId());
        }
        logger.warn("The employee {} is not updated ",employee);
        return Optional.empty();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        String sql ="DELETE FROM employee WHERE employee_id = ?";
        int status = jdbcTemplate.update(sql,id);
        if(status == 1)
        {
            logger.info("Employee {} was deleted ",id);
        }
    }

    @Override
    public void deleteImageByEmployeeId(Long id) {
        String sql = "UPDATE employee SET image=null WHERE employee_id=?";
        int status = jdbcTemplate.update(sql,id);
        if(status == 1)
        {
            logger.info("The image of Employee with id {} was deleted",id);
        }
    }
}
