package edu.tumo.banking.validation;

import edu.tumo.banking.domain.bank.model.BankModel;

public class BankValidation {
    public static boolean validateBankModel(BankModel bankModel) {
        if (bankModel != null && bankModel.getBankName() != null && !bankModel.getBankName().isEmpty()
                    && bankModel.getAddress() != null && !bankModel.getAddress().isEmpty()) {
                return true;
        }
        return false;
    }
}
