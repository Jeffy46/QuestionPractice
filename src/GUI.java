import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GUI implements ActionListener {
    private JFrame frame;
    private JLabel Title;
    private JLabel questiontext;
    private JTextField Questions;
    private JButton Enter;
    private String temp;
    private ArrayList<String> QuestionsList;
    private ArrayList<String> Topics;
    private ArrayList<String> A;
    private ArrayList<String> B;
    private ArrayList<String> C;
    private ArrayList<String> D;
    public GUI() {
        frame = new JFrame();
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

        frame.setSize(1980, 1080);
        frame.setLayout(null); // Use null layout for absolute positioning
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questiontext.setText("<html>Give me 30 grammar and math multiple choice questions with option choices but add a \"@\" before each question number like \"@1.\"  Make the topic of each question more specific and give topics for each question individually. Only put a \"|\" at the end of choice d.\n\" into ChatGPT.<html>");
        questiontext.setBounds(590, 130, 700, 60);
        Title.setFont(new Font("Arial", Font.PLAIN, 10));
        Title.setText("Question Practice");
        Title.setBounds(750, 80, 400, 50); // Adjust the size if needed
        Title.setFont(new Font("Arial", Font.PLAIN, 50));
        Enter.addActionListener(this);

        // Set the position for the JTextField
        Questions.setBounds(750, 200, 400, 30); // Adjust the position and size as needed
        Enter.setBounds(875, 250, 100, 30);

        // Add components to the frame
        frame.add(Enter);
        frame.add(Title);
        frame.add(Questions);
        frame.add(questiontext);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Enter) {
            temp = Questions.getText();
            System.out.println(temp);
            while(temp.contains("@")){
                int topicIndex = temp.indexOf("@")+4;
                int topicEnd = temp.indexOf(":");
                int questionIndex = topicEnd+2;
                int AIndex = temp.indexOf("a.");
                int BIndex = temp.indexOf("b.");
                int CIndex = temp.indexOf("c.");
                int DIndex = temp.indexOf("d.");
                Topics.add(temp.substring(topicIndex, topicEnd));
                QuestionsList.add(temp.substring(questionIndex,AIndex));
                ;
                A.add(temp.substring(AIndex+3,BIndex));
                B.add(temp.substring(BIndex+3,CIndex));
                C.add(temp.substring(CIndex+3,DIndex));
                D.add(temp.substring(DIndex+3,temp.indexOf("|")));
                System.out.println(A);
                System.out.println(B);
                System.out.println(C);
                System.out.println(D);
                System.out.println(A.size());
                System.out.println(B.size());
                System.out.println(C.size());
                System.out.println(D.size());
                temp = temp.substring(temp.indexOf("|")+2);
                System.out.println(temp);

            }


        }
    }
}
