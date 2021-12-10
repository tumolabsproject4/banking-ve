package edu.tumo.banking.validation;

import edu.tumo.banking.domain.user.UserModel;

public class UserValidation {

    public static boolean validateUserModel(UserModel userModel) {
        if (userModel != null && userModel.getUsername()!=null && !userModel.getUsername().isEmpty()
                && userModel.getPassword()!=null && !userModel.getPassword().isEmpty()) {
                return true;
        }
        return false;
    }
}

