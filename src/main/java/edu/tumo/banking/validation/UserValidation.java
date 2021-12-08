package edu.tumo.banking.validation;

import edu.tumo.banking.domain.user.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.io.*;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    private final Logger logger = LogManager.getLogger(UserValidation.class);

    public boolean validateForUser(UserModel userModel) throws IOException,SQLException{
        if(userModel.getUserId() == null){
            logger.warn("Id of bank cannot be null");
            return false;
        }
        if(userModel.getUsername() == null){
            logger.warn("Username of cannot be null");
            return false;
        }
        if(userModel.getPassword() == null){
            logger.warn("Password of user cannot be null");
            return false;
        }
        return true;
    }
}
