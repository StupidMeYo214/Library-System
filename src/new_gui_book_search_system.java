import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class new_gui_book_search_system extends JDialog {

	String ISBN="";
	String Title="";
	String Author="";
	String result="";
	
	private JPanel contentPane;
	private JTextField tisbn;
	private JTextField tauthor;
	private JTextField tbooktitle;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_gui_book_search_system frame = new new_gui_book_search_system();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public new_gui_book_search_system(JFrame frame) {
		super(frame,"Book Search System",true);
		//setTitle("Book Search System");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("Book Search System");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel TISBN = new JLabel("ISBN");
		TISBN.setHorizontalAlignment(SwingConstants.LEFT);
		TISBN.setFont(new Font("宋体", Font.PLAIN, 18));
		TISBN.setBounds(10, 23, 88, 30);
		contentPane.add(TISBN);
		
		tisbn = new JTextField();
		tisbn.setBounds(108, 26, 80, 30);
		contentPane.add(tisbn);
		tisbn.setColumns(10);
		
		JLabel lblHi = new JLabel("Book Author");
		lblHi.setFont(new Font("宋体", Font.PLAIN, 15));
		lblHi.setBounds(10, 63, 88, 30);
		contentPane.add(lblHi);
		
		tauthor = new JTextField();
		tauthor.setBounds(108, 63, 80, 30);
		contentPane.add(tauthor);
		tauthor.setColumns(10);
		
		JLabel lblBookTitle = new JLabel("Book Title");
		lblBookTitle.setFont(new Font("宋体", Font.PLAIN, 15));
		lblBookTitle.setBounds(10, 103, 88, 30);
		contentPane.add(lblBookTitle);
		
		tbooktitle = new JTextField();
		tbooktitle.setBounds(108, 103, 80, 30);
		contentPane.add(tbooktitle);
		tbooktitle.setColumns(10);
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 28, 393, 224);
		contentPane.add(scrollPane);
		
		JTextArea tresult = new JTextArea();
		scrollPane.setViewportView(tresult);
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ISBN="";
				String Title="";
				String Author="";
				String result="";
				ISBN=ISBN+tisbn.getText();
				Title=Title+tbooktitle.getText();
				Author=Author+tauthor.getText();
				result="Branch_id	Branch_name	Copies	Available	ISBN	Authors	Book Title\n"+BookSearchSystem.search(ISBN, Title, Author);
				tresult.setText(result);
			}
		});
		btnNewButton.setBounds(10, 162, 178, 23);
		contentPane.add(btnNewButton);
		
	
	
		
	}
}
