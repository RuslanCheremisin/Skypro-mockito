package com.example.demomock;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public List<String> getAllUserLogins(){
        return userRepository.getAllUsers().stream()
                .map(User::getLogin)
                .collect(Collectors.toList());
    }
    public boolean addUser(String login, String password) throws UserNonUniqueException {
        if (login.isBlank()||login.isEmpty()||login.equals(null)){
            throw new IllegalArgumentException("Login cannot be blank, empty or null!");
        }
        if (password.isBlank()||password.isEmpty()||password.equals(null)){
            throw new IllegalArgumentException("Password cannot be blank, empty or null!");
        }
        if (userRepository.getAllUsers().contains(new User(login,password))){
            throw new UserNonUniqueException("User with such login already exists!");
        }
        userRepository.addUser(new User(login,password));
        return true;
    }

    public boolean logIn(String login, String password){
        if (userRepository.getAllUsers().contains(new User(login,password))){
            return true;
        }
        return false;
    }
}
