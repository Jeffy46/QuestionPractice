import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GUI implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JPanel panel2;
    private JLabel Title;
    private JLabel questiontext;
    private JTextField Questions;
    private JButton Enter;
    private String temp;
    JButton choiceA;
    JButton choiceB;
    JButton choiceC;
    JButton choiceD;
    private ArrayList<String> QuestionsList;
    private ArrayList<String> Topics;
    private ArrayList<String> A;
    private ArrayList<String> B;
    private ArrayList<String> C;
    private ArrayList<String> D;
    public GUI() {
        //Start Screen GUI
        startScreen();
    }
    private void startScreen(){
        frame = new JFrame();
        panel = new JPanel();
        Title = new JLabel();
        questiontext = new JLabel();
        Questions = new JTextField(10);
        Enter = new JButton("Enter");
        temp = "";
        QuestionsList = new ArrayList<String>();
        Topics = new ArrayList<>();
        A = new ArrayList<>();
        B = new ArrayList<>();
        C = new ArrayList<>();
        D = new ArrayList<>();

        panel.setSize(1980, 1080);
        panel.setBackground(Color.CYAN);
        frame.setSize(1980, 1080);
        panel.setLayout(null); // Use null layout for absolute positioning
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questiontext.setText("<html>Give me 30 grammar and math multiple choice questions with option choices but add a \"@\" before each question number like \"@1.\"  Make the topic of each question more specific and give topics for each question individually. Only put a \"|\" at the end of choice d. Make the questions in the format \"1)\".\n\" into ChatGPT.<html>");
        questiontext.setBounds(500, 130, 700, 60);
        Title.setFont(new Font("Arial", Font.PLAIN, 10));
        Title.setText("Question Practice");
        Title.setBounds(660, 80, 400, 50); // Adjust the size if needed
        Title.setFont(new Font("Arial", Font.PLAIN, 50));
        Enter.addActionListener(this);

        // Set the position for the JTextField
        Questions.setBounds(660, 200, 400, 30); // Adjust the position and size as needed
        Enter.setBounds(785, 250, 100, 30);

        // Add components to the frame
        panel.add(Enter);
        panel.add(Title);
        panel.add(Questions);
        panel.add(questiontext);
        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
    }
    private void GameScreen(){
        panel2 = new JPanel();
        panel2.setSize(1920,1080);
        panel.setLayout(null); // Use null layout for absolute positioning
        Enter.setVisible(false);
        questiontext.setVisible(false);
        Questions.setVisible(false);
        panel2.setBackground(Color.CYAN);
        Title = new JLabel();
        Title.setFont(new Font("Arial", Font.PLAIN, 10));
        Title.setText("Question Practice");
        Title.setBounds(660, 80, 400, 50); // Adjust the size if needed
        Title.setFont(new Font("Arial", Font.PLAIN, 50));
        Title.setVisible(true);
        panel2.add(Title);
        panel2.setVisible(true);
        frame.add(panel2);
    }


    //Parses information from String
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Enter) {

            temp = Questions.getText();
            System.out.println(temp);
            while(temp.contains("@")) {
                int topicEnd = temp.indexOf(":");
                int questionIndex = temp.indexOf(":") + 5;
                int choiceA = temp.indexOf("a)");
                int choiceB = temp.indexOf("b)");
                int choiceC = temp.indexOf("c)");
                int choiceD = temp.indexOf("d)");
                int end = temp.indexOf("|");
                Topics.add(temp.substring(4, topicEnd));
                QuestionsList.add(temp.substring(questionIndex, choiceA));
                A.add(temp.substring(choiceA, choiceB));
                B.add(temp.substring(choiceB, choiceC));
                C.add(temp.substring(choiceC, choiceD));
                D.add(temp.substring(choiceD, end));
                if (temp.substring(temp.indexOf("|") + 2) == null) {
                    break;
                }
                temp = temp.substring(temp.indexOf("|") + 2);
            }
            GameScreen();
        }

    }
}
