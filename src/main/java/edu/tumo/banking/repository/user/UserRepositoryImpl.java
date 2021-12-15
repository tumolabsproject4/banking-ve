package edu.tumo.banking.repository.user;

import edu.tumo.banking.domain.user.UserModel;
import edu.tumo.banking.repository.mappers.UserRowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    @Transactional
    public UserModel add(UserModel userModel) {
        String sql = "INSERT INTO user(username, user_password) VALUES(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int inserted = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"user_id"});
            ps.setString(1, userModel.getUsername());
            ps.setString(2, userModel.getPassword());
            return ps;
        }, keyHolder);
        if (inserted == 1) {
            Number keyNumber = keyHolder.getKey();
            userModel.setUserId(keyNumber.longValue());
            return userModel;
        }
        logger.warn("The user {} is not added", userModel);
        return null;
    }

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM user";
        List<UserModel> users = jdbcTemplate.query(sql, new UserRowMapper());
        logger.info("Users are found ");
        return users;
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        UserModel userModel = null;
        try {
            userModel = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        } catch (DataAccessException ex) {
            logger.error("User not found with id {}", id);
        }
        logger.info("User {} id is found", id);
        return Optional.ofNullable(userModel);
    }

    @Override
    public Optional<UserModel> findByUserName(String userName) {
        String sql = "SELECT * FROM user WHERE username = ?";
        UserModel userModel = null;
        try {
            userModel = jdbcTemplate.queryForObject(sql, new UserRowMapper(), userName);
        } catch (DataAccessException ex) {
            logger.error("User not found where username is ", userName);
        }
        logger.info("User {} id is found", userName);
        return Optional.ofNullable(userModel);
    }

    @Override
    @Transactional
    public Optional<UserModel> update(UserModel user) {
        String sql = "UPDATE user SET username=?, user_password=? where user_id=?";
        int update = jdbcTemplate.update(sql, user.getUserId(), user.getUsername(), user.getPassword());
        if (update == 1) {
            return findById(user.getUserId());
        }
        logger.warn("User{} is not updated ", user);
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        String sql = "DELETE FROM user WHERE user_id= ?";
        int status = jdbcTemplate.update(sql, id);
        if (status == 1) {
            logger.info("User id {} was deleted", id);
        }
    }
}
