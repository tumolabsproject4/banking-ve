package edu.tumo.banking.validation;

import edu.tumo.banking.domain.user.UserModel;
import edu.tumo.banking.domain.user.UserRegistrationModel;

public class UserValidation {

    public static boolean validateUserModel(UserModel userModel) {
        if (userModel != null && userModel.getUsername() != null && !userModel.getUsername().isEmpty()
                && userModel.getPassword() != null && !userModel.getPassword().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean validateUserRegistrationModel(UserRegistrationModel userRegistrationModel) {
        if (userRegistrationModel != null && userRegistrationModel.getUsername() != null && !userRegistrationModel.getUsername().isEmpty()
                && userRegistrationModel.getPassword() != null && !userRegistrationModel.getPassword().isEmpty()) {
            return true;
        }
        return false;
    }
}

