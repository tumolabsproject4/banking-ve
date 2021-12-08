package edu.tumo.banking.repository.mappers;

import edu.tumo.banking.domain.employee.model.EmployeeModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<EmployeeModel> {
    @Override
    public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setEmployeeId(rs.getLong("employee_id"));
        employeeModel.setFirstName(rs.getString("first_name"));
        employeeModel.setLastName(rs.getString("last_name"));
        employeeModel.setAge(rs.getInt("age"));
        employeeModel.setSalary(rs.getFloat("salary"));
        employeeModel.setAddress(rs.getString("address"));
        employeeModel.setDepartment(rs.getString("department"));
        employeeModel.setEmployeeStatus(rs.getBoolean("employees_status"));
        employeeModel.setBankId(rs.getLong("bank_id"));
        return employeeModel;
    }
}