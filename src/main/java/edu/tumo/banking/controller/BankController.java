package edu.tumo.banking.controller;


import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.service.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("/banks")

public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/addbanks")
    public ResponseEntity<BankModel> addBank(@RequestBody BankModel newBank) {
        return new ResponseEntity<>(bankService.add(newBank), HttpStatus.CREATED);
    }


    @PostMapping("/{id}/addimage")
    public String addImage(@PathVariable Long id, @RequestParam("image") MultipartFile image, Model model) {
        BankModel bankModel = bankService.addImage(id, image);
        model.addAttribute("bank",bankModel);

        return "bankChanges";
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

//    @GetMapping("/{id}/image")
//    public ResponseEntity<byte[]> getImage(@PathVariable Long id){
//        return new ResponseEntity<>(bankService.getImage(id), HttpStatus.OK);
//    }

    @PutMapping
    public ResponseEntity<BankModel> updateBank(@RequestBody BankModel updatedBank) {
        return new ResponseEntity<>(bankService.update(updatedBank), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankById(@PathVariable Long id) {
        bankService.deleteBankModelById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<?> deleteImageById(@PathVariable Long id){
        bankService.deleteImageByBankId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
