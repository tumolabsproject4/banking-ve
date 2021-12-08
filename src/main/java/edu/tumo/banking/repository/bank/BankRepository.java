package edu.tumo.banking.repository.bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository<BankModel, Long>{
    BankModel add(BankModel bankModel);

    List<BankModel> findAll();

    Optional<BankModel> findById(Long id);

    BankModel update(BankModel bank);

    void deleteBankModelBy(Long id );

}