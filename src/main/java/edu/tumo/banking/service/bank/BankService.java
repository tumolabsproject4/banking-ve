package edu.tumo.banking.service.bank;

import edu.tumo.banking.domain.bank.model.BankModel;

import java.util.List;

public interface BankService {
    List<BankModel> findAll();

    BankModel add(BankModel bankModel);

    byte[] getImage(Long id);

    BankModel update(BankModel bank);

    BankModel findById(Long id);

    void deleteBankModelBy(Long id );

}
