import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Enum for CourseType
enum CourseType {
    English("English"),
    Math("Math"),
    Science("Science"),
    History("History"),
    Art("Art");


    private final String name;

    CourseType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

// Class for CourseDetail
class CourseDetail {
    private final CourseType courseType;
    private final String courseName;
    private final String courseProfessor;

    public CourseDetail(CourseType courseType, String courseName, String courseProfessor) {
        this.courseType = courseType;
        this.courseName = courseName;
        this.courseProfessor = courseProfessor;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    @Override
    public String toString() {
        return courseType + ": " + courseName + " (" + courseProfessor + ")";
    }
}

// Class for Course
class Course {
    private final CourseType courseType;
    private final String courseName;
    private final String courseCode;
    private final int courseCredits;
    private final ArrayList<CourseDetail> courseDetails;
    private int sectionNumber;

    public Course(int sectionNumber, CourseType courseType, String courseName, String courseCode, int courseCredits) {
        this.sectionNumber = sectionNumber;
        this.courseType = courseType;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredits = courseCredits;
        this.courseDetails = new ArrayList<>();
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public ArrayList<CourseDetail> getCourseDetails() {
        return courseDetails;
    }

    public void addCourseDetail(CourseDetail courseDetail) {
        courseDetails.add(courseDetail);
    }

    public void removeCourseDetail(CourseDetail courseDetail) {
        courseDetails.remove(courseDetail);
    }

    @Override
    public String toString() {
        return "Section " + sectionNumber + ": " + courseType + " - " + courseName + " (" + courseCode + ", " + courseCredits + " credits)";
    }
}

// Class for CourseCellRenderer
class CourseCellRenderer extends JPanel implements ListCellRenderer<Course> {
    private final DegreePlannerUI degreePlannerUI; // Added DegreePlannerUI instance
    private JLabel courseTypeLabel;
    private JComboBox<CourseDetail> courseDetailComboBox;
    private JButton minusButton;

    public CourseCellRenderer(DegreePlannerUI degreePlannerUI) {
        this.degreePlannerUI = degreePlannerUI;
        // Rest of the constructor code
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Course> list, Course course, int index, boolean isSelected, boolean cellHasFocus) {
        // Set the text and font of the course type label
        courseTypeLabel.setText(course.getCourseType().toString());
        courseTypeLabel.setFont(new Font(courseTypeLabel.getFont().getName(), Font.BOLD, 14));
        courseDetailComboBox.setModel(new DefaultComboBoxModel<>(course.getCourseDetails().toArray(new CourseDetail[0])));
        courseDetailComboBox.setRenderer(new CourseDetailRenderer());

        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseDetail selectedCourseDetail = (CourseDetail) courseDetailComboBox.getSelectedItem();

                if (selectedCourseDetail != null) {
                    JDialog detailDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(degreePlannerUI), "Course Detail", true);
                    detailDialog.setSize(400, 300);
                    detailDialog.setLocationRelativeTo(degreePlannerUI);

                    JLabel courseTypeLabel = new JLabel("Course Type:");
                    JComboBox<CourseType> courseTypeComboBox = new JComboBox<>(CourseType.values());
                    courseTypeComboBox.setSelectedItem(selectedCourseDetail.getCourseType());
                    JLabel courseNameLabel = new JLabel("Course Name:");
                    JTextField courseNameTextField = new JTextField(selectedCourseDetail.getCourseName());
                    JLabel courseProfessorLabel = new JLabel("Course Professor:");
                    JTextField courseProfessorTextField = new JTextField(selectedCourseDetail.getCourseProfessor());
                    JButton closeButton = new JButton("Close");

                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            detailDialog.dispose();
                        }
                    });

                    detailDialog.setLayout(new GridLayout(4, 2));
                    detailDialog.add(courseTypeLabel);
                    detailDialog.add(courseTypeComboBox);
                    detailDialog.add(courseNameLabel);
                    detailDialog.add(courseNameTextField);
                    detailDialog.add(courseProfessorLabel);
                    detailDialog.add(courseProfessorTextField);
                    detailDialog.add(new JLabel());
                    detailDialog.add(closeButton);

                    detailDialog.setVisible(true);
                }
            }
        });

        if (isSelected) {
            setBackground(list.getSelectionBackground());
        } else {
            setBackground(list.getBackground());
        }

        return this;
    }
}

// Class for CourseDetailRenderer
class CourseDetailRenderer extends JLabel implements ListCellRenderer<CourseDetail> {
    @Override
    public Component getListCellRendererComponent(JList<? extends CourseDetail> list, CourseDetail courseDetail, int index, boolean isSelected, boolean cellHasFocus) {
        setText(courseDetail.toString());
        setFont(new Font(getFont().getName(), Font.PLAIN, 12));

        if (isSelected) {
            setBackground(list.getSelectionBackground());
        } else {
            setBackground(list.getBackground());
        }

        setOpaque(true);
        return this;
    }
}

// Class for CourseTransferHandler
class CourseTransferHandler extends TransferHandler {
    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        JList<Course> sourceList = (JList<Course>) c;
        Course selectedCourse = sourceList.getSelectedValue();

        if (selectedCourse != null) {
            return new StringSelection(selectedCourse.toString());
        } else {
            return null;
        }
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        JList<Course> sourceList = (JList<Course>) source;
        Course selectedCourse = sourceList.getSelectedValue();

