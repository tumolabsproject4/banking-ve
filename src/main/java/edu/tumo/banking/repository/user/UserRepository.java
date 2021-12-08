package edu.tumo.banking.repository.user;


import java.util.List;
import java.util.Optional;

public interface UserRepository<UserModel,Long>{
    List<UserModel> findAll();
    UserModel add(UserModel userModel);
    UserModel update(UserModel userModel);
    Optional<UserModel> findById(Long id);
    void deleteUserById(Long id);
}
