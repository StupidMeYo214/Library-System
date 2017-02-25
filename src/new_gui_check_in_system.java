import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class new_gui_check_in_system extends JDialog {

	private List<String> model2 = new ArrayList();
	
	private JPanel contentPane;
	private JTextField tlid;
	private JTextField tisbn;
	private JTextField tcn;
	private JTextField tfn;
	private JTextField tln;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_gui_check_in_system frame = new new_gui_check_in_system();
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
	public new_gui_check_in_system(JFrame jFrame) {
		super(jFrame,"Book Check In System",true);
		//setTitle("Book Check In System");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		tlid = new JTextField();
		tlid.setBounds(336, 174, 124, 26);
		contentPane.add(tlid);
		tlid.setColumns(10);
		
		tisbn = new JTextField();
		tisbn.setBounds(180, 15, 408, 21);
		contentPane.add(tisbn);
		tisbn.setColumns(10);
		
		tcn = new JTextField();
		tcn.setColumns(10);
		tcn.setBounds(180, 55, 408, 21);
		contentPane.add(tcn);
		
		tfn = new JTextField();
		tfn.setColumns(10);
		tfn.setBounds(180, 95, 408, 21);
		contentPane.add(tfn);
		
		tln = new JTextField();
		tln.setColumns(10);
		tln.setBounds(180, 135, 408, 21);
		contentPane.add(tln);
		

		
		JLabel lblNewLabel = new JLabel("Search Results :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 206, 117, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblBookIdisbn = new JLabel("Book ID/ISBN :");
		lblBookIdisbn.setFont(new Font("宋体", Font.PLAIN, 15));
		lblBookIdisbn.setBounds(10, 10, 150, 30);
		contentPane.add(lblBookIdisbn);
		
		JLabel lblCardNumber = new JLabel("Card Number :");
		lblCardNumber.setFont(new Font("宋体", Font.PLAIN, 15));
		lblCardNumber.setBounds(10, 50, 150, 30);
		contentPane.add(lblCardNumber);
		
		JLabel lblBorrowerFirstName = new JLabel("Borrower First Name");
		lblBorrowerFirstName.setFont(new Font("宋体", Font.PLAIN, 15));
		lblBorrowerFirstName.setBounds(10, 90, 170, 30);
		contentPane.add(lblBorrowerFirstName);
		
		JLabel lblBorrowerLastName = new JLabel("Borrower Last Name : ");
		lblBorrowerLastName.setFont(new Font("宋体", Font.PLAIN, 15));
		lblBorrowerLastName.setBounds(10, 130, 170, 30);
		contentPane.add(lblBorrowerLastName);
		
		JLabel lblLoanId = new JLabel("Loan ID :");
		lblLoanId.setFont(new Font("宋体", Font.PLAIN, 15));
		lblLoanId.setBounds(225, 171, 150, 30);
		contentPane.add(lblLoanId);
		
		JList list = new JList();
		
		JButton bsearch = new JButton("SEARCH");
		bsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String BID=""+tisbn.getText();
				String CID=""+tcn.getText();
				String BFN=""+tfn.getText();
				String BLN=""+tln.getText();
				
				DefaultListModel<String> model1=new DefaultListModel<>();
				
				
				for(int i=0; i<CheckinSystem.searchToArray(BID, CID, BFN, BLN).length; i++)
				{model1.addElement(CheckinSystem.searchToArray(BID, CID, BFN, BLN)[i]);}
				model2.clear();
				for(int j = 0 ; j<CheckinSystem.searchLoan_idToArray(BID, CID, BFN, BLN).length; j++)
				{model2.add(CheckinSystem.searchLoan_idToArray(BID, CID, BFN, BLN)[j]);}
			
				list.setModel(model1);
			}
			
		});
		bsearch.setBounds(10, 170, 93, 23);
		contentPane.add(bsearch);
		
		JLabel tresult = new JLabel("");
		tresult.setBounds(174, 208, 286, 30);
		contentPane.add(tresult);
		
		//JList list = new JList(model1);
		
		list.setBounds(10, 248, 460, 201);
		
		list.setVisibleRowCount(6);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 248, 578, 202);
		contentPane.add(scrollPane);
		
		list.addListSelectionListener(
				new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						String temp=model2.get(list.getSelectedIndex());
						tlid.setText(temp);
					}
				}
		);
		
		JButton bcheckin = new JButton("CHECK IN");
		bcheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String LID=""+tlid.getText();
				tresult.setText(LID);
			}
		});
		bcheckin.setBounds(495, 174, 93, 23);
		contentPane.add(bcheckin);

		


	}
}
