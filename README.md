# Memory Allocation Simulation (Next Fit Algorithm)

This project simulates the memory allocation process using the **Next Fit** algorithm. The program allocates processes to memory blocks in a way that follows the "Next Fit" approach, where the allocation starts from the last allocated memory block.

## Features

- **Memory Allocation**: Allows the user to input memory sizes and process sizes. The system uses the **Next Fit** algorithm to allocate processes to memory blocks.
- **Styled UI**: The application features a user-friendly interface with styled input fields, buttons, and result display areas.
- **Memory Block Visualization**: Displays the initial state of memory and how each process is allocated or if it could not be allocated.
- **Error Handling**: Ensures that invalid inputs are handled appropriately, notifying users about incorrect formats.

## Technologies Used

### Framework and Tech Stack Implementation

This project was developed using the following frameworks, tools, and technologies:

- **Programming Languages**:
  - **Java**: The core programming language used for the logic and implementation of the memory allocation algorithm and user interface.
  
- **Frameworks**:
  - **Java Swing**: Used to build the graphical user interface (GUI). It provides a set of lightweight (all-Java language) components for building user interfaces.
  
- **Tools**:
  - **IDE**: The project was developed using **visual Studio Code** as the code editor for writing, compiling, and testing the Java code.
  - **JDK (Java Development Kit)**: JDK 8 or above is required to run and compile the Java code.
  
- **Version Control**:
  - **Git**: Git was used for version control to manage code changes and track the development progress. The project is hosted on **GitHub** for version control and sharing the repository.

- **GUI Design**:
  - **Swing Components**: For creating interactive components such as buttons, text fields, and scrollable areas.
  - **Java AWT (Abstract Window Toolkit)**: Used for the basic window creation and event handling.

- **Error Handling**:
  - **JOptionPane**: Used for displaying error dialogs and messages to the user when invalid inputs are provided.

### Development Process

1. **Planning**: The project was planned in phases, starting with defining the requirements for the memory allocation algorithm and designing the user interface.
   
2. **Design**: The user interface was designed with a focus on simplicity and clarity. Components like buttons, text fields, and labels were chosen for ease of use and aesthetic appeal.

3. **Implementation**:
   - **Core Logic**: The **Next Fit** memory allocation algorithm was implemented to allocate processes to available memory blocks. The core logic was written in Java.
   - **UI Development**: Swing components were used to create the graphical interface, which includes inputs for memory and processes, a result display area, and buttons to trigger actions like allocation and reset.
   
4. **Testing**:
   - The application was tested by providing different sets of inputs (valid and invalid) to ensure proper memory allocation and error handling.

5. **Debugging**: Debugging was carried out using the built-in Visual Studio Code debugging tools to fix any issues and ensure smooth performance.

6. **Documentation**: After the project was completed, the code was documented with comments explaining key parts of the logic, and a comprehensive README file was created to guide users through the setup and usage.


### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/memory-allocation-simulation.git
