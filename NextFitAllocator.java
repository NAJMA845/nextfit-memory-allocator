import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Class to represent a memory block
class MemoryBlock {
    int size; // Size of the memory block
    boolean isAllocated; // Allocation status of the block

    MemoryBlock(int size) {
        this.size = size; // Set block size
        this.isAllocated = false; // Initially, block is free
    }
}

public class NextFitAllocator {
    private static ArrayList<MemoryBlock> memory = new ArrayList<>(); // List to store memry blocks
    private static JTextArea resultArea; // Area to display results
    private static JTextField memoryInput, processesInput; // Input fields for memory and processes
    // Colors for UI design
    private static final Color PRIMARY_COLOR = new Color(63, 81, 181);
    private static final Color ACCENT_COLOR = new Color(92, 107, 192);
    private static final Color SUCCESS_COLOR = new Color(93, 181, 96);
    private static final Color DANGER_COLOR = new Color(225, 80, 70);
    private static final Color BACKGROUND_COLOR = new Color(250, 250, 255);

    public static void main(String[] args) {
        setLookAndFeel(); // Set UI look
        createAndShowGUI(); // Create GUI
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Use system look
        } catch (Exception e) {
            e.printStackTrace(); // Print any errors
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = createMainFrame(); // Create main frame
        JPanel mainContainer = createMainContainer(); // Create main container

        mainContainer.add(createHeader(), BorderLayout.NORTH); // Add header
        mainContainer.add(createContentPanel(), BorderLayout.CENTER); // Add content panel

        frame.add(mainContainer); // Add container to frame
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true); // Show the frame
    }

    private static JFrame createMainFrame() {
        JFrame frame = new JFrame("Memory Allocation Simulator"); // Main frame with title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close app on exit
        frame.setSize(1000, 700); // Set initial size
        frame.setMinimumSize(new Dimension(800, 600)); // Set minimum size
        return frame;
    }

    private static JPanel createMainContainer() {
        JPanel container = new JPanel(new BorderLayout(0, 20)); // Panel with layout
        container.setBackground(BACKGROUND_COLOR); // Set background color
        container.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Set margins
        return container;
    }

    private static JPanel createHeader() {
        // Header panel with gradient background
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Call parent method
                Graphics2D g2d = (Graphics2D) g; // 2D graphics
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); // Smooth rendering
                GradientPaint gradient = new GradientPaint(
                        0, 0, PRIMARY_COLOR,
                        getWidth(), 0, ACCENT_COLOR
                );
                g2d.setPaint(gradient); // Apply gradient
                g2d.fillRect(0, 0, getWidth(), getHeight()); // Fill with gradient
            }
        };
        headerPanel.setPreferredSize(new Dimension(800, 80)); // Set size
        headerPanel.setLayout(new BorderLayout()); // Use BorderLayout

        JLabel titleLabel = new JLabel("Next Fit Memory Allocator", SwingConstants.CENTER); // Title label
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28)); // Set font
        titleLabel.setForeground(Color.WHITE); // Set text color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Set padding
        headerPanel.add(titleLabel, BorderLayout.CENTER); // Add title to center

        return headerPanel; // Return header panel
    }

    private static JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout()); // Panel with GridBagLayout
        contentPanel.setBackground(BACKGROUND_COLOR); // Set background color
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Set margins

        GridBagConstraints gbc = new GridBagConstraints(); // Constraints for layout
        gbc.fill = GridBagConstraints.BOTH; // Fill both directions
        gbc.weightx = 0.4; // Weight for width
        gbc.weighty = 1.0; // Weight for height
        gbc.gridx = 0; // First column
        gbc.gridy = 0; // First row

        contentPanel.add(createInputPanel(), gbc); // Add input panel

        gbc.weightx = 0.6; // Adjust width weight
        gbc.gridx = 1; // Move to second column
        contentPanel.add(createOutputPanel(), gbc); // Add output panel

        return contentPanel; // Return content panel
    }

    private static JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(); // Input panel
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS)); // Use BoxLayout
        inputPanel.setBackground(Color.WHITE); // Set background color
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true), // Border style
                BorderFactory.createEmptyBorder(25, 25, 25, 25) // Set padding
        ));

        // Add sections for memory and process input
        inputPanel.add(createInputSection(
                "Memory Block Sizes",
                "Enter sizes separated by commas (e.g., 100,200,300)",
                memoryInput = createStyledTextField() // Memory input field
        ));
        inputPanel.add(Box.createVerticalStrut(20)); // Add vertical spacing

        inputPanel.add(createInputSection(
                "Process Sizes",
                "Enter sizes separated by commas (e.g., 50,75,100)",
                processesInput = createStyledTextField() // Process input field
        ));
        inputPanel.add(Box.createVerticalStrut(30)); // Add more spacing

        inputPanel.add(createButtonPanel()); // Add buttons for actions

        return inputPanel; // Return input panel
    }

    private static JPanel createInputSection(String title, String tooltip, JTextField field) {
        JPanel section = new JPanel(); // Section panel
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS)); // Vertical layout
        section.setOpaque(false); // Transparent background

        JLabel label = new JLabel(title); // Label for section
        label.setFont(new Font("Segoe UI", Font.BOLD, 15)); // Bold font
        label.setForeground(new Color(33, 33, 33)); // Text color

        JLabel hint = new JLabel(tooltip); // Tooltip for hint
        hint.setFont(new Font("Segoe UI", Font.ITALIC, 12)); // Italic font
        hint.setForeground(new Color(128, 128, 128)); // Hint color

        section.add(label); // Add label to section
        section.add(Box.createVerticalStrut(5)); // Add spacing
        section.add(hint); // Add hint below label
        section.add(Box.createVerticalStrut(10)); // Add spacing
        section.add(field); // Add text field

        return section; // Return section
    }

    private static JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Buttons with flow layout
        buttonPanel.setOpaque(false); // Transparent background

        JButton allocateButton = createStyledButton("Allocate", SUCCESS_COLOR); // Allocate button
        JButton resetButton = createStyledButton("Reset", DANGER_COLOR); // Reset button

        allocateButton.addActionListener(e -> handleAllocation()); // Action for allocation
        resetButton.addActionListener(e -> handleReset()); // Action for reset

        buttonPanel.add(allocateButton); // Add allocate button
        buttonPanel.add(resetButton); // Add reset button

        return buttonPanel; // Return button panel
    }

    private static JPanel createOutputPanel() {
        JPanel outputPanel = new JPanel(new BorderLayout()); // Output panel with BorderLayout
        outputPanel.setBackground(Color.WHITE); // Set background color
        outputPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true), // Border style
                BorderFactory.createEmptyBorder(20, 20, 20, 20) // Set padding
        ));

        JLabel outputTitle = new JLabel("Allocation Results"); // Title for output
        outputTitle.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Bold font
        outputTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Add spacing

        resultArea = new JTextArea(); // Text area for results
        resultArea.setEditable(false); // Make text area read-only
        resultArea.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
        resultArea.setBackground(new Color(252, 252, 255));
        // Set the border and line wrapping for the result area
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

