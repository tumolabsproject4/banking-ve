package edu.tumo.banking.service.bank;

import edu.tumo.banking.domain.bank.model.BankModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BankService {

    BankModel add(BankModel bankModel);

   // byte[] getImage(Long bankId);

    BankModel addImage(Long id, MultipartFile image);

    List<BankModel> findAll();

    BankModel findById(Long id);

    BankModel update(BankModel bank);

    void deleteBankModelById(Long id);

    void deleteImageByBankId(Long id);

}
