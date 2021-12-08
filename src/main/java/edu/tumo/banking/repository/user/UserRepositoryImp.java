package edu.tumo.banking.repository.user;

import edu.tumo.banking.domain.user.UserModel;
import edu.tumo.banking.repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserRepositoryImp implements UserRepository<UserModel, Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM user";
        List<UserModel> users=jdbcTemplate.query(sql, new UserRowMapper());
        return users;
    }

    @Override
    public UserModel add(UserModel userModel) {
        String sql = "INSERT INTO user('username','user_password') VALUES(?,?)";
        int inserted= jdbcTemplate.update(sql, userModel.getUserId(),userModel.getUsername(),userModel.getPassword());
        if (inserted == 1) {
            return userModel;
        }
        return null;
    }

    @Override
    public UserModel update(UserModel user) {
        String sql = "UPDATE user SET username=?, user_password=? ";
        int update = jdbcTemplate.update(sql,user.getUsername(),user.getPassword());
        if(update != 0){
            System.out.println("Employee data updated for id" + user.getUserId() );
        }else{
            System.out.println("No Employee found with id " + user.getUserId());
        }
        return user;
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        UserModel userModel=null;
        try{
            userModel= jdbcTemplate.queryForObject(sql,new UserRowMapper(), id);
        }catch (DataAccessException ex) {
            System.err.println("Bank not found with id "+ id);
        }
        return Optional.ofNullable(userModel);
    }

    @Override
    public void deleteUserById(Long id) {
        String sql ="DELETE FROM user WHERE user_id= ?";
        int status = jdbcTemplate.update(sql,id);
        if(status != 0){
            System.out.println("User deleted for id " + id);
        } else {
            System.out.println("No User found with id " + id);
        }
    }
}
