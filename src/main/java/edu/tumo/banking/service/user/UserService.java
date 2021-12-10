package edu.tumo.banking.service.user;

import edu.tumo.banking.domain.user.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserModel add(UserModel userModel);

    List<UserModel> findAll();

    Optional<UserModel> findById(Long id);

    Optional<UserModel> findByUserName(String userName);

    Optional<UserModel> update(UserModel userModel);

    void deleteUserById(Long id);

}
