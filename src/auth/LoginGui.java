package auth;

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
import java.awt.Color;

public class LoginGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private AuthService authService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
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
	public LoginGui() {
		authService = new AuthService();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setTitle("Student Management System - Login");
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(240, 248, 255));
		setContentPane(contentPane);

		// Title Label
		JLabel lblTitle = new JLabel("Login");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
		lblTitle.setBounds(200, 30, 100, 40);
		contentPane.add(lblTitle);

		// Username Label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUsername.setBounds(80, 100, 100, 25);
		contentPane.add(lblUsername);

		// Username Text Field
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUsername.setBounds(80, 130, 340, 35);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		// Password Label
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPassword.setBounds(80, 180, 100, 25);
		contentPane.add(lblPassword);

		// Password Field
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPassword.setBounds(80, 210, 340, 35);
		contentPane.add(txtPassword);

		// Login Button
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
		btnLogin.setBounds(200, 270, 100, 40);
		btnLogin.setBackground(new Color(70, 130, 180));
		btnLogin.setForeground(Color.WHITE);
		contentPane.add(btnLogin);

		// Register Link
		JLabel lblRegister = new JLabel("Don't have an account? Register");
		lblRegister.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRegister.setForeground(new Color(0, 0, 255));
		lblRegister.setBounds(150, 320, 200, 20);
		contentPane.add(lblRegister);

		// Login Button Action
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText().trim();
				String password = new String(txtPassword.getPassword());

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				boolean authenticated = authService.authenticate(username, password);
				
				if (authenticated) {
					JOptionPane.showMessageDialog(null, "Login successful! Welcome " + username, 
							"Success", JOptionPane.INFORMATION_MESSAGE);
					// Close login window and open main application
					dispose();
					// You can open your main application window here
					// For example: new MainApplication().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username or password!", 
							"Login Failed", JOptionPane.ERROR_MESSAGE);
					txtPassword.setText("");
				}
			}
		});

		// Register Link Action
		lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				dispose();
				RegisterGui registerFrame = new RegisterGui();
				registerFrame.setVisible(true);
			}
		});
	}
}
