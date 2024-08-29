# DegreeClassGenerator

**Class Generator** is a powerful course planning tool designed specifically for students at Skyline College, Cañada College, and College of San Mateo (CSM). This application helps students create the most optimal class schedules, tailored to their academic needs, based on the courses available at these institutions.

## Features

- **🎓 Intelligent Class Generator:**  
  Automatically generates the best possible class schedule for each student, considering course availability across Skyline, Cañada, and CSM. The generator takes into account various factors to ensure that the selected classes align with the student's academic goals and scheduling constraints.

- **💻 Java Swing GUI:**  
  Features a user-friendly graphical interface built with Java Swing, offering a straightforward and intuitive experience for users. The interface is designed to be easy to navigate, making course planning simple and efficient.

- **🔧 Maven Integration:**  
  The project is managed and built using Maven, ensuring a streamlined development process with easy dependency management and seamless builds.

## How to Run

To run the application:

1. Clone the repository.
2. Navigate to the project directory.
3. Compile and run the `Main.class` file.

```bash
javac Main.java
java Main
```

*Note:* The application is not yet packaged into an executable (.exe) format, so it must be run directly from the Java environment.

## Known Issues

- **⚠️ Term Mixing:**  
  Spring and Fall classes may be displayed in the same column without resetting between terms. This can lead to confusion when planning courses for different semesters.

- **🖥️ GUI Adjustments:**  
  Minor graphical user interface issues exist, such as misalignments or spacing problems, which may slightly affect the user experience.
