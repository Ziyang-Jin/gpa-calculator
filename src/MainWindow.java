import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainWindow {
    private GPACalculator gpaCalculator = new GPACalculator();
    private JFrame frame;
    // top part
    private static final String WELCOME = "Enter your course, grade, credits below";
    private static final String SCALE_CHANGE = "You have changed the scale. Please recalculate GPA.";
    private JLabel lblDialog;
    private JTextField lblText;
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
        initJFrame();
        initTopPart();
        initLeftPart();
        initRightPart();
        initBottomPart();
    }
    
    private void initTopPart() {
        initDialog();   
        initTextInput();
        initCreateBtn();
        initDeleteBtn();
    }
    
    private void initLeftPart() {       
        initTableAndScrollPane();
    }
    
    private void initRightPart() {
        initHeader();
        initRadioButtons();
    }
    
    private void initBottomPart() {
        initResultDisplay();
        initCalculate();
    }
    
    // START -> JFRAME
    private void initJFrame() {
        final String TITLE = "UBC GPA Calculator";
        frame = new JFrame(TITLE);
        frame.setResizable(false);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }
    // END --> JFRAME
    
    // START --> TOP PART
    private void initDialog() {
        lblDialog = new JLabel(WELCOME);
        lblDialog.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblDialog.setBounds(40, 10, 320, 20);
        frame.getContentPane().add(lblDialog);
    }
    
    private void initTextInput() {
        lblText = new JTextField();
        lblText.setToolTipText("Input your text here");
        lblText.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                final String EXAMPLE = "Example: CPSC121, 80, 4.0";
                lblDialog.setText(EXAMPLE);
            }
            @Override
            public void focusLost(FocusEvent e) {
                lblDialog.setText(WELCOME);
            }
        });
        lblText.setBackground(Color.WHITE);
        lblText.setBounds(36, 40, 200, 26);
        frame.getContentPane().add(lblText);
    }
    
    private void initCreateBtn() {
        JButton btnCreate = new JButton("create");
        btnCreate.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        btnCreate.setToolTipText("Add a table row based on your input.");
        btnCreate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String s = lblText.getText().trim();
                if (!s.isEmpty()) {
                    String[] rowData = {"", "", ""};
                    if (InputParser.parse(s, rowData, ",") || InputParser.parse(s, rowData, " ")) {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.addRow(rowData);
                    } else {
                        final String INVALID_INPUT = "Oops, this input is invalid.";
                        lblDialog.setText(INVALID_INPUT);
                    }
                }
            }
        });
        btnCreate.setBounds(250, 40, 68, 26);
        frame.getContentPane().add(btnCreate);
    }
    
    private void initDeleteBtn() {
        JButton btnDelete = new JButton("delete");
        btnDelete.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        btnDelete.setToolTipText("Delete selected row(s) from table.");
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] selectedRows = table.getSelectedRows();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                for (int i = selectedRows.length-1; i >= 0; i--) {
                    model.removeRow(selectedRows[i]);
                }
            }
        });
        btnDelete.setBounds(310, 40, 68, 26);
        frame.getContentPane().add(btnDelete);
    }
    // END --> TOP PART
    
    // START -> LEFT PART
    private void initTableAndScrollPane() {
        String[] columnNames = {"Course", "Grade", "Credits"};
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setBackground(new Color(255, 255, 255));
        table.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                lblDialog.setText("Edit your records in the table");
            }
        });
        table.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        
        JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setBounds(40, 80, 260, 165);
        frame.getContentPane().add(jsp);
    }
    // END --> LEFT PART
    
    // START --> RIGHT PART
    private void initHeader() {
        JLabel lblGpaScale = new JLabel("GPA  Scale");
        lblGpaScale.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblGpaScale.setBounds(325, 80, 65, 26);
        frame.getContentPane().add(lblGpaScale);
    }
    
    private void initRadioButtons() {
        scaleGroup = new ButtonGroup();
        initRadioBtn100();
        initRadioBtn433();
        initRadioBtn40L();
        initRadioBtn40();
    }
    
    private void initRadioBtn100() {
        JRadioButton radioBtn100 = new JRadioButton("100", true);
        radioBtn100.setToolTipText("GPA calculation based on a scale of 100");
        radioBtn100.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        radioBtn100.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scaleGroup.setSelected(radioBtn100.getModel(), true);
                lblScale.setText("100");
                gpaCalculator.setScale(GPACalculator.DEFAULT_SCALE);
                lblResult.setText(DEFAULT_LBL_RESULT);
                lblDialog.setText(SCALE_CHANGE);
            }
        });
        radioBtn100.setBounds(320, 106, 70, 24);
        frame.getContentPane().add(radioBtn100);
        scaleGroup.add(radioBtn100);
    }
    
    private void initRadioBtn40L() {
        JRadioButton radioBtn40L = new JRadioButton("4.0 (Letter)", false);
        radioBtn40L.setToolTipText("GPA calculation based on your letter grade");
        radioBtn40L.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        radioBtn40L.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scaleGroup.setSelected(radioBtn40L.getModel(), true);
                lblScale.setText("4.0");
                gpaCalculator.setScale(GPACalculator.SCALE40L);
                lblResult.setText(DEFAULT_LBL_RESULT);
                lblDialog.setText(SCALE_CHANGE);
            }
        });
        radioBtn40L.setBounds(320, 154, 100, 24);
        frame.getContentPane().add(radioBtn40L);
        scaleGroup.add(radioBtn40L);
    }
    
    private void initRadioBtn433() {
        JRadioButton radioBtn433 = new JRadioButton("4.33", false);
        radioBtn433.setToolTipText("GPA Calculation based on a scale of 4.33");
        radioBtn433.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        radioBtn433.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scaleGroup.setSelected(radioBtn433.getModel(), true);
                lblScale.setText("4.33");
                gpaCalculator.setScale(GPACalculator.SCALE433);
                lblResult.setText(DEFAULT_LBL_RESULT);
                lblDialog.setText(SCALE_CHANGE);
            }
        });
        radioBtn433.setBounds(320, 130, 70, 24);
        frame.getContentPane().add(radioBtn433);
        scaleGroup.add(radioBtn433);
    }
    
    private void initRadioBtn40() {
        JRadioButton radioBtn40 = new JRadioButton("4.0 (Grade)", false);
        radioBtn40.setToolTipText("GPA calculation based on a scale of 4.0");
        radioBtn40.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        radioBtn40.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scaleGroup.setSelected(radioBtn40.getModel(), true);
                lblScale.setText("4.0");
                gpaCalculator.setScale(GPACalculator.SCALE40);
                lblResult.setText(DEFAULT_LBL_RESULT);
                lblDialog.setText(SCALE_CHANGE);
            }
        });
        radioBtn40.setBounds(320, 178, 100, 24);
        frame.getContentPane().add(radioBtn40);
        scaleGroup.add(radioBtn40);
    }
    // END --> RIGHT PART
    
    // START --> BOTTOM PART
    private void initResultDisplay() {
        // "GPA:", "result", "/", "scale"
        initLblGpa();
        initLblResult();
        initLblDiv();   
        initLblScale();
    }
    
    private void initLblGpa() {
        JLabel lblGpa = new JLabel("GPA:");
        lblGpa.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblGpa.setBounds(50, 250, 60, 20);
        frame.getContentPane().add(lblGpa);
    }
    
    private void initLblResult() {
        final String INIT_RESULT = "0.0";
        lblResult = new JLabel(INIT_RESULT);
        lblResult.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblResult.setToolTipText("Your GPA!");
        lblResult.setBounds(110, 250, 60, 20);
        frame.getContentPane().add(lblResult);
    }
    
    private void initLblDiv() {
        JLabel lblDiv = new JLabel(" /    ");
        lblDiv.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblDiv.setBounds(170, 250, 60, 20);
        frame.getContentPane().add(lblDiv);
    }
    
    private void initLblScale() {
        lblScale = new JLabel("100");
        lblScale.setToolTipText("The chosen GPA scale");
        lblScale.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblScale.setBounds(230, 250, 60, 20);
        frame.getContentPane().add(lblScale);
    }
    
    private void initCalculate() {
        JButton btnCalculate = new JButton("calculate");
        btnCalculate.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        btnCalculate.setToolTipText("Calculate GPA using data from the table.");
        btnCalculate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearCourses();
                createCourses();
                double gpa = gpaCalculator.calculateGPA();
                renderResult(gpa);
            }
        });
        btnCalculate.setBounds(320, 220, 78, 26);
        frame.getContentPane().add(btnCalculate);
    }
    // END --> BOTTOM PART
    
    // HELPER FUNCTIONS
    private void clearCourses() {
        if (gpaCalculator.hasCourses()) {
            gpaCalculator.clearCourses();
        }
    }
    
    private void createCourses() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        @SuppressWarnings("unchecked")
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
    
    private void renderResult(double gpa) {
        if (gpaCalculator.getScale() != GPACalculator.SCALE433) {
            lblResult.setText(String.format("%.1f", gpa));
        } else {
            lblResult.setText(String.format("%.2f", gpa));
        }
    }
}
