import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class new_gui_library_management_system extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_gui_library_management_system frame = new new_gui_library_management_system();
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
	public new_gui_library_management_system() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToLibrary = new JLabel("Welcome To Library Management System");
		lblWelcomeToLibrary.setFont(new Font("宋体", Font.PLAIN, 18));
		lblWelcomeToLibrary.setBounds(48, 6, 424, 25);
		contentPane.add(lblWelcomeToLibrary);
		
		JButton btnBookSearchSystem = new JButton("Book Search System");
		btnBookSearchSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_gui_book_search_system gui= new new_gui_book_search_system(new_gui_library_management_system.this);
				gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				gui.setVisible(true);
			}
		});
		btnBookSearchSystem.setBounds(10, 41, 190, 58);
		contentPane.add(btnBookSearchSystem);
		
		JButton btnBookCheckOut = new JButton("Book Check Out System");
		btnBookCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_gui_check_out_system gui= new new_gui_check_out_system(new_gui_library_management_system.this);
				gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				gui.setVisible(true);
			}
		});
		btnBookCheckOut.setBounds(226, 41, 190, 58);
		contentPane.add(btnBookCheckOut);
		
		JButton btnBookCheckOut_1 = new JButton("Book Check In System");
		btnBookCheckOut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_gui_check_in_system gui= new new_gui_check_in_system(new_gui_library_management_system.this);
				gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				gui.setVisible(true);
			}
		});
		btnBookCheckOut_1.setBounds(10, 109, 190, 58);
		contentPane.add(btnBookCheckOut_1);
		
		JButton btnBorrowerManagementSystem = new JButton("Borrower Management System");
		btnBorrowerManagementSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_gui_borrower_management_system gui= new new_gui_borrower_management_system(new_gui_library_management_system.this);
				gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				gui.setVisible(true);
			}
		});
		btnBorrowerManagementSystem.setBounds(226, 109, 190, 58);
		contentPane.add(btnBorrowerManagementSystem);
		
		JButton btnFineManagementSystem = new JButton("Fine Management System");
		btnFineManagementSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_gui_fine_management_system gui= new new_gui_fine_management_system(new_gui_library_management_system.this);
				gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				gui.setVisible(true);
			}
		});
		btnFineManagementSystem.setFont(new Font("宋体", Font.PLAIN, 15));
		btnFineManagementSystem.setBounds(10, 177, 406, 50);
		contentPane.add(btnFineManagementSystem);
	}

}
