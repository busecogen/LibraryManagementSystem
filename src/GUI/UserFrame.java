package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.LibraryMaterial;
import System.LibrarySystem;
import user.Student;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class UserFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame thisis = this;
	private JComboBox<String> comboBox; 
	LibraryFrame lf=null;
	private JTextArea matText;
	private JTextField nameText;
	private JTextField depText;
	private JTextField mailText;
	private JTextField fineText;
	private JTextField textId;
	private JLabel lblTitle;
	AddFrame af= new AddFrame(this);
	
	public void clean() {
		nameText.setText("");
		depText.setText("");
		mailText.setText("");
		fineText.setText("");
		matText.setText("");
		textId.setText("");
	}
	
	
	public JComboBox<String> getComboBox_CMB() {
		return comboBox;
	}
	
	
	
	public UserFrame(JFrame l) {
		setTitle("User Frame");
		lf=(LibraryFrame)l;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(151, 33, 141, 34);
		contentPane.add(comboBox);
		
		lblTitle = new JLabel("Select Student :");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblTitle.setBounds(10, 30, 145, 34);
		contentPane.add(lblTitle);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(21, 88, 272, 231);
		contentPane.add(verticalBox);
		
		JLabel namelbl = new JLabel("Name :");
		verticalBox.add(namelbl);
		namelbl.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		
		nameText = new JTextField();
		verticalBox.add(nameText);
		nameText.setColumns(10);
		
		JLabel deplbl = new JLabel("Department : ");
		verticalBox.add(deplbl);
		deplbl.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		
		depText = new JTextField();
		verticalBox.add(depText);
		depText.setColumns(10);
		
		JLabel mail = new JLabel("E-Mail :");
		verticalBox.add(mail);
		mail.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		
		mailText = new JTextField();
		verticalBox.add(mailText);
		mailText.setColumns(10);
		
		JLabel fine = new JLabel("Fine : ");
		verticalBox.add(fine);
		fine.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		
		fineText = new JTextField();
		verticalBox.add(fineText);
		fineText.setColumns(10);
		
		JButton btnAddStudent = new JButton("Add New Student");
		btnAddStudent.setBackground(new Color(217, 221, 247));
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				af.setVisible(true);
				dispose();
			}
		});
		btnAddStudent.setFont(new Font("Verdana", Font.BOLD, 14));
		btnAddStudent.setBounds(45, 342, 193, 34);
		contentPane.add(btnAddStudent);
		
		JButton btnReturnMaterial = new JButton("Return Material");
		btnReturnMaterial.setBackground(new Color(217, 221, 247));
		btnReturnMaterial.setFont(new Font("Verdana", Font.BOLD, 13));
		btnReturnMaterial.setBounds(351, 356, 154, 34);
		contentPane.add(btnReturnMaterial);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(351, 30, 318, 251);
		contentPane.add(scrollPane);
		
		matText = new JTextArea();
		matText.setBackground(new Color(242, 243, 253));
		scrollPane.setViewportView(matText);
		
		textId = new JTextField();
		textId.setBounds(525, 293, 154, 27);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBackground(new Color(217, 221, 247));
		btnClose.setFont(new Font("Verdana", Font.BOLD, 14));
		btnClose.setBounds(464, 400, 95, 34);
		contentPane.add(btnClose);
		
		JButton btnAddMaterial = new JButton("Add Material");
		btnAddMaterial.setBackground(new Color(217, 221, 247));
		btnAddMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibraryMaterial temp = LibrarySystem.searchMaterial(textId.getText());
				if(temp==null) {
					JOptionPane.showMessageDialog(thisis, "Enter correct id to add!");
				}else if(Integer.parseInt(temp.getBorrowingDate())!=0 ){
					JOptionPane.showMessageDialog(thisis, temp.getName()+" is already borrowed!!!");
				}else {
					JOptionPane.showMessageDialog(thisis, temp.getName() + " is borrowed");
					Student stu= null;
					stu = LibrarySystem.searchStudent(Integer.parseInt((String)comboBox.getSelectedItem()));
					stu.addMaterial(temp);
					
					LibrarySystem.borrowedMaterial.add(temp);
					temp.setBorrowingDate(String.valueOf(LibrarySystem.systemDate));
					temp.setTotalDay(0);
					matText.setText(stu.displayOwnedMaterials());
				}
			}
		});
		btnAddMaterial.setFont(new Font("Verdana", Font.BOLD, 13));
		btnAddMaterial.setBounds(515, 356, 154, 34);
		contentPane.add(btnAddMaterial);
		
		JLabel labelRtrn_1 = new JLabel("Enter Material ID:");
		labelRtrn_1.setFont(new Font("Verdana", Font.BOLD, 13));
		labelRtrn_1.setBounds(361, 291, 165, 28);
		contentPane.add(labelRtrn_1);
		
		JCheckBox lostCheck = new JCheckBox("Allow returning lost materials");
		lostCheck.setFont(new Font("Verdana", Font.PLAIN, 13));
		lostCheck.setBounds(351, 326, 221, 21);
		contentPane.add(lostCheck);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt((String)comboBox.getSelectedItem());
				Student stu= LibrarySystem.searchStudent(id);
				nameText.setText(stu.getName()+" " +stu.getSurname());
				depText.setText(stu.getDeptName());
				mailText.setText(stu.getEmail());
				fineText.setText(stu.getFine()+"");
				matText.setText(stu.displayOwnedMaterials());
				
				
			}
		});
		
		
		btnReturnMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibraryMaterial temp = LibrarySystem.searchMaterial(textId.getText());
				if(temp==null) {
					JOptionPane.showMessageDialog(thisis, "Enter correct id to return!!");
				}else if(temp.isLost()&&!lostCheck.isSelected()) {
					JOptionPane.showMessageDialog(thisis, "The lost material cannot be returned!!");
				}else {
					temp.setRetDate(LibrarySystem.systemDate+"");
					JOptionPane.showMessageDialog(thisis, temp.getName()+" is returned to library!");
					
					int id= Integer.parseInt((String)comboBox.getSelectedItem());
					Student stu= LibrarySystem.searchStudent(id);
					
					temp.setTotalDay((Integer.parseInt(temp.getRetDate()) - Integer.parseInt(temp.getBorrowingDate())));
					LibrarySystem.calculateStuFine(stu);
					fineText.setText(stu.getFine()+"");
					matText.setText(stu.displayOwnedMaterials());
					
					LibrarySystem.borrowedMaterial.remove(temp);
					stu.getOwnedMaterial().remove(temp);
					
					if(stu.getOwnedMaterial()==null)
						LibrarySystem.deleteStudent(id);
					
				}
				
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(LibraryMaterial lm : LibrarySystem.libraryMaterial) {
					if(Integer.parseInt(lm.getRetDate())!=0) {
						lm.setBorrowingDate("0");
						lm.setRetDate("0");
						lm.setTotalDay(0);
					}
				}
				clean();
				setVisible(false);
				lf.setVisible(true);
			}
		});
	}
}
