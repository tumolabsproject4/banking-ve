package edu.tumo.banking.repository.bank;

import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.repository.mappers.BankRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class BankRepositoryImpl implements BankRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(BankRepositoryImpl.class);

    @Autowired
    public BankRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public BankModel add(BankModel bankModel) {
        String sql = "INSERT INTO bank(bank_name,address) VALUES(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int inserted = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"bank_id"});
            ps.setString(1, bankModel.getBankName());
            ps.setString(2, bankModel.getAddress());
            return ps;
        }, keyHolder);
        if (inserted == 1) {
            Number keyNumber = keyHolder.getKey();
            bankModel.setBankID(keyNumber.longValue());
            return bankModel;
        }
        logger.warn("The bank {} is not added ", bankModel);
        return null;
    }

    @Override
    @Transactional
    public BankModel addImage(Long id, MultipartFile image) {
        Optional<BankModel> bank = findById(id);
        String sql = "UPDATE bank SET image = ? where bank_id = ?";
        byte[] bytes = new byte[0];
        try {
            bytes = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int update = jdbcTemplate.update(sql, bytes, id);
        if (update == 1) {
            return bank.get().setImage(bytes);
        }
        logger.warn("This image not added to bank id {}", id);
        return null;
    }

    @Override
    public List<BankModel> findAll() {
        String sql = "SELECT * FROM bank";
        logger.info("Banks are found ");
        return jdbcTemplate.query(sql, new BankRowMapper());
    }

    @Override
    public Optional<BankModel> findById(Long id) {
        String sql = "SELECT * FROM bank WHERE bank_id = ?";
        BankModel bankModel = null;
        try {
            bankModel = jdbcTemplate.queryForObject(sql, new BankRowMapper(), id);
        } catch (DataAccessException ex) {
            logger.error("Bank not found with id {}", id);
        }
        logger.info("Bank id{} is found ", id);
        return Optional.ofNullable(bankModel);
    }

    @Override
    @Transactional
    public Optional<BankModel> update(BankModel bank) {
        String sql = "UPDATE bank SET bank_name=?, address=? WHERE bank_id=?";
        int update = jdbcTemplate.update(sql, bank.getBankName(), bank.getAddress(), bank.getBankID());
        if (update == 1) {
            return findById(bank.getBankID());
        }
        logger.warn("Bank {} is not updated", bank);
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteBankModelById(Long id) {
        String sql = "DELETE FROM bank WHERE bank_id = ?";
        int status = jdbcTemplate.update(sql, id);
        if (status == 1) {
            logger.info("Bank {} was deleted ", id);
        }
        else{
            logger.info ("Bank with id {} is not deleted",id);
            throw new RuntimeException("Bank with id " + id + " is not deleted");
        }
    }

    @Override
    @Transactional
    public void deleteImageByBankId(Long id) {
        String sql = "UPDATE bank SET image=null WHERE bank_id=?";
        int status = jdbcTemplate.update(sql, id);
        if (status == 1) {
            logger.info("The image of Bank with id {} was deleted", id);
        }
        else{
            logger.info ("the image of the bank with id {} is not deleted",id);
            throw new RuntimeException("The image of the bank with id " + id + " is not deleted");
        }
    }
}