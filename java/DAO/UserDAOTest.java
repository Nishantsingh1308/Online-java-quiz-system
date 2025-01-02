package com.example.dao;

import com.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDAOTest {

    private Connection mockConnection;
    private UserDAO userDAO;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        userDAO = new UserDAO(mockConnection);
    }

    @Test
    public void testGetUserByUsername_Success() throws SQLException {
        // Setup mock ResultSet behavior
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("username")).thenReturn("john_doe");
        when(mockResultSet.getString("email")).thenReturn("john_doe@example.com");

        // Call the method under test
        User user = userDAO.getUserByUsername("john_doe");

        // Verify the result
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("john_doe@example.com", user.getEmail());
    }

    @Test
    public void testGetUserByUsername_UserNotFound() throws SQLException {
        // Setup mock ResultSet behavior
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Call the method under test
        User user = userDAO.getUserByUsername("non_existing_user");

        // Verify that user is null
        assertNull(user);
    }

    @Test
    public void testAddUser_Success() throws SQLException {
        User user = new User(1, "new_user", "new_user@example.com");

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        // Call the method under test
        userDAO.addUser(user);

        // Verify the execution of insert
        verify(mockStatement, times(1)).executeUpdate();
    }
}
