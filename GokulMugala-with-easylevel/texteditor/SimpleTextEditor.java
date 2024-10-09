import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class SimpleTextEditor extends JFrame {
    private final JTextArea textArea;  // The main text area for editing
    private String currentFilePath; // Tracks the currently opened file path

    public SimpleTextEditor() {
        setTitle("Simple Text Editor"); // Set the title of the window
        setSize(600, 400); // Set the initial size of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Ensure the app closes when the window is closed
        setLocationRelativeTo(null); // Center the window on the screen

        textArea = new JTextArea(); // Create a text area for user input
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Add a scroll pane to the text area for overflow

        JMenuBar menuBar = createMenuBar(); // Time to set up our menu bar
        setJMenuBar(menuBar); // Attach the menu bar to our window
    }

    // Let’s create the menu bar with all the necessary options
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar(); // Create the menu bar

        // Create the File menu and set its background color
        JMenu fileMenu = new JMenu("File");
        fileMenu.setBackground(new Color(100, 149, 237)); // Set a light blue color for the menu background
        fileMenu.setForeground(Color.BLACK); // Set the text color to white

        // Here we create our menu items and add actions
        JMenuItem openItem = createMenuItem("Open", new OpenFileAction());
        JMenuItem saveItem = createMenuItem("Save", new SaveFileAction());
        JMenuItem exitItem = createMenuItem("Exit", e -> System.exit(0)); // Exit action

        // Adding our items to the file menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu); // And don’t forget to add the file menu to the menu bar

        return menuBar; // Return the completed menu bar
    }

    // Helper function to make creating menu items easier with custom colors
    private JMenuItem createMenuItem(String title, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(title); // Create a new menu item
        menuItem.addActionListener(action); // Attach the action listener for functionality
        
        // Customize the look of the menu item
        menuItem.setBackground(new Color(220, 220, 220)); // Light grey background for items
        menuItem.setForeground(Color.BLACK); // Black text
        menuItem.setOpaque(true); // Necessary to show background color
        
        return menuItem; // Return the newly created item
    }

    // Action for opening a file
    private class OpenFileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser(); // Create a file chooser
            int returnValue = fileChooser.showOpenDialog(SimpleTextEditor.this); // Show the dialog
            if (returnValue == JFileChooser.APPROVE_OPTION) { // Check if a file was selected
                File selectedFile = fileChooser.getSelectedFile(); // Get the selected file
                currentFilePath = selectedFile.getAbsolutePath(); // Save the file path
                loadFileContent(selectedFile); // Load the file content into the text area
            }
        }

        // This method handles reading the file and displaying its content
        private void loadFileContent(File file) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder(); // Use StringBuilder for efficient string handling
                String line;
                while ((line = reader.readLine()) != null) { // Read line by line
                    content.append(line).append("\n"); // Append the line to our content
                }
                textArea.setText(content.toString()); // Set the text area content all at once—much smoother!
            } catch (IOException ex) { // If something goes wrong...
                JOptionPane.showMessageDialog(SimpleTextEditor.this, 
                    "Error opening file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Action for saving a file
    private class SaveFileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentFilePath == null) { // Check if there is no current file path
                JFileChooser fileChooser = new JFileChooser(); // Create a file chooser for saving
                int returnValue = fileChooser.showSaveDialog(SimpleTextEditor.this); // Show the save dialog
                if (returnValue == JFileChooser.APPROVE_OPTION) { // Check if a file was selected
                    File selectedFile = fileChooser.getSelectedFile(); // Get the selected file
                    currentFilePath = selectedFile.getAbsolutePath(); // Save the file path
                } else {
                    return; // Exit if no file was selected
                }
            }
            saveFileContent(currentFilePath); // Save the content to the specified file path
        }

        // Method to handle saving text area content to a file
        private void saveFileContent(String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(textArea.getText()); // Write the text area content to the file
            } catch (IOException ex) { // Handle any I/O errors gracefully
                JOptionPane.showMessageDialog(SimpleTextEditor.this, 
                    "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // The main method—this is where everything starts!
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleTextEditor editor = new SimpleTextEditor(); // Create our text editor instance
            editor.setVisible(true); // Make it visible so users can interact with it
        });
    }
}
