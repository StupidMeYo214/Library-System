import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class new_gui_borrower_management_system extends JDialog {

	private JPanel contentPane;
	private JTextField tssn;
	private JTextField tfn;
	private JTextField tln;
	private JTextField taddress;
	private JTextField tpn;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_gui_borrower_management_system frame = new new_gui_borrower_management_system();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public new_gui_borrower_management_system(Frame jFrame) {
		super(jFrame,"Borrower Management System",true);
		//setTitle("Borrower Management System");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseEnterYour = new JLabel("* SSN (9 digit number): ");
		lblPleaseEnterYour.setFont(new Font("宋体", Font.PLAIN, 15));
		lblPleaseEnterYour.setBounds(10, 10, 200, 30);
		contentPane.add(lblPleaseEnterYour);
		
		JLabel lblPleaseEnterYour_1 = new JLabel("* First Name:");
		lblPleaseEnterYour_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblPleaseEnterYour_1.setBounds(10, 45, 200, 30);
		contentPane.add(lblPleaseEnterYour_1);
		
		JLabel lblPleaseEnterYour_2 = new JLabel("* Last Name:");
		lblPleaseEnterYour_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblPleaseEnterYour_2.setBounds(10, 89, 200, 30);
		contentPane.add(lblPleaseEnterYour_2);
		
		JLabel lblPleaseEnterYour_3 = new JLabel("* Address: ");
		lblPleaseEnterYour_3.setFont(new Font("宋体", Font.PLAIN, 15));
		lblPleaseEnterYour_3.setBounds(10, 125, 200, 30);
		contentPane.add(lblPleaseEnterYour_3);
		
		JLabel lblPleaseEnterYour_4 = new JLabel("Phone Number: ");
		lblPleaseEnterYour_4.setFont(new Font("宋体", Font.PLAIN, 15));
		lblPleaseEnterYour_4.setBounds(10, 165, 200, 30);
		contentPane.add(lblPleaseEnterYour_4);
		
		tssn = new JTextField();
		tssn.setBounds(199, 15, 200, 30);
		contentPane.add(tssn);
		tssn.setColumns(10);
		
		tfn = new JTextField();
		tfn.setBounds(199, 50, 200, 30);
		contentPane.add(tfn);
		tfn.setColumns(10);
		
		tln = new JTextField();
		tln.setColumns(10);
		tln.setBounds(199, 85, 200, 30);
		contentPane.add(tln);
		
		taddress = new JTextField();
		taddress.setColumns(10);
		taddress.setBounds(199, 125, 200, 30);
		contentPane.add(taddress);
		
		tpn = new JTextField();
		tpn.setColumns(10);
		tpn.setBounds(199, 165, 200, 30);
		contentPane.add(tpn);
		
		JLabel lblPleaseNoteThat = new JLabel("Please Note That * Means This Information Is Required");
		lblPleaseNoteThat.setBounds(10, 205, 389, 15);
		contentPane.add(lblPleaseNoteThat);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 288, 389, 139);
		contentPane.add(scrollPane);
		
		JTextArea tresult = new JTextArea();
		scrollPane.setViewportView(tresult);
		JButton bcreate = new JButton("Create New Account");
		
		bcreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Fname=""+tfn.getText();
				String Lname=""+tln.getText();
				String Ssn=""+tssn.getText();
				String Address=""+taddress.getText();
				String Phone=""+tpn.getText();
				
				String result=""+BorrowerManagementSystem.CreateBorrowerID(Ssn, Fname, Lname, Address, Phone);
				tresult.setText(result);
			}
		});
		bcreate.setBounds(10, 230, 188, 23);
		contentPane.add(bcreate);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(10, 263, 54, 15);
		contentPane.add(lblResult);
		
		
	}

}
