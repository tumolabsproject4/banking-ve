package edu.tumo.banking.service.bank;

import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.exception.NotFoundValueException;
import edu.tumo.banking.exception.ResourceNotFoundException;
import edu.tumo.banking.exception.ResourceNotValidException;
import edu.tumo.banking.repository.bank.BankRepository;
import edu.tumo.banking.validation.BankValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    private final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);


    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }


    @Override
    @Transactional
    public BankModel add(BankModel bankModel) {
        if (!BankValidation.validateBankModel(bankModel)) {

            logger.info("The bank name - {}, id - {} is not valid", bankModel.getBankName(), bankModel.getBankID());
            throw new ResourceNotValidException("The bank is not valid");
        }
        logger.info("The bank with id {} is successfully added", bankModel.getBankID());
        return bankRepository.add(bankModel);
    }

//    @Override
//    public byte[] getImage(Long bankId) {
//        return bankRepository.getImage(bankId);
//    }

    @Override
    @Transactional
    public BankModel addImage(Long id, MultipartFile image) {
        Optional<BankModel> bankModel = bankRepository.findById(id);
        if (bankModel.isEmpty()) {
            logger.info("The bank with id {} is not found ", id);
            throw new NotFoundValueException("The bank with id " + id + "is not found");
        }
        logger.info("The image is successfully added for the user with id {} ", id);

        return bankRepository.addImage(id, image);
    }

    @Override
    public List<BankModel> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public BankModel findById(Long id) {
        Optional<BankModel> bankModel = bankRepository.findById(id);
        if (bankModel.isEmpty()) {
            logger.info("Bank with the following id {} doesn't exist", id);
            throw new ResourceNotValidException("Bank with the following id" + id + "doesn't exist");
        }
        logger.info("Bank with the following id {} is found", id);
        return bankModel.orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    @Transactional
    public BankModel update(BankModel bank) {
        if (!BankValidation.validateBankModel(bank)) {
            logger.info("The bank with id {} is not valid", bank.getBankID());
            throw new ResourceNotValidException("The bank with id " + bank.getBankID() + "is not valid");
        }
        Optional<BankModel> bankModel = bankRepository.findById(bank.getBankID());
        if (bankModel.isEmpty()) {
            logger.info("The bank with the following id {} is not found", bank);
            throw new NotFoundValueException("The bank with the following id " + bank.getBankID() + "is not found");

        }
        logger.info("The bank with id {} is updated",bank.getBankID());
        return bankRepository.update(bank).get();

    }


    @Override
    @Transactional
    public void deleteBankModelById(Long id) {
        BankModel bank = bankRepository.findById(id).orElse(null);
        if (bank == null) {
            logger.info("Bank {} is not found", id);
            throw new NotFoundValueException("Bank" + id + "is not found");
        }

        bankRepository.deleteBankModelById(id);
        logger.info("Bank {} is deleted", id);
    }

    @Override
    @Transactional
    public void deleteImageByBankId(Long id) {
        BankModel bank =  bankRepository.findById(id).orElse(null);
        if (bank == null) {
            logger.info("Bank {} is not found", id);
            throw new NotFoundValueException("Bank" + id + "is not found");
        }
        bankRepository.deleteImageByBankId(id);
        logger.info("Image of bank {} is deleted", id);

    }
}
