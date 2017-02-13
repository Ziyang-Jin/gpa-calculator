import java.awt.EventQueue;
import java.util.ArrayList;

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
	JLabel scaleLabel;
	JTextArea textArea;

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
		
		JButton btnUpdateTable = new JButton("update");
		btnUpdateTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String s = textArea.getText();
				String[] rowData = s.split(",");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(rowData);
			}
		});
		btnUpdateTable.setBounds(222, 11, 87, 29);
		frame.getContentPane().add(btnUpdateTable);
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
			}
		});
		radioButton_3.setBounds(325, 137, 141, 23);
		frame.getContentPane().add(radioButton_3);
		scaleGroup.add(radioButton_3);
	}
	
	private void initCalculate() {
		JButton btnCalculate = new JButton("calculate");
		btnCalculate.setBounds(325, 172, 117, 29);
		frame.getContentPane().add(btnCalculate);
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
		
		JLabel labelResult = new JLabel("3.0");
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
