package edu.tumo.banking.validation;

import edu.tumo.banking.domain.bank.model.BankModel;
import org.springframework.stereotype.Component;


@Component
public class BankValidation {
    public static boolean validateBankModel(BankModel bankModel) {
        if(bankModel==null && bankModel.getBankName() == null)
        {

            return false;
        }
        if () {

            return false;
        }
        if (bankModel.getAddress() == null) {

            return false;
        }
        if (bankModel.getBankID()==null){

            return false;
        }
        return true;
    }
    public static boolean validateBankModelforAdd(BankModel bankModel) {
        if(bankModel==null)
        {

            return false;
        }
        if (bankModel.getBankName() == null) {

            return false;
        }
        if (bankModel.getAddress() == null) {

            return false;
        }
        return true;
    }

    public boolean validateForNull(BankModel updatedBank) {
        return false;
    }
}
