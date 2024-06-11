import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GUI implements ActionListener {
    //initialize variables
    private JFrame frame;
    private JPanel panel;
    private JLabel Title;
    private JLabel questiontext;
    private JLabel askedQuestion;
    private JTextField questionsInput;
    private int questionNum = 0;
    private JButton Enter;
    private String temp;
    JButton buttonA;
    JButton buttonB;
    JButton buttonC;
    JButton buttonD;

    private ArrayList<String> QuestionsList;
    private ArrayList<String> Topics;
    private ArrayList<String> topicWeaknesses = new ArrayList<>();
    private ArrayList<String> A;
    private ArrayList<String> B;
    private ArrayList<String> C;
    private ArrayList<String> D;
    private ArrayList<String> Answers;


    private int correct = 0;
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

        questiontext.setText("<html>Give me 30 grammar and math multiple choice questions with option choices but add a \"@\" before each question number like \"@1.\"  Make the topic of each question more specific and give topics for each question individually. Only put a \"|\" at the end of choice d. Make the questions in the format \"1)\". Make the correct choice end with a \"*\". into ChatGPT.<html> You might have to try a few times!");
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
        askedQuestion = new JLabel(QuestionsList.get(questionNum));

        askedQuestion.setVisible(true);

        buttonA= new JButton(A.get(questionNum));

        buttonB = new JButton(B.get(questionNum));

        buttonC = new JButton(C.get(questionNum));

        buttonD = new JButton(D.get(questionNum));
        buttonA.addActionListener(this);
        buttonB.addActionListener(this);
        buttonC.addActionListener(this);
        buttonD.addActionListener(this);


        panel.add(buttonA);
        panel.add(buttonB);
        panel.add(buttonC);
        panel.add(buttonD);
        buttonA.setVisible(true);
        buttonB.setVisible(true);
        buttonC.setVisible(true);
        buttonD.setVisible(true);


        panel.add(askedQuestion);
        buttonA.setBounds(500, 230, 700, 60);
        buttonB.setBounds(500, 330, 700, 60);
        buttonC.setBounds(500, 430, 700, 60);
        buttonD.setBounds(500, 530, 700, 60);
        askedQuestion.setBounds(600, 130, 700, 60);
    }



    //Parses information from String
    @Override
    public void actionPerformed(ActionEvent e) {
        //parses for each needed in each Arraylist
        if(e.getSource() == Enter) {

            temp = questionsInput.getText();

            while(temp.contains("@")) {
                int topicEnd = temp.indexOf(":");
                int questionIndex = temp.indexOf(":") + 2;
                int choiceA = temp.indexOf("a)");
                int choiceB = temp.indexOf("b)");
                int choiceC = temp.indexOf("c)");
                int choiceD = temp.indexOf("d)");
                int end = temp.indexOf("|");
                Topics.add(temp.substring(4, topicEnd));

                QuestionsList.add(temp.substring(questionIndex, choiceA));
                if(temp.substring(choiceA, choiceB).contains("*")){
                    A.add(temp.substring(choiceA, choiceB-2));
                    Answers.add(temp.substring(choiceA, choiceB-2));
                }else{
                    A.add(temp.substring(choiceA, choiceB));
                }
                if(temp.substring(choiceB, choiceC).contains("*")){
                    Answers.add(temp.substring(choiceB, choiceC-2));
                    B.add(temp.substring(choiceB, choiceC-2));
                }else{
                    B.add(temp.substring(choiceB, choiceC));
                }
                if(temp.substring(choiceC, choiceD).contains("*")){
                    Answers.add(temp.substring(choiceC, choiceD-2));
                    C.add(temp.substring(choiceC, choiceD-2));

                }else{
                    C.add(temp.substring(choiceC, choiceD));
                }
                if(temp.substring(choiceD, end).contains("*")){
                    Answers.add(temp.substring(choiceD, end-2));
                    D.add(temp.substring(choiceD, end-2));
                }else{
                    D.add(temp.substring(choiceD, end));
                }
                temp = temp.substring(temp.indexOf("|") + 2);
            }
            GameScreen();
        }

        JLabel percentage = new JLabel("You got " + correct + " questions right!.");
        JLabel Weaknesses = new JLabel("<html>You need to work on the following topics: <html>" + topicWeaknesses);
        //checks whether the choice you clicked was right and moves on to next question, if your at last question, it also moves you to end screen
        if(e.getSource() == buttonA){
            if(A.get(questionNum).equals(Answers.get(questionNum))){
                correct++;
                questionNum++;

            }else{
                topicWeaknesses.add(Topics.get(questionNum));
                questionNum++;
            }
            if(questionNum < 30){
                askedQuestion.setText(QuestionsList.get(questionNum));
                buttonA.setText(A.get(questionNum));
                buttonB.setText(B.get(questionNum));
                buttonC.setText(C.get(questionNum));
                buttonD.setText(D.get(questionNum));
            }else{
                buttonA.setVisible(false);
                buttonB.setVisible(false);
                buttonC.setVisible(false);
                buttonD.setVisible(false);
                askedQuestion.setVisible(false);
                percentage.setVisible(true);
                Weaknesses.setVisible(true);
                percentage.setBounds(500,230, 700, 60);
                Weaknesses.setBounds(500, 430, 700, 60);
                panel.add(percentage);
                panel.add(Weaknesses);

            }
        }
        if(e.getSource() == buttonB){
            if(B.get(questionNum).equals(Answers.get(questionNum))){
                correct++;
                questionNum++;

            }else{
                topicWeaknesses.add(Topics.get(questionNum));
                questionNum++;
            }
            if(questionNum < 30){
                askedQuestion.setText(QuestionsList.get(questionNum));
                buttonA.setText(A.get(questionNum));
                buttonB.setText(B.get(questionNum));
                buttonC.setText(C.get(questionNum));
                buttonD.setText(D.get(questionNum));
            }else{
                buttonA.setVisible(false);
                buttonB.setVisible(false);
                buttonC.setVisible(false);
                buttonD.setVisible(false);
                askedQuestion.setVisible(false);
                percentage.setVisible(true);
                Weaknesses.setVisible(true);
                percentage.setBounds(500,230, 700, 60);
                Weaknesses.setBounds(500, 430, 700, 60);
                panel.add(percentage);
                panel.add(Weaknesses);
            }
        }
        if(e.getSource() == buttonC){
            if(C.get(questionNum).equals(Answers.get(questionNum))){
                correct++;
                questionNum++;
            }else{
                topicWeaknesses.add(Topics.get(questionNum));
                questionNum++;
            }
            if(questionNum < 30){
                askedQuestion.setText(QuestionsList.get(questionNum));
                buttonA.setText(A.get(questionNum));
                buttonB.setText(B.get(questionNum));
                buttonC.setText(C.get(questionNum));
                buttonD.setText(D.get(questionNum));
            }else{
                buttonA.setVisible(false);
                buttonB.setVisible(false);
                buttonC.setVisible(false);
                buttonD.setVisible(false);
                askedQuestion.setVisible(false);
                percentage.setVisible(true);
                Weaknesses.setVisible(true);
                percentage.setBounds(500,230, 700, 60);
                Weaknesses.setBounds(500, 430, 700, 60);
                panel.add(percentage);
                panel.add(Weaknesses);
            }

        }
        if(e.getSource() == buttonD){
            if(D.get(questionNum).equals(Answers.get(questionNum))){
                correct++;
                questionNum++;
            }else{
                topicWeaknesses.add(Topics.get(questionNum));
                questionNum++;
            }
            if(questionNum < 30){
                askedQuestion.setText(QuestionsList.get(questionNum));
                buttonA.setText(A.get(questionNum));
                buttonB.setText(B.get(questionNum));
                buttonC.setText(C.get(questionNum));
                buttonD.setText(D.get(questionNum));
            }else{
                buttonA.setVisible(false);
                buttonB.setVisible(false);
                buttonC.setVisible(false);
                buttonD.setVisible(false);
                askedQuestion.setVisible(false);
                percentage.setVisible(true);
                Weaknesses.setVisible(true);
                percentage.setBounds(500,230, 700, 60);
                Weaknesses.setBounds(500, 430, 700, 60);
                panel.add(percentage);
                panel.add(Weaknesses);
            }
        }

    }

}