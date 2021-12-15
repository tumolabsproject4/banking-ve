package edu.tumo.banking.controller;

import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.service.bank.BankService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


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
        model.addAttribute("banks",bank);
        return "banks";
    }

    @GetMapping("/{id}")
    public String findBankById(@PathVariable Long id,Model model) {
        BankModel bank= bankService.findById(id);
        model.addAttribute("bank",bank);
        return "employees";
    }

    @PostMapping(value = "/{id}/updateBanks", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateBank(@PathVariable Long id, @RequestParam MultiValueMap<String,String> paramMap, Model model) {
        BankModel updateModel = getUpdateBankModel(paramMap);
        updateModel.setBankID(id);
        BankModel bank= bankService.update(updateModel);
        model.addAttribute("bank",bank);
        return "redirect:/banks/allBanks";
    }

    @DeleteMapping("/{id}/deleteBank")
    public String deleteBankById(@PathVariable Long id) {
        bankService.deleteBankModelById(id);
//        model.addAttribute("bank",null);
        return "banks";
    }

    @DeleteMapping("/{id}/deleteImage")
    public String deleteImageById(@PathVariable Long id){
        bankService.deleteImageByBankId(id);
//        model.addAttribute("bank",null);
        return "banks";
    }

    private BankModel getUpdateBankModel(final MultiValueMap<String, String> paramMap) {
      final var address = paramMap.getFirst("address");
      final var bankName = paramMap.getFirst("bankName");
      return new BankModel(bankName, address);
    }


}
