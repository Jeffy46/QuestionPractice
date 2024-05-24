import javax.swing.*;
import java.awt.*;

public class GUI {
    public GUI() {
        JFrame frame = new JFrame();
        JLabel Title = new JLabel();
        JLabel questiontext = new JLabel();
        JTextField Questions = new JTextField(10);

        frame.setSize(1980, 1080);
        frame.setLayout(null); // Use null layout for absolute positioning
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        questiontext.setText("Enter your questions below by pasting \" \" into ChatGPT.");
        questiontext.setBounds(750, 150, 400, 50);
        Title.setFont(new Font("Arial", Font.PLAIN, 10));
        Title.setText("Question Practice");
        Title.setBounds(750, 80, 400, 50); // Adjust the size if needed
        Title.setFont(new Font("Arial", Font.PLAIN, 50));

        // Set the position for the JTextField
        Questions.setBounds(750, 200, 400, 30); // Adjust the position and size as needed

        // Add components to the frame
        frame.add(Title);
        frame.add(Questions);
        frame.add(questiontext);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // To make sure the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
