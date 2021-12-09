package edu.tumo.banking.service.user;

import edu.tumo.banking.domain.user.UserModel;
import edu.tumo.banking.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService{

    private final UserRepositoryImpl userRepositoryImp;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepositoryImp) {
        this.userRepositoryImp = userRepositoryImp;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepositoryImp.findAll();
    }

    @Override
    public UserModel add(UserModel userModel) {
        return userRepositoryImp.add(userModel);
    }

    @Override
    public UserModel update(UserModel userModel) {
        return userRepositoryImp.update(userModel);
    }

    @Override
    public UserModel findById(Long id) {
        return userRepositoryImp.findById(id).get();
    }

    public void deleteUserById(Long id)
    {
        userRepositoryImp.deleteUserById(id);
    }


}
