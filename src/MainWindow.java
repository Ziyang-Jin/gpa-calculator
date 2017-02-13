import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Font;

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
		JLabel lblExample = new JLabel("e.g. ECON101, 100, 3.0");
		lblExample.setBounds(50, 20, 180, 20);
		frame.getContentPane().add(lblExample);
		
		textArea = new JTextArea();
		textArea.setBounds(50, 50, 180, 20);
		frame.getContentPane().add(textArea);
		
		JButton btnCreate = new JButton("create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String s = textArea.getText();
				String[] rowDataRaw = s.split(",");
				String[] rowData = {"", "", ""};
				String courseID = rowDataRaw[0].trim();
				courseID = UBCCourseFactory.validateCourseID(courseID);
				int grade = Integer.parseInt(rowDataRaw[1].trim());
				grade = UBCCourseFactory.validateGrade(grade);
				double credits = Double.parseDouble(rowDataRaw[2].trim());
				credits = UBCCourseFactory.validateCredits(credits);
				rowData[0] = courseID;
				rowData[1] = String.valueOf(grade);
				rowData[2] = String.format("%.1f", credits);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(rowData);
			}
		});
		btnCreate.setBounds(240, 45, 80, 30);
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
		btnDelete.setBounds(320, 45, 80, 30);
		frame.getContentPane().add(btnDelete);
	}
	
	private void initRightPart() {
		initHeader();
		initRadioButtons();
		initCalculate();
	}
	
	private void initHeader() {
		JLabel lblGpaScale = new JLabel("GPA Scale");
		lblGpaScale.setBounds(320, 80, 80, 30);
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
				labelResult.setText("???");
			}
		});
		radioButton_1.setBounds(320, 110, 80, 30);
		frame.getContentPane().add(radioButton_1);
		scaleGroup.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("5.0", false);
		radioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioButton_2.getModel(), true);
				scaleLabel.setText("5.0");
				gpaCalculator.setScale(5.0);
				labelResult.setText("???");
			}
		});
		radioButton_2.setBounds(320, 140, 80, 30);
		frame.getContentPane().add(radioButton_2);
		scaleGroup.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("4.33", false);
		radioButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioButton_3.getModel(), true);
				scaleLabel.setText("4.33");
				gpaCalculator.setScale(4.33);
				labelResult.setText("???");
			}
		});
		radioButton_3.setBounds(320, 170, 80, 30);
		frame.getContentPane().add(radioButton_3);
		scaleGroup.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("4.0", false);
		radioButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioButton_4.getModel(), true);
				scaleLabel.setText("4.0");
				gpaCalculator.setScale(4.0);
				labelResult.setText("???");
			}
		});
		radioButton_4.setBounds(320, 200, 80, 30);
		frame.getContentPane().add(radioButton_4);
		scaleGroup.add(radioButton_4);
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
		btnCalculate.setBounds(320, 235, 80, 30);
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
		int grade = Integer.parseInt(rowData.get(1));
		double credits = Double.parseDouble(rowData.get(2));
		return new Course(courseID, grade, credits);
	}
	
	private void initTable() {
		String[] columnNames = {"Course", "Grade", "Credits"};
		Object[][] data = null;
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setFont(new Font("Monaco", Font.PLAIN, 12));
		table.setBounds(50, 80, 240, 150);
		frame.getContentPane().add(table);
	}
	
	private void initBottomPart() {
		JLabel lblGpa = new JLabel("GPA:");
		lblGpa.setBounds(50, 240, 60, 20);
		frame.getContentPane().add(lblGpa);
		
		labelResult = new JLabel("???");
		labelResult.setBounds(110, 240, 60, 20);
		frame.getContentPane().add(labelResult);
		
		JLabel labelSeparator = new JLabel(" /    ");
		labelSeparator.setBounds(170, 240, 60, 20);
		frame.getContentPane().add(labelSeparator);
		
		scaleLabel = new JLabel("100");
		scaleLabel.setBounds(230, 240, 60, 20);
		frame.getContentPane().add(scaleLabel);
	}
	
}
