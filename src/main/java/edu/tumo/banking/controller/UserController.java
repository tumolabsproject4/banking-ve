package edu.tumo.banking.controller;

import edu.tumo.banking.domain.user.UserModel;
import edu.tumo.banking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping

    public ResponseEntity<UserModel> adduser( @RequestBody UserModel user) {
        return new ResponseEntity<>(userService.add(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> findUsers() {
        List<UserModel> user = userService.findAll();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable Long id){
        UserModel model=userService.findById(id);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel updatedUser) {
        return new ResponseEntity<>(userService.update(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
