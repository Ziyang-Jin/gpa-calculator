import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {

	private JFrame frame;
	private JTable table;
	private ButtonGroup scaleGroup;
	private JLabel scaleLabel;
	private JLabel labelResult;
	private JTextArea textArea;
	private GPACalculator gpaCalculator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gpaCalculator = new GPACalculator();
		initJFrame();
		initTopPart();
		initTable();
		initRightPart();
		initBottomPart();
	}
	
	private void initJFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	private void initTopPart() {
		textArea = new JTextArea();
		textArea.setBounds(51, 16, 165, 16);
		frame.getContentPane().add(textArea);
		
		JButton btnCreate = new JButton("create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String s = textArea.getText();
				String[] rowDataRaw = s.split(",");
				String[] rowData = {"", "", ""};
				for (int i = 0; i < table.getColumnCount(); i++) {
					rowData[i] = rowDataRaw[i].trim();
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(rowData);
			}
		});
		btnCreate.setBounds(222, 11, 76, 29);
		frame.getContentPane().add(btnCreate);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int[] selectedRows = table.getSelectedRows();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i = 0; i < selectedRows.length; i++) {
					model.removeRow(selectedRows[i]);
				}
			}
		});
		btnDelete.setBounds(294, 11, 76, 29);
		frame.getContentPane().add(btnDelete);
	}
	
	private void initRightPart() {
		initHeader();
		initRadioButtons();
		initCalculate();
	}
	
	private void initHeader() {
		JLabel lblGpaScale = new JLabel("GPA Scale");
		lblGpaScale.setBounds(333, 44, 61, 16);
		frame.getContentPane().add(lblGpaScale);
	}
	
	private void initRadioButtons() {
		scaleGroup = new ButtonGroup();
		JRadioButton radioButton_1 = new JRadioButton("100", true);
		radioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioButton_1.getModel(), true);
				scaleLabel.setText("100");
				gpaCalculator.setScale(100.0);
			}
		});
		radioButton_1.setBounds(325, 67, 141, 23);
		frame.getContentPane().add(radioButton_1);
		scaleGroup.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("5.0", false);
		radioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioButton_2.getModel(), true);
				scaleLabel.setText("5.0");
				gpaCalculator.setScale(5.0);
			}
		});
		radioButton_2.setBounds(325, 102, 141, 23);
		frame.getContentPane().add(radioButton_2);
		scaleGroup.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("4.0", false);
		radioButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioButton_3.getModel(), true);
				scaleLabel.setText("4.0");
				gpaCalculator.setScale(4.0);
			}
		});
		radioButton_3.setBounds(325, 137, 141, 23);
		frame.getContentPane().add(radioButton_3);
		scaleGroup.add(radioButton_3);
	}
	
	private void initCalculate() {
		JButton btnCalculate = new JButton("calculate");
		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createCourses();
				double gpa = gpaCalculator.calculateGPA();
				labelResult.setText(String.format("%.1f", gpa));
			}
		});
		btnCalculate.setBounds(325, 172, 117, 29);
		frame.getContentPane().add(btnCalculate);
	}
	
	private void createCourses() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		Vector<Vector<String>> data = model.getDataVector();
		for (int i = 0; i < rowCount; i++) {
			Vector<String> rowData = data.get(i);
			Course c = createCourse(rowData);
			gpaCalculator.registerCourse(c);
		}
	}
	
	private Course createCourse(Vector<String> rowData) {
		String courseID = rowData.get(0);
		int grade = Integer.parseInt(rowData.get(1).trim());
		double credits = Double.parseDouble(rowData.get(2).trim());
		return new Course(courseID, grade, credits);
	}
	
	private void initTable() {
		String[] columnNames = {"Course", "Grade", "Credits"};
		Object[][] data = {
				{"SMPL 101", "85", "0.0"}	
		};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setBounds(51, 44, 258, 153);
		frame.getContentPane().add(table);
	}
	
	private void initBottomPart() {
		JLabel lblGpa = new JLabel("GPA:");
		lblGpa.setBounds(51, 227, 50, 16);
		frame.getContentPane().add(lblGpa);
		
		labelResult = new JLabel("3.0");
		labelResult.setBounds(113, 227, 47, 16);
		frame.getContentPane().add(labelResult);
		
		JLabel labelSeparator = new JLabel("    /    ");
		labelSeparator.setBounds(169, 227, 47, 16);
		frame.getContentPane().add(labelSeparator);
		
		scaleLabel = new JLabel("100");
		scaleLabel.setBounds(248, 227, 61, 16);
		frame.getContentPane().add(scaleLabel);
	}
	
}
