package edu.tumo.banking.repository.bank;

import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.repository.mappers.BankRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class BankRepositoryImpl implements BankRepository<BankModel, Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BankRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BankModel> findAll() {
        String sql = "SELECT * FROM bank";
        return jdbcTemplate.query(sql, new BankRowMapper());
    }

    @Override
    public BankModel add(BankModel bankModel) {
        String sql = "INSERT INTO bankModel('bank_name','address') VALUES(?,?)";
        int inserted= jdbcTemplate.update(sql, bankModel.getBankID(),bankModel.getBankName(),bankModel.getAddress());
        if (inserted == 1) {
            return bankModel;
        }
        return null;
    }

    @Override
    public BankModel update(BankModel bank) {
       String sql = "UPDATE bank SET bank_name=?, adress=? WHERE bank_id=?";
        int update = jdbcTemplate.update(sql, bank.getBankName(),bank.getAddress(), bank.getBankID());
        if(update != 0){
            System.out.println("Bank data updated for id" + bank.getBankID() );
        }else{
            throw new RuntimeException(); // todo remove
        }
        return bank;
    }

    @Override
    public Optional<BankModel> findById(Long id) {
        String sql = "SELECT * FROM bank WHERE bank_id = ?";
        BankModel bankModel=null;
        try{
            bankModel = jdbcTemplate.queryForObject(sql,new BankRowMapper(), id);
        }catch (DataAccessException ex) {
            System.err.println("Bank not found with id "+ id);
        }
        return Optional.ofNullable(bankModel);
    }

    @Override
    public void deleteBankModelBy (Long id)
    {
        String sql="DELETE FROM bank WHERE bank_id = ?";
        int status = jdbcTemplate.update(sql,id);
        if(status != 0){
            System.out.println("Bank data deleted for id " + id);
        }else{
            System.out.println("No Bank found with id " + id);
        }
    }
}