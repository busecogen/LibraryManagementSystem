package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import Classes.LibraryMaterial;
import System.LibrarySystem;
import user.Student;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class AddFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame thisis = this;
	UserFrame uf = null;
	private JTextField stuIdTF;
	private JTextField nameTF;
	private JTextField surnameTF;
	private JTextField deptTF;
	private JTextField emailTF;
	private JComboBox<String> comboBox;

	public void fillCombo() {
		ArrayList<String> out = new ArrayList<String>();
		for (LibraryMaterial lm : LibrarySystem.libraryMaterial) {
			if (lm.getBorrowingDate().equalsIgnoreCase("0")) {
				out.add(lm.getId());
			}
		}
		String[] out_arr = out.toArray(new String[0]);
		Arrays.sort(out_arr);
		comboBox.setModel(new DefaultComboBoxModel<String>(out_arr));
	}

	public AddFrame(UserFrame userFrame) {
		setTitle("Add Student");
		uf = userFrame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Student Info:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 110, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Student ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 60, 95, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Department:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(259, 60, 140, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Name:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 124, 78, 14);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Email:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(259, 124, 116, 14);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Surname:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(10, 189, 110, 14);
		contentPane.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_6 = new JLabel("Material ID:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_6.setBounds(259, 189, 140, 14);
		contentPane.add(lblNewLabel_1_6);

		stuIdTF = new JTextField();
		stuIdTF.setBounds(97, 55, 129, 30);
		contentPane.add(stuIdTF);
		stuIdTF.setColumns(10);

		JButton addBtn = new JButton("Add");
		addBtn.setBackground(new Color(217, 221, 247));
		addBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stuIdTF.getText().isEmpty() || nameTF.getText().isEmpty() || surnameTF.getText().isEmpty()
						|| deptTF.getText().isEmpty() || emailTF.getText().isEmpty())
					JOptionPane.showMessageDialog(thisis, "Please fill all the fields!");
				else {
					LibraryMaterial lm = null;

					lm = LibrarySystem.searchMaterial((String) comboBox.getSelectedItem());
					Student stu = new Student(stuIdTF.getText(), nameTF.getText(), surnameTF.getText(),
							deptTF.getText(), emailTF.getText());

					if (!LibrarySystem.addStudent(stu)) {
						JOptionPane.showMessageDialog(thisis,
								"The student id exists in the system, please add material from the User Frame");
					} else {

						stu.addMaterial(lm);
						LibrarySystem.borrowedMaterial.add(lm);
						lm.setBorrowingDate(String.valueOf(LibrarySystem.systemDate));
						lm.setTotalDay(0);
						JOptionPane.showMessageDialog(thisis, "The student has been added to the system!");

					}
				}
			}
		});
		addBtn.setBounds(335, 260, 116, 35);
		contentPane.add(addBtn);

		JButton closeBtn = new JButton("Close");
		closeBtn.setBackground(new Color(217, 221, 247));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uf.lf.fillComboBox();
				uf.setVisible(true);
				dispose();
			}
		});
		closeBtn.setBounds(97, 255, 116, 40);
		contentPane.add(closeBtn);

		nameTF = new JTextField();
		nameTF.setColumns(10);
		nameTF.setBounds(98, 119, 129, 30);
		contentPane.add(nameTF);

		surnameTF = new JTextField();
		surnameTF.setColumns(10);
		surnameTF.setBounds(97, 184, 129, 30);
		contentPane.add(surnameTF);

		deptTF = new JTextField();
		deptTF.setColumns(10);
		deptTF.setBounds(366, 55, 129, 30);
		contentPane.add(deptTF);

		emailTF = new JTextField();
		emailTF.setColumns(10);
		emailTF.setBounds(366, 119, 129, 30);
		contentPane.add(emailTF);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(366, 183, 129, 30);
		contentPane.add(comboBox);
		fillCombo();
	}
}
