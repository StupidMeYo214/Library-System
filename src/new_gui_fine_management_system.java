import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class new_gui_fine_management_system extends JDialog {

	private JPanel contentPane;
	private JTextField tCID;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_gui_fine_management_system frame = new new_gui_fine_management_system();
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
	public new_gui_fine_management_system(JFrame jFrame) {
		super(jFrame,"Fine Management System",true);
		//setTitle("Fine Management System");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseRefreshThe = new JLabel("Please Refresh The Fine Data");
		lblPleaseRefreshThe.setFont(new Font("宋体", Font.PLAIN, 15));
		lblPleaseRefreshThe.setBounds(10, 0, 233, 47);
		contentPane.add(lblPleaseRefreshThe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(253, 34, 296, 202);
		contentPane.add(scrollPane);
		
		JTextArea tresult = new JTextArea();
		scrollPane.setViewportView(tresult);
		
		JButton brefresh = new JButton("REFRESH");
		brefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			tresult.setText(FineManegementSystem.refresh());
			}
		});
		brefresh.setBounds(10, 34, 220, 32);
		contentPane.add(brefresh);
		
		JLabel lblPleaseEnterThe = new JLabel("Please Enter The Card ID :");
		lblPleaseEnterThe.setFont(new Font("宋体", Font.PLAIN, 15));
		lblPleaseEnterThe.setBounds(10, 76, 220, 32);
		contentPane.add(lblPleaseEnterThe);
		
		tCID = new JTextField();
		tCID.setBounds(10, 109, 220, 32);
		contentPane.add(tCID);
		tCID.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=tCID.getText();
				tresult.setText(FineManegementSystem.check(id));
			}
		});
		btnSearch.setBounds(10, 156, 220, 32);
		contentPane.add(btnSearch);
		
		JButton btnPay = new JButton("PAY");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=tCID.getText();
				tresult.setText(FineManegementSystem.pay(id));
			}
		});
		btnPay.setBounds(10, 207, 220, 32);
		contentPane.add(btnPay);
		

	}
}