// Create a scroll pane to hold the result area with a border
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

// Add the scroll pane to the output panel
        outputPanel.add(outputTitle, BorderLayout.NORTH);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        return outputPanel;
    }

    // Function to create a styled text field
    private static JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Set font
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35)); // Set maximum size
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true), // Border style
                BorderFactory.createEmptyBorder(8, 12, 8, 12) // Padding inside the field
        ));
        return field;
    }

    // Function to create a styled button with hover effect
    private static JButton createStyledButton(String text, Color baseColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Set font
        button.setForeground(Color.WHITE); // Set text color
        button.setBackground(baseColor); // Set button color
        button.setBorderPainted(false); // Remove border
        button.setFocusPainted(false); // Remove focus highlight
        button.setPreferredSize(new Dimension(120, 40)); // Set button size
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set cursor to hand on hover

        // Change button color on hover
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(baseColor.darker()); // Darken color on hover
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(baseColor); // Reset color on mouse exit
            }
        });

        return button;
    }

    // Function to handle memory allocation
    private static void handleAllocation() {
        resultArea.setText(""); // Clear previous results
        try {
            // Get input values and split by commas
            String[] memorySizes = memoryInput.getText().split(",");
            String[] processSizes = processesInput.getText().split(",");

            memory.clear(); // Clear previous memory blocks

            // Convert memory sizes to integers and create memory blocks
            for (String size : memorySizes) {
                int memorySize = Integer.parseInt(size.trim());
                if (memorySize <= 0) throw new NumberFormatException(); // Error handling
                memory.add(new MemoryBlock(memorySize)); // Add memory block
            }

            int[] processes = new int[processSizes.length];
            // Convert process sizes to integers
            for (int i = 0; i < processSizes.length; i++) {
                int processSize = Integer.parseInt(processSizes[i].trim());
                if (processSize <= 0) throw new NumberFormatException(); // Error handling
                processes[i] = processSize;
            }

            // Perform memory allocation using Next Fit and display the result
            resultArea.append(nextFitAllocation(processes));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter positive integers separated by commas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Function to reset inputs and results
    private static void handleReset() {
        memoryInput.setText(""); // Clear memory input
        processesInput.setText(""); // Clear processes input
        resultArea.setText(""); // Clear result area
        memory.clear(); // Clear memory blocks
    }

    // Next Fit memory allocation algorithm
    public static String nextFitAllocation(int[] processes) {
        StringBuilder output = new StringBuilder();
        int n = memory.size();  // Number of memory blocks
        int pointer = 0;  // Pointer to track the next memory block

        // Display initial memory state
        output.append("Initial Memory State:\n");
        for (int i = 0; i < n; i++) {
            MemoryBlock block = memory.get(i);
            output.append(String.format("• Block %d: %d KB (free)\n", i + 1, block.size));
        }

        output.append("\nProcesses to Allocate:\n");
        for (int i = 0; i < processes.length; i++) {
            output.append(String.format("%d. Process %c requires %d KB.\n", i + 1, (char) ('A' + i), processes[i]));
        }
        output.append("\n Final Allocation Process:\n");

        // Process Allocation Loop
        for (int i = 0; i < processes.length; i++) {
            int processSize = processes[i];  // Get current process size
            boolean allocated = false;  // Flag to track allocation status

            // Loop through memory blocks, starting from the current pointer
            for (int j = 0; j < n; j++) {
                int index = (pointer + j) % n;  // Circular check of memory blocks
                MemoryBlock block = memory.get(index);

                // If block is free and can accommodate the process
                if (!block.isAllocated && block.size >= processSize) {
                    block.isAllocated = true;  // Mark the block as allocated
                    pointer = (index + 1) % n;  // Move pointer to the next block
                    output.append(String.format("• Process %c: Allocated %d KB in Block %d.\n", (char) ('A' + i), processSize, index + 1));
                    allocated = true;  // Set allocated flag
                    break;  // Exit the loop once the process is allocated
                }
            }

            // If process could not be allocated, mention it
            if (!allocated) {
                output.append(String.format("• Process %c: Could not be allocated.\n", (char) ('A' + i)));
            }
        }

        // Display final memory allocation state
        output.append("\nFinal Memory Allocation:\n");
        for (int i = 0; i < n; i++) {
            MemoryBlock block = memory.get(i);
            if (block.isAllocated) {
                output.append(String.format("• Block %d: Allocated\n", i + 1));
            } else {
                output.append(String.format("• Block %d: Free\n", i + 1));
            }
        }

        return output.toString(); // Return the allocation result
    }
}