import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class new_gui_check_out_system extends JDialog {

	private JPanel contentPane;
	private JTextField tisbn;
	private JTextField tbranchid;
	private JTextField tcardnumber;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_gui_check_out_system frame = new new_gui_check_out_system();
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
	public new_gui_check_out_system(JFrame jFrame) {
		
		super(jFrame,"Book Checking Out System",true );
		//setTitle("Book Checking Out System");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("宋体", Font.PLAIN, 18));
		lblIsbn.setBounds(10, 20, 109, 36);
		contentPane.add(lblIsbn);
		
		JLabel lblBranchId = new JLabel("Branch ID:");
		lblBranchId.setFont(new Font("宋体", Font.PLAIN, 18));
		lblBranchId.setBounds(10, 81, 109, 36);
		contentPane.add(lblBranchId);
		
		JLabel lblCardNumber = new JLabel("Card Number:");
		lblCardNumber.setFont(new Font("宋体", Font.PLAIN, 18));
		lblCardNumber.setBounds(10, 140, 109, 36);
		contentPane.add(lblCardNumber);
		
		tisbn = new JTextField();
		tisbn.setBounds(62, 30, 169, 21);
		contentPane.add(tisbn);
		tisbn.setColumns(10);
		
		tbranchid = new JTextField();
		tbranchid.setBounds(99, 91, 132, 21);
		contentPane.add(tbranchid);
		tbranchid.setColumns(10);
		
		tcardnumber = new JTextField();
		tcardnumber.setBounds(122, 150, 109, 21);
		contentPane.add(tcardnumber);
		tcardnumber.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(245, 20, 179, 200);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnCheckOut = new JButton("CHECK OUT");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String isbn=tisbn.getText();
			String branchid=tbranchid.getText();
			String cardnumber=tcardnumber.getText();
			textArea.setText(CheckOutSystem.checkout(isbn, branchid, cardnumber));
			}
		});
		btnCheckOut.setBounds(10, 186, 221, 36);
		contentPane.add(btnCheckOut);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		btnOk.setBounds(331, 229, 93, 23);
		contentPane.add(btnOk);
	}
}
