package com.example.demomock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final List<User> userList = new ArrayList<>();

    public List<User> getAllUsers() {
        return List.copyOf(userList);
    }

    public Optional<User> findUserByLogin(String login) {
        return userList.stream().filter(u -> u.getLogin().equals(login)).findFirst();
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return userList.stream().filter(u -> (u.getLogin().equals(login) && u.getPassword().equals(password))).findFirst();
    }

    public void addUser(User user){
        userList.add(user);
    }

}
