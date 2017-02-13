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
	private GPACalculator gpaCalculator;
	private JFrame frame;
	// top part
	private JLabel lblDialog;
	private JTextArea lblText;
	// left part
	private JTable table;
	// right part
	private ButtonGroup scaleGroup;
	// bottom part
	private JLabel lblScale;
	private JLabel lblResult;
	private static final String DEFAULT_LBL_RESULT = "0.0";

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
		initLeftPart();
		initRightPart();
		initBottomPart();
	}
	
	private void initJFrame() {
		final String TITLE = "UBC GPA Calculator";
		frame = new JFrame(TITLE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	private void initTopPart() {
		initDialog();	
		initTextInput();
		initCreateBtn();
		initDeleteBtn();
	}
	
	private void initLeftPart() {
		String[] columnNames = {"Course", "Grade", "Credits"};
		Object[][] data = null;
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setFont(new Font("Monaco", Font.PLAIN, 12));
		table.setBounds(50, 80, 240, 150);
		frame.getContentPane().add(table);
	}
	
	private void initRightPart() {
		initHeader();
		initRadioButtons();
		initCalculate();
	}
	
	private void initBottomPart() {
		JLabel lblGpa = new JLabel("GPA:");
		lblGpa.setBounds(50, 240, 60, 20);
		frame.getContentPane().add(lblGpa);
		
		final String INIT_RESULT = "0.0";
		lblResult = new JLabel(INIT_RESULT);
		lblResult.setBounds(110, 240, 60, 20);
		frame.getContentPane().add(lblResult);
		
		JLabel lblSeparator = new JLabel(" /    ");
		lblSeparator.setBounds(170, 240, 60, 20);
		frame.getContentPane().add(lblSeparator);
		
		lblScale = new JLabel("100");
		lblScale.setBounds(230, 240, 60, 20);
		frame.getContentPane().add(lblScale);
	}
	
	// START --> TOP PART
	private void initDialog() {
		final String WELCOME = "Please create course records:";
		lblDialog = new JLabel(WELCOME);
		lblDialog.setBounds(50, 20, 185, 20);
		frame.getContentPane().add(lblDialog);
	}
	
	private void initTextInput() {
		lblText = new JTextArea();
		lblText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final String EXAMPLE = "Example: CPSC121, 80, 4.0";
				lblDialog.setText(EXAMPLE);
			}
		});
		lblText.setBounds(50, 50, 185, 20);
		frame.getContentPane().add(lblText);
	}
	
	private void initCreateBtn() {
		JButton btnCreate = new JButton("create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String s = lblText.getText();
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
	}
	
	private void initDeleteBtn() {
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
	// END --> TOP PART
	
	// START --> RIGHT PART
	private void initHeader() {
		JLabel lblGpaScale = new JLabel("GPA Scale");
		lblGpaScale.setBounds(320, 80, 80, 30);
		frame.getContentPane().add(lblGpaScale);
	}
	
	private void initRadioButtons() {
		scaleGroup = new ButtonGroup();
		initRadioBtn100();
		initRadioBtn50();
		initRadioBtn433();
		initRadioBtn40();
	}
	
	private void initRadioBtn100() {
		JRadioButton radioBtn100 = new JRadioButton("100", true);
		radioBtn100.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioBtn100.getModel(), true);
				lblScale.setText("100");
				gpaCalculator.setScale(100.0);
				lblResult.setText(DEFAULT_LBL_RESULT);
			}
		});
		radioBtn100.setBounds(320, 110, 80, 30);
		frame.getContentPane().add(radioBtn100);
		scaleGroup.add(radioBtn100);
	}
	
	private void initRadioBtn50() {
		JRadioButton radioBtn50 = new JRadioButton("5.0", false);
		radioBtn50.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioBtn50.getModel(), true);
				lblScale.setText("5.0");
				gpaCalculator.setScale(5.0);
				lblResult.setText(DEFAULT_LBL_RESULT);
			}
		});
		radioBtn50.setBounds(320, 140, 80, 30);
		frame.getContentPane().add(radioBtn50);
		scaleGroup.add(radioBtn50);
	}
	
	private void initRadioBtn433() {
		JRadioButton radioBtn433 = new JRadioButton("4.33", false);
		radioBtn433.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioBtn433.getModel(), true);
				lblScale.setText("4.33");
				gpaCalculator.setScale(4.33);
				lblResult.setText(DEFAULT_LBL_RESULT);
			}
		});
		radioBtn433.setBounds(320, 170, 80, 30);
		frame.getContentPane().add(radioBtn433);
		scaleGroup.add(radioBtn433);
	}
	
	private void initRadioBtn40() {
		JRadioButton radioBtn40 = new JRadioButton("4.0", false);
		radioBtn40.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scaleGroup.setSelected(radioBtn40.getModel(), true);
				lblScale.setText("4.0");
				gpaCalculator.setScale(4.0);
				lblResult.setText(DEFAULT_LBL_RESULT);
			}
		});
		radioBtn40.setBounds(320, 200, 80, 30);
		frame.getContentPane().add(radioBtn40);
		scaleGroup.add(radioBtn40);
	}
	
	private void initCalculate() {
		JButton btnCalculate = new JButton("calculate");
		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createCourses();
				double gpa = gpaCalculator.calculateGPA();
				lblResult.setText(String.format("%.1f", gpa));
			}
		});
		btnCalculate.setBounds(320, 235, 80, 30);
		frame.getContentPane().add(btnCalculate);
	}
	// END --> RIGHT PART
	
	// HELPER FUNCTIONS
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
}
