package server;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.http.client.ClientProtocolException;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class GUI {

	private DefaultTableModel model;
	Connection con;
	
	private JFrame frmValetParkingSystem;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnPark, btnDeliver, btnUpdate;
	private JButton btnCreateDB;
	private JButton btnNewButton;
	private JTable table;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmValetParkingSystem.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public GUI() throws Exception {
		initialize();
		try {
		Class.forName("org.hsqldb.jdbcDriver");
		con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/OneDB", "SA", "");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	fillData();	
	}

//	public void getInfo() {
//		try {
//			textArea.setText(GetParkingDetails.Details().toString());
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}

	public TableModel fillData() {
		
		
		DefaultTableModel model1 = new DefaultTableModel(new String[] { "Id", "Name", "Car Number", "Area" }, 0);
		// String sql1 = "SELECT * FROM storemanagement.member";
		Statement ps;
		try {

			ps = con.createStatement();
			ResultSet rs1 = ps.executeQuery("SELECT * FROM car");

			while (rs1.next()) {
				String Id = rs1.getString(1);
				String Name = rs1.getString(2);
				String numb = rs1.getString(3);
				String area = rs1.getString(4);

				model1.addRow(new Object[] { Id, Name,numb, area });
			}

			table.setModel(model1);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return model1;

		// table.setModel(model);

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frmValetParkingSystem = new JFrame();
		frmValetParkingSystem.getContentPane().setBackground(new Color(102, 205, 170));
		frmValetParkingSystem.setTitle(" Parking System");
		frmValetParkingSystem.setBounds(100, 100, 1047, 858);
		frmValetParkingSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmValetParkingSystem.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("  ID");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Deepansh\\eclipse-workspace\\ProjectServer\\default package\\billnumber.png"));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(217, 95, 135, 32);
		frmValetParkingSystem.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Deepansh\\eclipse-workspace\\ProjectServer\\default package\\membericon.png"));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel_1.setBounds(217, 138, 162, 64);
		frmValetParkingSystem.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Car Number");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Deepansh\\eclipse-workspace\\ProjectServer\\default package\\info.png"));
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel_2.setBounds(206, 213, 175, 38);
		frmValetParkingSystem.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Area Parked");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Deepansh\\eclipse-workspace\\ProjectServer\\default package\\expirydate.png"));
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel_3.setBounds(206, 273, 177, 38);
		frmValetParkingSystem.getContentPane().add(lblNewLabel_3);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setToolTipText("gfhvb");
		textField.setBounds(414, 101, 170, 25);
		frmValetParkingSystem.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setBounds(414, 160, 170, 28);
		frmValetParkingSystem.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setBounds(414, 216, 170, 25);
		frmValetParkingSystem.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setBounds(414, 275, 170, 25);
		frmValetParkingSystem.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		btnPark = new JButton("PARK");
		btnPark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InsertClient client = new InsertClient();
				client.insert(textField_1.getText(), textField_2.getText(), textField_3.getText());
			// textArea.setText("");
				fillData();
			}
		});
		btnPark.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPark.setBounds(108, 342, 141, 32);
		frmValetParkingSystem.getContentPane().add(btnPark);

		btnDeliver = new JButton("DELETE");
		btnDeliver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

		
				try {
					DeleteItem deleteItem = new DeleteItem();
					deleteItem.deleteCarinfo(textField.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
				// textArea.setText("");
				fillData();
				}
			}
		});
		btnDeliver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeliver.setBounds(523, 342, 129, 32);
		frmValetParkingSystem.getContentPane().add(btnDeliver);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateItem item = new UpdateItem();
				item.updateCarData(textField.getText(), textField_1.getText(), textField_2.getText(),
						textField_3.getText());
				//textArea.setText("");
				fillData();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(316, 342, 129, 32);
		frmValetParkingSystem.getContentPane().add(btnUpdate);

		btnCreateDB = new JButton("Create DB");
		btnCreateDB.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCreateDB.setBounds(455, 372, 129, 25);

		JLabel lblNewLabel_4 = new JLabel("PARKING SYSTEM");
		lblNewLabel_4.setForeground(new Color(255, 140, 0));
		lblNewLabel_4.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_4.setBounds(278, 11, 352, 38);
		frmValetParkingSystem.getContentPane().add(lblNewLabel_4);

		JButton btnNewButton_1 = new JButton("Create Table");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CreateDB cd = new CreateDB();
				cd.getConnection();

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(746, 526, 175, 38);
		frmValetParkingSystem.getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 427, 594, 234);
		frmValetParkingSystem.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(Color.yellow);
		
		JButton btnCl = new JButton("CLEAR");
		btnCl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			
			}
		});
		btnCl.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCl.setBounds(760, 330, 129, 32);
		frmValetParkingSystem.getContentPane().add(btnCl);
		
		btnExit = new JButton("EXIT");
		btnExit.setIcon(new ImageIcon("C:\\Users\\Deepansh\\eclipse-workspace\\ProjectServer\\default package\\cancel.png"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(834, 11, 135, 38);
		frmValetParkingSystem.getContentPane().add(btnExit);

	}
}