        if (selectedCourse != null && action == MOVE) {
            DefaultListModel<Course> sourceListModel = (DefaultListModel<Course>) sourceList.getModel();
            sourceListModel.removeElement(selectedCourse);

            for (int i = 0; i < sourceListModel.size(); i++) {
                Course course = sourceListModel.getElementAt(i);
                course.setSectionNumber(i + 1);
            }
        }
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(DataFlavor.stringFlavor) && support.getDropLocation() != null;
    }

    @Override
    public boolean importData(TransferSupport support) {
        JList<Course> targetList = (JList<Course>) support.getComponent();
        JList.DropLocation dropLocation = (JList.DropLocation) support.getDropLocation();

        try {
            String transferData = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
            String[] courseInfo = transferData.split(": | - | \\(|, | credits\\)");
            int sectionNumber = Integer.parseInt(courseInfo[0].substring(8));
            CourseType courseType = CourseType.valueOf(courseInfo[1]);
            String courseName = courseInfo[2];
            String courseCode = courseInfo[3];
            int courseCredits = Integer.parseInt(courseInfo[4]);
            Course course = new Course(sectionNumber, courseType, courseName, courseCode, courseCredits);

            DefaultListModel<Course> targetListModel = (DefaultListModel<Course>) targetList.getModel();
            targetListModel.add(dropLocation.getIndex(), course);

            for (int i = 0; i < targetListModel.size(); i++) {
                Course c = targetListModel.getElementAt(i);
                c.setSectionNumber(i + 1);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

public class DegreePlannerApp {
    private static JPanel toDoPanel;
    private static JPanel inProgressPanel;
    private static JPanel donePanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DegreePlannerApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Degree Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 3)); // Three columns

        // Creating panels for each column
        toDoPanel = createColumn("To-Do");
        inProgressPanel = createColumn("In Progress");
        donePanel = createColumn("Done");

        // Adding columns to the frame
        frame.add(toDoPanel);
        frame.add(inProgressPanel);
        frame.add(donePanel);

        // Create a dropdown menu for JSON file selection
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open JSON File");
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));
                int choice = fileChooser.showOpenDialog(null);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    String selectedFile = fileChooser.getSelectedFile().getPath();
                    loadProfessorDataFromJSON(selectedFile);
                }
            }
        });

        fileMenu.add(openMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createColumn(String title) {
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
        columnPanel.setBorder(BorderFactory.createTitledBorder(title));
        return columnPanel;
    }

    private static void enableDragAndDrop(JPanel panel) {
        panel.setTransferHandler(new TransferHandler("text"));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent c = (JComponent) e.getSource();
                TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag(c, e, TransferHandler.COPY);
            }
        });
    }

    private static void loadProfessorDataFromJSON(String filePath) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray professors = (JSONArray) parser.parse(reader);
            for (Object professorObj : professors) {
                JSONObject professorJson = (JSONObject) professorObj;
                String name = (String) professorJson.get("Professor");
                String course = (String) professorJson.get("Rating");
                String courseTypeStr = (String) professorJson.get("Subject"); // Assuming courseType is a field in the JSON

                if (name != null && course != null && courseTypeStr != null) {
                    CourseType courseType = CourseType.valueOf(courseTypeStr);
                    CourseDetail courseDetail = new CourseDetail(courseType, course, name);

                    // Now, you can add the loaded courseDetail to your data structure or perform other necessary actions inshallah
                    // For example, if you have a list to store these courseDetails, you can do:
                    // courseDetailList.add(courseDetail);
                } else {
                    // Handle the case where any of the required fields is null
                    System.out.println("Skipping invalid professor data.");
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void loadDataFromJSON(String filePath) {
        clearColumns();

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            JSONArray tasks = (JSONArray) parser.parse(reader);
            for (Object taskObj : tasks) {
                JSONObject taskJson = (JSONObject) taskObj;
                String taskName = (String) taskJson.get("task");
                JPanel taskPanel = createTaskPanel(taskName);
                if (filePath.contains("todo")) {
                    toDoPanel.add(taskPanel);
                } else if (filePath.contains("inprogress")) {
                    inProgressPanel.add(taskPanel);
                } else if (filePath.contains("done")) {
                    donePanel.add(taskPanel);
                }
            }
            revalidateColumns();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void clearColumns() {
        toDoPanel.removeAll();
        inProgressPanel.removeAll();
        donePanel.removeAll();
    }

    private static void revalidateColumns() {
        toDoPanel.revalidate();
        inProgressPanel.revalidate();
        donePanel.revalidate();
    }

    private static JPanel createTaskPanel(String courseName) {
        JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        taskPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel courseLabel = new JLabel(courseName);
        JButton detailsButton = new JButton("Details");
        JButton deleteButton = new JButton("X");

        taskPanel.add(courseLabel);
        taskPanel.add(detailsButton);
        taskPanel.add(deleteButton);

        // Add action listener for delete button here
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JPanel parentPanel = (JPanel) component.getParent();
                Container grandparent = parentPanel.getParent();
                grandparent.remove(parentPanel);
                grandparent.revalidate();
                grandparent.repaint();
            }
        });

        // Action listener for details button can be added here
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog detailDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(taskPanel), "Task Detail", true);
                detailDialog.setSize(400, 300);
                detailDialog.setLocationRelativeTo(taskPanel);

                JLabel courseNameLabel = new JLabel("Course Name:");
                JTextField courseNameTextField = new JTextField(courseName);
                JLabel courseProfessorLabel = new JLabel("Course Professor:");
                JTextField courseProfessorTextField = new JTextField();
                JButton closeButton = new JButton("Close");

                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        detailDialog.dispose();
                    }
                });

                detailDialog.setLayout(new GridLayout(3, 2));
                detailDialog.add(courseNameLabel);
                detailDialog.add(courseNameTextField);
                detailDialog.add(courseProfessorLabel);
                detailDialog.add(courseProfessorTextField);
                detailDialog.add(new JLabel());
                detailDialog.add(closeButton);

                detailDialog.setVisible(true);
            }
        });

        // Enable drag-and-drop for the task panel
        enableDragAndDrop(taskPanel);

        return taskPanel;
    }
}
