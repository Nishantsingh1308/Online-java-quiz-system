package com.example.service;

import com.example.User;
import com.example.dao.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserDAO mockUserDAO;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        mockUserDAO = mock(UserDAO.class);
        userService = new UserService(mockUserDAO);
    }

    @Test
    public void testGetUserByUsername_Success() throws Exception {
        // Setup mock behavior
        User mockUser = new User(1, "john_doe", "john_doe@example.com");
        when(mockUserDAO.getUserByUsername("john_doe")).thenReturn(mockUser);

        // Call the method under test
        User user = userService.getUserByUsername("john_doe");

        // Verify the result
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("john_doe@example.com", user.getEmail());
    }

    @Test
    public void testGetUserByUsername_UserNotFound() throws Exception {
        // Setup mock behavior
        when(mockUserDAO.getUserByUsername("non_existing_user")).thenReturn(null);

        // Call the method under test
        User user = userService.getUserByUsername("non_existing_user");

        // Verify that user is null
        assertNull(user);
    }
}