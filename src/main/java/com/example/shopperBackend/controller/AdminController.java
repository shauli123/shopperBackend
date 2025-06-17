package com.example.shopperBackend.controller;

import com.example.shopperBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private UserService userService;

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping(value = "/all-users")
//    public ResponseEntity<List<CustomUser>> getAllUsers() {
//        try{
//            List<CustomUser> users = userService.getAllUsers();
//            return ResponseEntity.ok(users);
//        } catch (Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/delete-user/{username}")
//    public ResponseEntity<String> deleteAnotherUser(@PathVariable String username) {
//        try {
//            String result = userService.deleteUser(username);
//            if (result.contains("successfully")) {
//                return new ResponseEntity(result, HttpStatus.OK);
//            }
//            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

