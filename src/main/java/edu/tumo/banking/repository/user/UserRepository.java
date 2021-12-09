package edu.tumo.banking.repository.user;


import java.util.List;
import java.util.Optional;

public interface UserRepository<UserModel,Long>{

    UserModel add(UserModel userModel);

    List<UserModel> findAll();

    Optional<UserModel> findById(Long id);

    Optional<UserModel> findByUserName(String userName);

    Optional<UserModel> update(UserModel userModel);

    void deleteUserById(Long id);
}
