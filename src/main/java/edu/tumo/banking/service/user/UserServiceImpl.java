package edu.tumo.banking.service.user;

import edu.tumo.banking.domain.user.UserModel;
import edu.tumo.banking.exception.AlreadyExistingValueException;
import edu.tumo.banking.exception.NotFoundValueException;
import edu.tumo.banking.exception.ResourceNotValidException;
import edu.tumo.banking.repository.user.UserRepository;
import edu.tumo.banking.validation.UserValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserModel add(UserModel userModel) {

        if (!UserValidation.validateUserModel(userModel)) {
            logger.info("User {} is not valid", userModel);
            throw new ResourceNotValidException("User" + userModel + "is not valid");
        }
        UserModel user = findByUserName(userModel.getUsername()).orElse(null);
        if (user != null) {
            logger.warn("User with the username {} exists ", userModel.getUsername());
            throw new AlreadyExistingValueException("User with the username" + userModel.getUsername() + "exists");
        }
        logger.info("User {} is successfully added", userModel);
        return userRepository.add(userModel);
    }

    @Override
    @Transactional
    public List<UserModel> findAll() {
        logger.info("Users are found");
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<UserModel> findById(Long id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel == null) {
            logger.warn("User with the following id {} is not found", id);
            throw new NotFoundValueException("User with the following id" + id + "is not found");
        }
        logger.info("User with the following id {} is found", id);
        return userModel;
    }

    @Override
    @Transactional
    public Optional<UserModel> findByUserName(String userName) {
        Optional<UserModel> userModel = userRepository.findByUserName(userName);//hnaravor tarberak havasar e null
        if (userModel == null) {
            logger.warn("User with the username {} doesn't exist ", userName);
            throw new NotFoundValueException("User with the username" + userName + "exists");
        }
        logger.info("User with the username{} exists", userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public Optional<UserModel> update(UserModel userModel) {
        if (!UserValidation.validateUserModel(userModel)) {
            logger.info("User{} is not valid", userModel);
            throw new ResourceNotValidException("User" + userModel + "is not valid");
        }
        Optional<UserModel> user = userRepository.findById(userModel.getUserId());
        if (user == null) {
            logger.warn("User with the following id {} is not found", userModel.getUserId());
            throw new NotFoundValueException("User with the following id" + userModel.getUserId() + "is not found");
        }
        logger.info("User{} is updated", userModel);
        return userRepository.update(userModel);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        UserModel user = userRepository.findById(id).orElse(null);
        if (user == null) {
            logger.warn("User with the following id {} is not found", id);
            throw new NotFoundValueException("User with the following id" + id + "is not found");
        }
        logger.info("User {} is deleted", id);
        userRepository.deleteUserById(id);
    }


}
