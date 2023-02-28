package com.example.demomock;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

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


}
