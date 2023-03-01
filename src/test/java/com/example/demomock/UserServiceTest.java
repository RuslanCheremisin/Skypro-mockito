package com.example.demomock;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("When user list is empty then getAllUsers() returns empty list")
    void getEmptyUserLoginList(){
        when(userRepository.getAllUsers()).thenReturn(new ArrayList<>());
        assertEquals(userService.getAllUserLogins(), new ArrayList<>());

    }

    @Test
    @DisplayName("When user list is not empty then getAllUsers() returns list of logins")
    void getUserLoginList(){
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("user1", "qwerty")));
        assertEquals(List.of("user1"), userService.getAllUserLogins());

    }

    @Test
    @DisplayName("When login is blank addUser throws exception")
    void addUserWithBlankLogin(){
        assertThatThrownBy(() -> userService.addUser(" ", "qwerty"))
                .isInstanceOf(IllegalArgumentException.class);
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }
    @Test
    @DisplayName("When login is empty addUser throws exception")
    void addUserWithEmptyLogin(){
        assertThatThrownBy(() -> userService.addUser("", "qwerty"))
                .isInstanceOf(IllegalArgumentException.class);
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }
    @Test
    @DisplayName("When login is null addUser throws exception")
    void addUserWithNullLogin(){
        assertThatThrownBy(() -> userService.addUser(null, "qwerty"))
                .isInstanceOf(IllegalArgumentException.class);
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }

    @Test
    @DisplayName("When password is blank addUser throws exception")
    void addUserWithBlankPassword(){
        assertThatThrownBy(() -> userService.addUser("user1", " "))
                .isInstanceOf(IllegalArgumentException.class);
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }
    @Test
    @DisplayName("When password is empty addUser throws exception")
    void addUserWithEmptyPassword(){
        assertThatThrownBy(() -> userService.addUser("user1", ""))
                .isInstanceOf(IllegalArgumentException.class);
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }
    @Test
    @DisplayName("When password is null addUser throws exception")
    void addUserWithNullPassword(){
        assertThatThrownBy(() -> userService.addUser("user1", null))
                .isInstanceOf(IllegalArgumentException.class);
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }

    @Test
    @DisplayName("When user already exists addUser throws UserNonUniqueException")
    void addExistingUser(){
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("user1", "qwerty")));
        assertThatThrownBy(() -> userService.addUser("user1", "qwerty"))
                .isInstanceOf(UserNonUniqueException.class);
    }

    @Test
    @DisplayName("When one logs in with non existing login false value is returned")
    void logInWithNonExistingLogin(){
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("user1", "qwerty")));
        assertEquals(false, userService.logIn("user2", "qwerty"));
    }

    @Test
    @DisplayName("When one logs in with invalid password false value is returned")
    void logInWithInvalidPassword(){
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("user1", "qwerty")));
        assertEquals(false, userService.logIn("user1", "qwert"));

    }

    @Test
    @DisplayName("When one logs in with existing login and correct password true value is returned")
    void logInWithCorrectCredentials(){
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("user1", "qwerty")));
        assertEquals(true, userService.logIn("user1", "qwerty"));

    }


}
