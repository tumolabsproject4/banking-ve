package edu.tumo.banking.controller;


import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.validation.BankValidation;
import edu.tumo.banking.service.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/banks")

public class BankController {

    private final BankService bankService;
    private final BankValidation bankValidation;

    @Autowired
    public BankController(BankService bankService, BankValidation bankValidation) {
        this.bankService = bankService;
        this.bankValidation=bankValidation;
    }

    @PostMapping
    public ResponseEntity<BankModel> addBank(@RequestBody BankModel newBank){
        if(!bankValidation.validateBankModel(newBank))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bankService.add(newBank), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BankModel>> findBanks() {
        List<BankModel> bank = bankService.findAll();
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankModel> findBankById(@PathVariable Long id) {
        return new ResponseEntity<>(bankService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BankModel> updateBank(@RequestBody BankModel updatedBank) {

        if(!(bankValidation.validateForNull(updatedBank)))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bankService.update(updatedBank), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankById(@PathVariable Long id) {
        bankService.deleteBankModelBy(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
