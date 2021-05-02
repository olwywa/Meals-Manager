package com.example.mealsmanagerapp.services;

import com.example.mealsmanagerapp.models.User;
import com.example.mealsmanagerapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        Optional<User> userDb = userRepository.findById(id);

        if (userDb.isPresent()) {
            return userDb.get();
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, String name, String email, String password) {

        User userExists = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Student with id" + id + "does not exists"));

        if(name != null && name.length() > 0 && !Objects.equals(userExists.getName(), name)) {
            userExists.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(userExists.getEmail(), email)) {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("This e-mail is taken.");
            }
            userExists.setEmail(email);
        }

        if(password != null && password.length() > 0 && !Objects.equals(userExists.getPassword(), password)) {
            userExists.setPassword(password);
        }

        userRepository.save(userExists);

        return userExists;


//        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
//        User userDb = null;
//
//        if(userOptional.isPresent()) {
//            userDb = userOptional.get();
//
//            userDb.setId(user.getId());
//            userDb.setName(user.getName());
//            userDb.setEmail(user.getEmail());
//            userDb.setPassword(user.getPassword());
//            userDb.setMeals(user.getMeals());
//            userRepository.save(userDb);
//            return userDb;
//        } else {
//            throw new RuntimeException("User not found with id " + user.getId());
//        }
    }

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException("User not found with id " + id);
        } else {
            userRepository.deleteById(id);
        }
    }
}
