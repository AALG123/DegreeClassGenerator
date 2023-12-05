package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class UI {
    private final Map<String, List<ClassInfo>> classMapFall;
    private final Map<String, List<ClassInfo>> classMapSpring;
    private final BayesianSorter bayesianSorter;

    public UI(Map<String, List<ClassInfo>> classMapFall,
              Map<String, List<ClassInfo>> classMapSpring,
              BayesianSorter bayesianSorter) {
        this.classMapFall = classMapFall;
        this.classMapSpring = classMapSpring;
        this.bayesianSorter = bayesianSorter;
    }

    public void testRunUI() {
        try {
            // Set the Nimbus look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Degree Class Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(2000, 800);

            // Create a panel for the main content
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding

            // Create a panel for the title and subtitle, centered
            JPanel titlePanel = new JPanel(new BorderLayout());
            titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

            titleLabel(titlePanel);

            // WordToggleButton
            WordToggleButton wordToggleButton = new WordToggleButton();
            titlePanel.add(wordToggleButton, BorderLayout.SOUTH);

            contentPanel.add(titlePanel, BorderLayout.NORTH);

            // Create three columns (a little bigger)
            JPanel mainPanel = new JPanel(new GridLayout(1, 3, 20, 20)); // Add margins between columns
            mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding

            // First Column
            DefaultListModel<String> toDoModel = new DefaultListModel<>();
            JList<String> toDoList = new JList<>(toDoModel);
            JScrollPane toDoScrollPane = new JScrollPane(toDoList);
            mainPanel.add(createColumnPanel("To-Do", toDoScrollPane, 16));

            // Second Column
            DefaultListModel<String> inProgressModel = new DefaultListModel<>();
            JList<String> inProgressList = new JList<>(inProgressModel);
            JScrollPane inProgressScrollPane = new JScrollPane(inProgressList);
            mainPanel.add(createColumnPanel("In Progress", inProgressScrollPane, 16));

            // Third Column
            DefaultListModel<String> doneModel = new DefaultListModel<>();
            JList<String> doneList = new JList<>(doneModel);
            JScrollPane doneScrollPane = new JScrollPane(doneList);
            mainPanel.add(createColumnPanel("Done", doneScrollPane, 16));

            contentPanel.add(mainPanel, BorderLayout.CENTER);

            frame.getContentPane().add(contentPanel);
            centerFrame(frame);
            frame.setVisible(true);
        });
    }

    private static void titleLabel(JPanel titlePanel) {
        JLabel titleLabel = new JLabel("Degree Class Generator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Use a bold Arial font for a modern look
        titleLabel.setForeground(Color.DARK_GRAY); // Use black color for the title
        titlePanel.add(titleLabel, BorderLayout.NORTH);
    }

    private JPanel createColumnPanel(String title, JScrollPane scrollPane, int fontSize) {
    JPanel columnPanel = new JPanel(new BorderLayout());
    columnPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

    // Create a new JPanel for the title with a border
    JPanel titlePanel = new JPanel();
    titlePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    titlePanel.setBackground(Color.LIGHT_GRAY);

    // Create a new button
    JButton button = new JButton("Button");
    button.setFont(new Font("Arial", Font.PLAIN, 14));
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Define the behavior when the button is clicked
            System.out.println(title + " button clicked");

            // Create a new "+" button
            JButton plusButton = new JButton("+");
            plusButton.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add a grey border
            plusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create an instance of PlusButtonAction and call the execute method
                    PlusButtonAction plusButtonAction = new PlusButtonAction(classMapFall, classMapSpring, bayesianSorter);
                    plusButtonAction.execute();
                }
            });

            // Create a new "X" button
            JButton xButton = new JButton("X");
            xButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Remove the "+" button when the "X" button is clicked
                    scrollPane.setColumnHeaderView(null);
                    scrollPane.revalidate();
                    scrollPane.repaint();
                }
            });

            // Set the preferred size of the "+" button to match the "X" button
            plusButton.setPreferredSize(xButton.getPreferredSize());

            // Create a panel to hold the "+" and "X" buttons
            JPanel buttonPanel = new JPanel(new BorderLayout());
            buttonPanel.add(plusButton, BorderLayout.EAST);
            buttonPanel.add(xButton, BorderLayout.WEST);

            // Add the buttonPanel to the scrollPane
            scrollPane.setColumnHeaderView(buttonPanel);
        }
    });

    JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, fontSize));
    titleLabel.setForeground(Color.WHITE);

    titlePanel.add(button, BorderLayout.NORTH);
    titlePanel.add(titleLabel, BorderLayout.CENTER);

    columnPanel.add(titlePanel, BorderLayout.NORTH);
    columnPanel.add(scrollPane, BorderLayout.CENTER);

    return columnPanel;
}

    private static void centerFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) (screenSize.getWidth() - frame.getWidth()) / 2;
        int centerY = (int) (screenSize.getHeight() - frame.getHeight()) / 2;
        frame.setLocation(centerX, centerY);
    }
}