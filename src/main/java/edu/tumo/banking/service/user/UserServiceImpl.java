package edu.tumo.banking.service.user;

import edu.tumo.banking.domain.user.UserModel;
import edu.tumo.banking.domain.user.UserRegistrationModel;
import edu.tumo.banking.exception.AlreadyExistingValueException;
import edu.tumo.banking.exception.NotFoundValueException;
import edu.tumo.banking.exception.ResourceNotValidException;
import edu.tumo.banking.repository.user.UserRepository;
import edu.tumo.banking.validation.UserValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserModel register(UserRegistrationModel userRegistrationModel) {

        if (!UserValidation.validateUserRegistrationModel(userRegistrationModel)) {
            logger.info("User {} is not valid", userRegistrationModel);
            throw new ResourceNotValidException("User" + userRegistrationModel + "is not valid");
        }
        Optional<UserModel> userModelOptional = getByUserName(userRegistrationModel.getUsername());
        if (userModelOptional.isPresent()) {
            logger.info("User with the username {} exists ", userRegistrationModel.getUsername());
            throw new AlreadyExistingValueException("User with the username" + userRegistrationModel.getUsername() + "exists");
        }
        logger.info("User {} is successfully added", userRegistrationModel);
        UserModel userModel = new UserModel();
        userModel.setUsername(userRegistrationModel.getUsername());
        userModel.setPassword(passwordEncoder.encode(userRegistrationModel.getPassword()));
        return userRepository.add(userModel);
    }

    @Override
    @Transactional
    public UserModel add(UserModel userModel) {

        if (!UserValidation.validateUserModel(userModel)) {

            logger.info("User with username {} is not valid", userModel.getUsername());
            throw new ResourceNotValidException("User with username " + userModel.getUsername() + "is not valid");
        }
        UserModel user = findByUserName(userModel.getUsername()).orElse(null);
        if (user != null) {
            logger.info("User with the username {} exists ", userModel.getUsername());
            throw new AlreadyExistingValueException("User with the username" + userModel.getUsername() + "exists");
        }

        logger.info("User with username {} is successfully added", userModel.getUsername());
        return userRepository.add(userModel);

    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isEmpty()) {
            logger.info("User with the following id {} is not found", id);
            throw new NotFoundValueException("User with the following id" + id + "is not found");
        }
        logger.info("User with the following id {} is found", id);
        return userModel;
    }

    @Override
    public Optional<UserModel> findByUserName(String userName) {
        Optional<UserModel> userModel = userRepository.findByUserName(userName);
        if (userModel.isEmpty()) {
            logger.info("User with the username {} doesn't exist ", userName);
            throw new NotFoundValueException("User with the username" + userName + "exists");
        }
        logger.info("User with the username{} exists", userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public Optional<UserModel> update(UserModel userModel) {
        if (!UserValidation.validateUserModel(userModel)) {
            logger.info("User with id {} is not valid", userModel.getUserId());
            throw new ResourceNotValidException("User with id " + userModel.getUserId() + "is not valid");
        }
        Optional<UserModel> user = userRepository.findById(userModel.getUserId());
        if (user.isEmpty()) {
            logger.info("User with the following id {} is not found", userModel.getUserId());
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
            logger.info("User with the following id {} is not found", id);
            throw new NotFoundValueException("User with the following id" + id + "is not found");
        }
        userRepository.deleteUserById(id);
        logger.info("User {} is deleted", id);
    }

    private Optional<UserModel> getByUserName(String userName) {
        Optional<UserModel> userModelOptional = userRepository.findByUserName(userName);
        logger.info("User with the username{} exists", userName);
        return userModelOptional;
    }


}
