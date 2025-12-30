package auth;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private AuthService authService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGui frame = new RegisterGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGui() {
		// Initialize AuthService
		authService = new AuthService();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		setTitle("Student Management System - Register");
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(240, 248, 255));
		setContentPane(contentPane);

		// Title Label
		JLabel lblTitle = new JLabel("Register");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
		lblTitle.setBounds(180, 20, 150, 40);
		contentPane.add(lblTitle);

		// Username Label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUsername.setBounds(80, 80, 100, 25);
		contentPane.add(lblUsername);

		// Username Text Field
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUsername.setBounds(80, 110, 340, 35);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		// Password Label
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPassword.setBounds(80, 160, 100, 25);
		contentPane.add(lblPassword);

		// Password Field
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPassword.setBounds(80, 190, 340, 35);
		contentPane.add(txtPassword);

		// Confirm Password Label
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(80, 240, 150, 25);
		contentPane.add(lblConfirmPassword);

		// Confirm Password Field
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		txtConfirmPassword.setBounds(80, 270, 340, 35);
		contentPane.add(txtConfirmPassword);

		// Register Button
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegister.setBounds(200, 330, 100, 40);
		btnRegister.setBackground(new Color(70, 130, 180));
		btnRegister.setForeground(Color.WHITE);
		contentPane.add(btnRegister);

		// Login Link
		JLabel lblLogin = new JLabel("Already have an account? Login");
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLogin.setForeground(new Color(0, 0, 255));
		lblLogin.setBounds(140, 380, 220, 20);
		contentPane.add(lblLogin);

		// Register Button Action
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText().trim();
				String password = new String(txtPassword.getPassword());
				String confirmPassword = new String(txtConfirmPassword.getPassword());

				// Validation
				if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (password.length() < 4) {
					JOptionPane.showMessageDialog(null, "Password must be at least 4 characters long!", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", 
							JOptionPane.ERROR_MESSAGE);
					txtPassword.setText("");
					txtConfirmPassword.setText("");
					return;
				}

				// Check if username already exists and register user
				boolean saved = authService.registerUser(username, password);
				
				if (!saved && authService.usernameExists(username)) {
					JOptionPane.showMessageDialog(null, "Username already exists! Please choose another.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (saved) {
					JOptionPane.showMessageDialog(null, "Registration successful! You can now login.", 
							"Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					LoginGui loginFrame = new LoginGui();
					loginFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Registration failed! Please try again.", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Login Link Action
		lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				dispose();
				LoginGui loginFrame = new LoginGui();
				loginFrame.setVisible(true);
			}
		});
	}
}
