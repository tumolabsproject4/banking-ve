package edu.tumo.banking.repository.bank;

import edu.tumo.banking.domain.bank.model.BankModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BankRepository<BankModel, Long>{
    BankModel add(BankModel bankModel);

    BankModel addImage(Long id, MultipartFile image);

    List<BankModel> findAll();

    Optional<BankModel> findById(Long id);


    BankModel update(BankModel bank);

    void deleteBankModelBy(Long id );

    void deleteImageByBankId(Long id);

}