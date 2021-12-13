package edu.tumo.banking.controller;

import edu.tumo.banking.domain.user.UserModel;
import edu.tumo.banking.domain.user.UserRegistrationModel;
import edu.tumo.banking.exception.AlreadyExistingValueException;
import edu.tumo.banking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationModel userRegistrationModel, Model model) {
        try {
            UserModel registered = userService.register(userRegistrationModel);
        } catch (AlreadyExistingValueException uaeEx) {
            model.addAttribute("usernameExists", true);
            return "/register";
        }
        return "redirect:/login";
    }

    @PostMapping("/addUsers")
    public String addUser(@RequestBody UserModel user, Model model) {
        UserModel userModel = userService.add(user);
        model.addAttribute("user", userModel);
        return "userCreate";
    }

    @GetMapping("/allUsers")
    public String findUsers(Model model) {
        List<UserModel> user = userService.findAll();
        model.addAttribute("user", user);
        return "users";
    }

    @GetMapping("/{id}")
    public String findUserById(@PathVariable Long id, Model model) {
        Optional<UserModel> userModel = userService.findById(id);
        model.addAttribute("user", userModel);
        return "users";
    }

    @GetMapping("/username")
    public String findUserByUsername(@PathVariable String username, Model model) {
        Optional<UserModel> user = userService.findByUserName(username);
        model.addAttribute("bank", user);
        return "users";
    }

    @PutMapping
    public String updateUser(@RequestBody UserModel updatedUser, Model model) {
        Optional<UserModel> user = userService.update(updatedUser);
        model.addAttribute("user", user);
        return "users";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id, Model model) {
        userService.deleteUserById(id);
        model.addAttribute("user", null);
        return "users";
    }

}
