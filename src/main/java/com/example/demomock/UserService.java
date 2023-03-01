package com.example.demomock;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllUserLogins() {
        return userRepository.getAllUsers().stream()
                .map(User::getLogin)
                .collect(Collectors.toList());
    }

    public boolean addUser(String login, String password) {
        if (login == null || login.isBlank() || login.isEmpty()) {
            throw new IllegalArgumentException("Login cannot be blank, empty or null!");
        }
        if (password == null || password.isBlank() || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be blank, empty or null!");
        }
        if (userRepository.getAllUsers().contains(new User(login, password))) {
            throw new UserNonUniqueException();
        }
        userRepository.addUser(new User(login, password));
        return true;
    }

    public boolean logIn(String login, String password) {
        if (userRepository.getAllUsers().contains(new User(login, password))) {
            return true;
        }
        return false;
    }
}
