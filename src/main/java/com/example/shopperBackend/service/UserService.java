package com.example.shopperBackend.service;

import com.example.shopperBackend.model.CustomUser;
import com.example.shopperBackend.model.Role;
import com.example.shopperBackend.repository.ItemRepository;
import com.example.shopperBackend.repository.OrderRepository;
import com.example.shopperBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(CustomUser user) {
        if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null
                || user.getUsername() == null || user.getPassword() == null) {
            return "User not created, first name, last name, email, username and password are required";
        }
        CustomUser userWithTheSameEmail = getUserByEmail(user.getEmail());
        CustomUser userWithTheSameUsername = getUserByUsername(user.getUsername());
        if(
                userWithTheSameEmail != null || userWithTheSameUsername != null){
            return "User not created, This email or username already exists in the system.";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Encoded password: " + user.getPassword()); // Log the encoded password

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        return userRepository.register(user);
    }

    public CustomUser getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public CustomUser getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<CustomUser> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public CustomUser updateUser(CustomUser updatedUser) {
        return userRepository.updateUser(updatedUser);
    }

    public String deleteUser(String username) {
        CustomUser registeredUser = userRepository.findUserByUsername(username);
        if (registeredUser == null) {
            return "The user with this username does not exist, so it cannot be deleted";
        }
        System.out.println("before deleted user" + registeredUser);
        String result = userRepository.deleteUser(registeredUser.getUsername());
        System.out.println("deleted user");
        orderRepository.deleteAllOrdersAndOrderItems(username);
        itemRepository.deleteAllFavItems(username);
        System.out.println("deleted user items & favs");

        return result;
    }

}

