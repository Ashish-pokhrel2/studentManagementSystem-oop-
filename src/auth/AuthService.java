package auth;

import java.sql.*;


public class AuthService {
	
	private UserDAO userDAO;
	private Connection connection;
	
	private static final String URL = "jdbc:mysql://localhost:3306/studentManagementSystem";
	private static final String USER = "root";
	private static final String PASS = "";

    public AuthService() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            this.userDAO = new UserDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate(String username, String password) {
        // Credential validation
        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        
        User user = userDAO.findByUsername(username);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        // Password management - validation
        if (oldPassword == null || oldPassword.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            return false;
        }
        
        if (newPassword.length() < 4) {
            return false;
        }
        
        User user = userDAO.findByUsername(getUsernameById(userId));

        if (user == null) {
            return false;
        }

        // Validate old password
        if (!user.getPassword().equals(oldPassword)) {
            return false;
        }

        return userDAO.updatePassword(userId, newPassword);
    }
    
    private String getUsernameById(int userId) {
        String sql = "SELECT name FROM user WHERE userId = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Method to check if username exists (for registration)
    public boolean usernameExists(String username) {
        User user = userDAO.findByUsername(username);
        return user != null;
    }
    
    // Method to register a new user
    public boolean registerUser(String username, String password) {
        // Credential validation
        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        
        if (password.length() < 4) {
            return false;
        }
        
        // Check if username already exists
        if (usernameExists(username)) {
            return false;
        }
        
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        
        return userDAO.saveUser(newUser);
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
