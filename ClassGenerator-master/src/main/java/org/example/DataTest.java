package org.example;

import java.util.List;
import java.util.Map;

public class DataTest {
    Map<String, List<ClassInfo>> classMapFall;
    Map<String, List<ClassInfo>>classMapSpring;
    BayesianSorter bayesianSorter;

    DataTest(Map<String, List<ClassInfo>> classMapFall,
             Map<String, List<ClassInfo>>classMapSpring,
             BayesianSorter bayesianSorter) {
        this.classMapFall = classMapFall;
        this.classMapSpring = classMapSpring;
        this.bayesianSorter = bayesianSorter;
    }

    public String testDataOutput(String userInput)
    {
        String sem = userInput.substring(0, 1);
        String course = userInput.substring(1).trim();
        String bestClass = "";

        if (sem.equals("F")) {
            course = capitalizeAndConcatenate(course);
            String course1 = bayesianSorter.getBestClass(classMapFall, course);
            if (course1.equals("null")) {
                bestClass = "No such course exists.";
            } else {
                bestClass = "" + bayesianSorter.getBestClass(classMapFall, course);
            }
        } else if (sem.equals("S")) {
            course = capitalizeAndConcatenate(course);
            String course1 = bayesianSorter.getBestClass(classMapSpring, course);
            if (course1.equals("null")) {
                bestClass = "No such course exists.";
            } else {
                bestClass = "" + bayesianSorter.getBestClass(classMapSpring, course);
            }
        } else {
            bestClass = "I couldn't register the semester.";
        }

        return bestClass;
    }

    // Method to capitalize the whole word and concatenate with the rest of the string
    private static String capitalizeAndConcatenate(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            // If the string is null or empty, return the original string
            return inputString;
        } else {
            // Split the string into words
            String[] words = inputString.split("\\s+"); //
            // \\s+ matches one or more whitespace characters

            // Capitalize each word
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                result.append(words[i].toUpperCase());
                if (i < words.length - 1) {
                    result.append(" "); // Add a space between words
                }
            }

            return result.toString();
        }
    }
}