package edu.tumo.banking.controller;

import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.service.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/addBanks")
    public String addBank(@RequestBody BankModel newBank,Model model) {
        BankModel bank=bankService.add(newBank);
        model.addAttribute("bank",bank);
        return "bankChanges";
    }


    @PostMapping("/{id}/addBankImage")
    public String addImage(@PathVariable Long id, @RequestParam("image") MultipartFile image, Model model) {
        BankModel bank = bankService.addImage(id, image);
        model.addAttribute("bank",bank);
        return "bankChanges";
    }

    @GetMapping("/allBanks")
    public String findBanks(Model model) {
        List<BankModel> bank = bankService.findAll();
        model.addAttribute("bank",bank);
        return "banks";
    }

    @GetMapping("/{id}")
    public String findBankById(@PathVariable Long id,Model model) {
        BankModel bank= bankService.findById(id);
        model.addAttribute("bank",bank);
        return "employees";
    }

    @PutMapping("/updateBanks")
    public String updateBank(@RequestBody BankModel updatedBank,Model model) {
        BankModel bank= bankService.update(updatedBank);
        model.addAttribute("bank",bank);
        return "bankChanges";
    }

    @DeleteMapping("/{id}/deleteBank")
    public String deleteBankById(@PathVariable Long id,Model model) {
        bankService.deleteBankModelById(id);
//        model.addAttribute("bank",null);
        return "bankChanges";
    }

    @DeleteMapping("/{id}/deleteImage")
    public String deleteImageById(@PathVariable Long id,Model model){
        bankService.deleteImageByBankId(id);
//        model.addAttribute("bank",null);
        return "bankChanges";
    }


}
