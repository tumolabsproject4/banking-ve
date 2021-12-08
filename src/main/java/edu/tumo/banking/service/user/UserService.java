package edu.tumo.banking.service.user;

import edu.tumo.banking.domain.user.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> findAll();
    UserModel add(UserModel userModel);
    UserModel update(UserModel userModel);
    UserModel findById(Long id);
    void deleteUserById(Long id);
}
