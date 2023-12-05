package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PlusButtonAction {
    private final Map<String, List<ClassInfo>> classMapFall;
    private final Map<String, List<ClassInfo>> classMapSpring;
    private final BayesianSorter bayesianSorter;

    public PlusButtonAction(Map<String, List<ClassInfo>> classMapFall,
                            Map<String, List<ClassInfo>> classMapSpring,
                            BayesianSorter bayesianSorter) {
        this.classMapFall = classMapFall;
        this.classMapSpring = classMapSpring;
        this.bayesianSorter = bayesianSorter;
    }

    public void execute() {
        try {
            // Set the Nimbus look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a new JFrame
        JFrame frame = new JFrame();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame

        // Create a JPanel to hold the JLabel and JTextField
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding
        panel.setBackground(new Color(169, 169, 169)); // Set the background color to darkish grey

        // Create a JLabel asking "What is the class that you want?"
        JLabel label = new JLabel("What is the class that you want?", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20)); // Use a plain Arial font for a modern look
        panel.add(label);

        // Create a JTextField for the user to enter the class name
        JTextField textField = new JTextField();
        panel.add(textField);

        // Create a "Submit" button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            // Create an instance of DataTest and call the testDataOutput method
            DataTest dataTest = new DataTest(classMapFall, classMapSpring, bayesianSorter);
            String bestClass = dataTest.testDataOutput(textField.getText());

            // Display the best class with the best rating in a JOptionPane dialog box
            JOptionPane.showMessageDialog(frame, bestClass);
        });
        panel.add(submitButton);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}