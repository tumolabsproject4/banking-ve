package edu.tumo.banking.controller;

import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.service.bank.BankService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/banks")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping(value = "/addBanks", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addBank(@RequestParam MultiValueMap<String,String> paramMap,Model model) {
        BankModel bank = getCreateBankModel(paramMap);
        bankService.add(bank);
        return "redirect:/banks/allBanks";
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

//    @ResponseBody
//    @GetMapping(value = "/{id}/image", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    public ResponseEntity<InputStream> getImage(@PathVariable Long id) {
//        byte[] image = bankService.getImage(id);
//        return ResponseEntity.ok()
//                .contentLength(image.length)
//                .body(new ByteArrayInputStream(image));
//    }

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

    @PostMapping("/{id}/deleteBank")
    public String deleteBankById(@PathVariable Long id) {
        bankService.deleteBankModelById(id);
        return "redirect:/banks/allBanks";
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

    private BankModel getCreateBankModel(final MultiValueMap<String, String> paramMap) {
      final var address = paramMap.getFirst("address");
      final var bankName = paramMap.getFirst("bankName");
      return new BankModel(bankName, address);
    }

}
