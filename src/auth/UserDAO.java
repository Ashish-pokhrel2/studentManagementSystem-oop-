package auth;

import java.sql.*;

public class UserDAO {
	
	Connection connection;
	
	User findByUsername(String username) {
		return null;
		}
	
	boolean saveUser(User user) {
		return false;
		}
	
	boolean updatePassword(int userId, String newPassword) {
		return false;
		}
	
	boolean deleteUser(int userId) {
		return false;
		}
	

}
