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
    private JLabel Title;
    private JLabel questiontext;
    private JTextField questionsInput;
    private int questionNum = 0;
    private JButton Enter;
    private String temp;
    JButton choiceA;
    JButton choiceB;
    JButton choiceC;
    JButton choiceD;
    JLabel aText;
    JLabel bText;
    JLabel cText;
    JLabel dText;

    private ArrayList<String> QuestionsList;
    private ArrayList<String> Topics;
    private ArrayList<String> A;
    private ArrayList<String> B;
    private ArrayList<String> C;
    private ArrayList<String> D;
    private ArrayList<String> Answers;
    public GUI() {
        //Start Screen GUI
        startScreen();
    }
    private void startScreen(){
        frame = new JFrame();
        panel = new JPanel();
        Title = new JLabel();
        questiontext = new JLabel();
        questionsInput = new JTextField(10);
        Enter = new JButton("Enter");
        temp = "";
        QuestionsList = new ArrayList<String>();
        Topics = new ArrayList<>();
        A = new ArrayList<>();
        B = new ArrayList<>();
        C = new ArrayList<>();
        D = new ArrayList<>();
        Answers = new ArrayList<>();
        panel.setSize(1600, 1080);
        panel.setBackground(Color.CYAN);
        frame.setSize(1600, 1080);
        panel.setLayout(null); // Use null layout for absolute positioning
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questiontext.setText("<html>Give me 30 grammar and math multiple choice questions with option choices but add a \"@\" before each question number like \"@1.\"  Make the topic of each question more specific and give topics for each question individually. Only put a \"|\" at the end of choice d. Make the questions in the format \"1)\". Make the correct choice end with a \"*\". into ChatGPT.<html>");
        questiontext.setBounds(500, 130, 700, 60);
        Title.setFont(new Font("Arial", Font.PLAIN, 10));
        Title.setText("Question Practice");
        Title.setBounds(660, 80, 400, 50); // Adjust the size if needed
        Title.setFont(new Font("Arial", Font.PLAIN, 50));
        Enter.addActionListener(this);

        // Set the position for the JTextField
        questionsInput.setBounds(660, 200, 400, 30); // Adjust the position and size as needed
        Enter.setBounds(785, 250, 100, 30);

        // Add components to the frame
        panel.add(Enter);
        panel.add(Title);
        panel.add(questionsInput);
        panel.add(questiontext);
        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
    }
    private void GameScreen(){
        Enter.setVisible(false);
        questiontext.setVisible(false);
        questionsInput.setVisible(false);
        JLabel askedQuestion = new JLabel(QuestionsList.get(questionNum));

        askedQuestion.setVisible(true);

        choiceA= new JButton(A.get(questionNum));

        choiceB = new JButton(B.get(questionNum));

        choiceC = new JButton(C.get(questionNum));

        choiceD = new JButton(D.get(questionNum));

        panel.add(choiceA);
        panel.add(choiceB);
        panel.add(choiceC);
        panel.add(choiceD);
        choiceA.setVisible(true);
        choiceB.setVisible(true);
        choiceC.setVisible(true);
        choiceD.setVisible(true);


        panel.add(askedQuestion);
        choiceA.setBounds(500, 230, 700, 60);
        choiceB.setBounds(500, 330, 700, 60);
        choiceC.setBounds(500, 430, 700, 60);
        choiceD.setBounds(500, 530, 700, 60);
        askedQuestion.setBounds(600, 130, 700, 60);



    }



    //Parses information from String
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Enter) {

            temp = questionsInput.getText();
            System.out.println(temp);
            while(temp.contains("@")) {
                int topicEnd = temp.indexOf(":");
                int questionIndex = temp.indexOf(":") + 2;
                int choiceA = temp.indexOf("a)");
                int choiceB = temp.indexOf("b)");
                int choiceC = temp.indexOf("c)");
                int choiceD = temp.indexOf("d)");
                int end = temp.indexOf("|");
                Topics.add(temp.substring(4, topicEnd));
                System.out.println(Topics);
                System.out.println(Topics.size());
                QuestionsList.add(temp.substring(questionIndex, choiceA));
                if(temp.substring(choiceA, choiceB).contains("*")){
                    A.add(temp.substring(choiceA, choiceB-1));
                    Answers.add(temp.substring(choiceA, choiceB-1));
                }else{
                    A.add(temp.substring(choiceA, choiceB));
                }
                if(temp.substring(choiceB, choiceC).contains("*")){
                    Answers.add(temp.substring(choiceB, choiceC-1));
                    B.add(temp.substring(choiceB, choiceC-1));
                }else{
                    B.add(temp.substring(choiceB, choiceC));
                }
                if(temp.substring(choiceC, choiceD).contains("*")){
                    Answers.add(temp.substring(choiceC, choiceD-1));
                    C.add(temp.substring(choiceC, choiceD-1));

                }else{
                    C.add(temp.substring(choiceC, choiceD));
                }
                if(temp.substring(choiceD, end).contains("*")){
                    Answers.add(temp.substring(choiceD, end-1));
                    D.add(temp.substring(choiceD, end-1));
                }else{
                    D.add(temp.substring(choiceD, end));
                }
                temp = temp.substring(temp.indexOf("|") + 2);
            }

            GameScreen();
        }
        if(e.getSource() == choiceA){

        }
        if(e.getSource() == choiceB){

        }
        if(e.getSource() == choiceC){

        }
        if(e.getSource() == choiceD){

        }
    }
}