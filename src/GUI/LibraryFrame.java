package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import Classes.Book;
import Classes.LibraryMaterial;
import Classes.MultiMedia;
import System.LibrarySystem;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LibraryFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel dayLabel;
	private JPanel contentPane;
	private JFrame thisis = this;
	UserFrame uf = new UserFrame(this);
	private JTextField dayTF;
	private JTextArea materialArea;
	private JComboBox<String> searchBox;
	private JComboBox<String> displayBox;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton bookRadioButton;
	private JRadioButton multimediaRadioButton;

	public void fillComboBox() {
		uf.getComboBox_CMB().setModel(new DefaultComboBoxModel<String>(LibrarySystem.getStudentId()));
	}
	
	public void fillSearchBox() {
		ArrayList<String> out = new ArrayList<String>();
		if(bookRadioButton.isSelected()) {
			for(Book bl:LibrarySystem.bookList)
				out.add(bl.getId());
		}else {
			for(MultiMedia ml:LibrarySystem.multimediaList)
				out.add(ml.getId());
		}
		
		String[] out_arr = out.toArray(new String[0]);
		Arrays.sort(out_arr);
		searchBox.setModel(new DefaultComboBoxModel<String>(out_arr));
		
		
		
	}
	
	public void showAllMaterial() {
		if (Integer.parseInt(dayTF.getText()) < LibrarySystem.systemDate) {
			JOptionPane.showMessageDialog(thisis, "Please do not enter days less than current day");
		} else {
			materialArea.setText("");
			for (LibraryMaterial lm : LibrarySystem.libraryMaterial) {
				if (lm.getBorrowingDate().equalsIgnoreCase("0"))
					lm.setTotalDay(0);
				else {
					LibrarySystem.systemDate = Integer.parseInt(dayTF.getText());
					lm.setTotalDay(LibrarySystem.systemDate - Integer.parseInt(lm.getBorrowingDate()));
				}
			}
			dayLabel.setText(LibrarySystem.systemDate + "");
			String item=(String)displayBox.getSelectedItem();
			if(item.equalsIgnoreCase("All")) {
				String out = "***************************************\n"
						+ "              Available Library Materials               \n"
						+ "***************************************\n";

				for (LibraryMaterial lm : LibrarySystem.libraryMaterial) {
					out += lm.toString() + "-".repeat(50) + "\n";
				}
				materialArea.setText(out);
				
			}else if(item.equalsIgnoreCase("Lost Material")) {
				String out = "***************************************\n"
						+ "              Lost Materials               \n"
						+ "***************************************\n";
				for (LibraryMaterial lm : LibrarySystem.libraryMaterial) {
					if (lm.isLost()) {
						out += lm.toString() + "-".repeat(50) + "\n";
					}
					materialArea.setText(out);
				}
			}else {
				materialArea.setText("***************************************\n"
						+ "            Borrowed Materials              \n"
						+ "***************************************\n" + LibrarySystem.displayBorrowedMaterials());
			}
		}
	}

	public JTextArea getTextArea() {
		return materialArea;
	}

	public LibraryFrame() {
		setTitle("Library Materials");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Welcome to Library System");
		lblTitle.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
		lblTitle.setBounds(196, 10, 330, 47);
		contentPane.add(lblTitle);

		JLabel lblSubTitle = DefaultComponentFactory.getInstance().createLabel("All Materials in Library System");
		lblSubTitle.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
		lblSubTitle.setBounds(10, 78, 266, 24);
		contentPane.add(lblSubTitle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 391, 323);
		contentPane.add(scrollPane);

		materialArea = new JTextArea();
		scrollPane.setViewportView(materialArea);
		materialArea.setForeground(new Color(0, 0, 0));

		JButton btnUser = new JButton("Add/Return Materials");
		btnUser.setBackground(new Color(217, 221, 247));

		btnUser.setBounds(424, 275, 223, 39);
		contentPane.add(btnUser);
		btnUser.setFont(new Font("Tahoma", Font.BOLD, 13));

		JButton btnNextDay = new JButton("Enter");
		btnNextDay.setBackground(new Color(217, 221, 247));

		btnNextDay.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
		btnNextDay.setBounds(532, 126, 115, 39);
		contentPane.add(btnNextDay);

		JLabel lblCurrentDay = new JLabel("Current Day : ");
		lblCurrentDay.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCurrentDay.setBounds(448, 92, 137, 24);
		contentPane.add(lblCurrentDay);

		
		dayTF = new JTextField();
		dayTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					showAllMaterial();
				}
			}
		});
		dayTF.setHorizontalAlignment(SwingConstants.CENTER);
		dayTF.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		dayTF.setBounds(424, 128, 97, 36);
		contentPane.add(dayTF);
		dayTF.setColumns(10);
		dayTF.setText(LibrarySystem.systemDate + "");

		dayLabel = new JLabel("10");
		dayLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		dayLabel.setBounds(569, 93, 97, 25);
		contentPane.add(dayLabel);
		
		searchBox = new JComboBox<String>();
		searchBox.setBackground(Color.WHITE);
		searchBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		searchBox.setBounds(424, 324, 123, 36);
		contentPane.add(searchBox);
		
		JButton btnSearch = new JButton("Show");
		btnSearch.setBackground(new Color(217, 221, 247));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearch.setBounds(557, 324, 90, 36);
		contentPane.add(btnSearch);
		
		displayBox = new JComboBox<String>();
		displayBox.setBackground(Color.WHITE);
		displayBox.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "Lost Material", "Borrowed Material"}));
		displayBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		displayBox.setBounds(424, 180, 223, 36);
		contentPane.add(displayBox);
		
		bookRadioButton = new JRadioButton("Book");
		bookRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillSearchBox();
			}
		});
		bookRadioButton.setSelected(true);
		buttonGroup.add(bookRadioButton);
		bookRadioButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		bookRadioButton.setBounds(424, 366, 103, 21);
		contentPane.add(bookRadioButton);
		
		multimediaRadioButton = new JRadioButton("Multimedia");
		multimediaRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillSearchBox();
			}
		});
		buttonGroup.add(multimediaRadioButton);
		multimediaRadioButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		multimediaRadioButton.setBounds(544, 366, 103, 21);
		contentPane.add(multimediaRadioButton);
		fillSearchBox();

		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uf.setVisible(true);
				setVisible(false);
				fillComboBox();

			}
		});

		
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllMaterial();
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempId=(String) searchBox.getSelectedItem();
				materialArea.setText(LibrarySystem.searchMaterial(tempId).toString());
				
			}
		});
		
		displayBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item=(String)displayBox.getSelectedItem();
				if(item.equalsIgnoreCase("All")) {
					String out = "***************************************\n"
							+ "             Available Library Materials              \n"
							+ "***************************************\n";

					for (LibraryMaterial lm : LibrarySystem.libraryMaterial) {
						out += lm.toString() + "-".repeat(50) + "\n";
					}
					materialArea.setText(out);
					
				}else if(item.equalsIgnoreCase("Lost Material")) {
					String out = "***************************************\n"
							+ "              Lost Materials               \n"
							+ "***************************************\n";
					for (LibraryMaterial lm : LibrarySystem.libraryMaterial) {
						if (lm.isLost()) {
							out += lm.toString() + "-".repeat(50) + "\n";
						}
						materialArea.setText(out);
					}
				}else {
					materialArea.setText("***************************************\n"
							+ "              Borrowed Materials               \n"
							+ "***************************************\n" + LibrarySystem.displayBorrowedMaterials());
				}
			}
			
		});
	}
}
