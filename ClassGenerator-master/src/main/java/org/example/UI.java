package org.example;

import javax.swing.*;
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
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Degree Class Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(2000, 800);

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

            JPanel titlePanel = new JPanel(new BorderLayout());
            titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

            titleLabel(titlePanel);

            WordToggleButton wordToggleButton = new WordToggleButton();
            titlePanel.add(wordToggleButton, BorderLayout.SOUTH);

            contentPanel.add(titlePanel, BorderLayout.NORTH);

            JPanel mainPanel = new JPanel(new GridLayout(1, 3, 20, 20));
            mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

            DefaultListModel<String> toDoModel = new DefaultListModel<>();
            JList<String> toDoList = new JList<>(toDoModel);
            JScrollPane toDoScrollPane = new JScrollPane(toDoList);
            mainPanel.add(createColumnPanel("To-Do", toDoScrollPane, 16, wordToggleButton));

            DefaultListModel<String> inProgressModel = new DefaultListModel<>();
            JList<String> inProgressList = new JList<>(inProgressModel);
            JScrollPane inProgressScrollPane = new JScrollPane(inProgressList);
            mainPanel.add(createColumnPanel("In Progress", inProgressScrollPane, 16, wordToggleButton));

            DefaultListModel<String> doneModel = new DefaultListModel<>();
            JList<String> doneList = new JList<>(doneModel);
            JScrollPane doneScrollPane = new JScrollPane(doneList);
            mainPanel.add(createColumnPanel("Done", doneScrollPane, 16, wordToggleButton));

            contentPanel.add(mainPanel, BorderLayout.CENTER);

            frame.getContentPane().add(contentPanel);
            centerFrame(frame);
            frame.setVisible(true);
        });
    }

    private static void titleLabel(JPanel titlePanel) {
        JLabel titleLabel = new JLabel("Degree Class Generator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.DARK_GRAY);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
    }

    private JPanel createColumnPanel(String title, JScrollPane scrollPane, int fontSize, WordToggleButton wordToggleButton) {
        JPanel columnPanel = new JPanel(new BorderLayout());
        columnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        titlePanel.setBackground(Color.LIGHT_GRAY);

        JButton button = new JButton("Button");
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(title + " button clicked");

                // Create a JPanel to hold the sections of "+" and "-" buttons
                JPanel headerPanel;
                if (scrollPane.getColumnHeader() == null) {
                    headerPanel = new JPanel();
                    headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.PAGE_AXIS));
                    scrollPane.setColumnHeaderView(headerPanel);
                } else {
                    headerPanel = (JPanel) scrollPane.getColumnHeader().getView();
                }

                JLabel bestClassLabel = new JLabel();
                bestClassLabel.setFont(new Font("Arial", Font.ITALIC, 10));
                bestClassLabel.setForeground(Color.DARK_GRAY);
                bestClassLabel.setHorizontalAlignment(JLabel.CENTER);
                bestClassLabel.setVerticalAlignment(JLabel.CENTER);

                JButton plusButton = new JButton("+");
                plusButton.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add a grey border
                plusButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Create an instance of PlusButtonAction and call the execute method
                        PlusButtonAction plusButtonAction = new PlusButtonAction(classMapFall, classMapSpring, bayesianSorter, wordToggleButton, bestClassLabel);
                        plusButtonAction.execute();
                    }
                });

                JButton xButton = new JButton("X");
                xButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scrollPane.setColumnHeaderView(null);
                        scrollPane.revalidate();
                        scrollPane.repaint();
                    }
                });
                plusButton.setPreferredSize(xButton.getPreferredSize());
                JPanel buttonPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                // Add the "X" button to the west
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 0.2; // Increase the weightx of the "X" button
                buttonPanel.add(xButton, gbc);

                // Add the bestClassLabel to the center
                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.weightx = 0.6; // Decrease the weightx of the bestClassLabel
                buttonPanel.add(bestClassLabel, gbc);

                // Add the "+" button to the east
                gbc.gridx = 2;
                gbc.gridy = 0;
                gbc.weightx = 0.2; // Increase the weightx of the "+" button
                buttonPanel.add(plusButton, gbc);

                // Add the buttonPanel to the headerPanel
                headerPanel.add(buttonPanel);

                // Refresh the scrollPane
                scrollPane.revalidate();
                scrollPane.repaint();
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