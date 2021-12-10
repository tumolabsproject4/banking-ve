package edu.tumo.banking.service.bank;

import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.exception.NotFoundValueException;
import edu.tumo.banking.exception.ResourceNotFoundException;
import edu.tumo.banking.exception.ResourceNotValidException;
import edu.tumo.banking.repository.bank.BankRepository;
import edu.tumo.banking.validation.BankValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private final Logger logger = LogManager.getLogger(BankServiceImpl.class);

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    @Transactional
    public BankModel add(BankModel bankModel) {
        if (!BankValidation.validateBankModel(bankModel)) {
            logger.info("The bank {} is not valid", bankModel);
            throw new ResourceNotValidException("The bank is not valid");
        }

        logger.info("The bank is successfully added", bankModel);
        return bankRepository.add(bankModel);
    }

    @Override
    @Transactional
    public BankModel addImage(Long id, MultipartFile image) {
        Optional<BankModel> bankModel = bankRepository.findById(id);
        if (bankModel.isEmpty()) {
            logger.warn("The bank with id {} is not found ", id);
            throw new NotFoundValueException("The bank with id " + id + "is not found");
        }
        logger.info("The image{} is successfully added", image);

        return bankRepository.addImage(id, image);
    }

    @Override
    public List<BankModel> findAll() {
        logger.info("Banks are found");
        return bankRepository.findAll();
    }

    @Override
    public BankModel findById(Long id) {
        Optional<BankModel> bankModel = bankRepository.findById(id);
        if (bankModel.isEmpty()) {
            logger.warn("Bank with the following id {} doesn't exist", id);
            throw new ResourceNotValidException("Bank with the following id" + id + "doesn't exist");
        }
        logger.info("Bank with the following id {} is found", id);
        return bankModel.orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    @Transactional
    public BankModel update(BankModel bank) {
        if (!BankValidation.validateBankModel(bank)) {
            logger.warn("The bank {} is not valid", bank);
            throw new ResourceNotValidException("The bank with id " + bank.getBankID() + "is not valid");
        }
        Optional<BankModel> bankModel = bankRepository.findById(bank.getBankID());
        if (bankModel.isEmpty()) {
            logger.warn("The bank with the following id {} is not found", bank);
            throw new NotFoundValueException("The bank with the following id " + bank.getBankID() + "is not found");

        }
        logger.info("The bank{} is updated", bank);
        return bankRepository.update(bank).get();

    }


    @Override
    @Transactional
    public void deleteBankModelById(Long id) {
        BankModel bank = bankRepository.findById(id).orElse(null);
        if (bank == null) {
            logger.warn("Bank{} is not found", id);
            throw new NotFoundValueException("Bank" + id + "is not found");
        }
        logger.info("Bank{} is deleted", id);
        bankRepository.deleteBankModelById(id);
    }

    @Override
    @Transactional
    public void deleteImageByBankId(Long id) {
        BankModel bank = (BankModel) bankRepository.findById(id).orElse(null);
        if (bank == null) {
            logger.warn("Bank{} is not found", id);
            throw new NotFoundValueException("Bank" + id + "is not found");
        }
        logger.info("Image of bank{} is deleted", id);
        bankRepository.deleteImageByBankId(id);
    }
}
