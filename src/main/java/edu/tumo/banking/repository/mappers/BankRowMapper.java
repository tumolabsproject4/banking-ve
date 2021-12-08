package edu.tumo.banking.repository.mappers;

import edu.tumo.banking.domain.bank.model.BankModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankRowMapper implements RowMapper<BankModel> {
    @Override
    public BankModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            BankModel bankModel = new BankModel();
            bankModel.setBankID(rs.getLong("bank_id"));
            bankModel.setBankName(rs.getString("bank_name"));
            bankModel.setAddress((rs.getString("address")));
            return bankModel;
    }
}

