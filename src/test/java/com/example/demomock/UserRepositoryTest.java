package com.example.demomock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserRepositoryTest {
    User user1 = new User("user1", "qwerty");

    UserRepository userRepository = new UserRepository();

    @Test
    @DisplayName("When user list is empty then getAllUsers() returns empty list")
    void getEmptyUserList(){
        assertEquals(userRepository.getAllUsers(), new ArrayList<>());

    }
    @Test
    @DisplayName("When user list is filled with particular users then getAllUsers() returns list with same users")
    void getAllUsersList(){
        userRepository.addUser(user1);
        assertEquals(userRepository.getAllUsers(), List.of(user1));
    }

    @Test
    @DisplayName("When there is a user with provided login then method returns such user")
    void getUserByLogin(){
        userRepository.addUser(user1);
        assertEquals(
                Optional.of(new User("user1", "qwerty")),
                userRepository.findUserByLogin("user1"));

    }
    @Test
    @DisplayName("When there is a user with provided login and password then method returns such user")
    void getUserByLoginAndPassword(){
        userRepository.addUser(user1);
        assertEquals(
                Optional.of(new User("user1", "qwerty")),
                userRepository.findUserByLoginAndPassword("user1","qwerty"));

    }
    @Test
    @DisplayName("When there is a user with provided login and password then password matches but login does not match")
    void getUserByLoginAndPasswordLoginNoMatch(){
        userRepository.addUser(user1);
        assertNotEquals(
                new User("user0", "qwerty").getLogin(),
                userRepository.findUserByLoginAndPassword("user1","qwerty").map(u->u.getLogin()).get());

    }
    @Test
    @DisplayName("When there is a user with provided login and password then login matches but password does not match")
    void getUserByLoginAndPasswordPasswordNoMatch(){
        userRepository.addUser(user1);
        assertNotEquals(
                new User("user1", "qwert").getPassword(),
                userRepository.findUserByLoginAndPassword("user1","qwerty").map(u->u.getPassword()).get());

    }
}
