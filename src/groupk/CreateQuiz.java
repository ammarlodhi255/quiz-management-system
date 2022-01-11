package groupk;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;

public class CreateQuiz extends JFrame {
    private JButton addQuestion = new JButton();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JPanel jPanel1 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JTextField option1 = new JTextField();
    private JTextField option2 = new JTextField();
    private JTextField option3 = new JTextField();
    private JTextField option4 = new JTextField();
    private JLabel provideOpLabel = new JLabel();
    private JLabel provideOpLabel1 = new JLabel();
    private JTextArea questionField = new JTextArea();
    private JButton submitQuestion = new JButton();
    
    private ArrayList<String> questions = new ArrayList<String>();
    private String[][] options = new String[20][4];
    private int[] correctOptions = new int[20];
    private JTextField[] optionFields = {option1, option2, option3, option4};
    private CreateQuiz thisFrame = this;
    private int questionNum = 1;
    
    public CreateQuiz() {
        setComponents();
        questionField.setText(String.valueOf(questionNum + ". "));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        submitQuestion.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
               int time = 0;
               if(jTextField1.getText().length() < 1 || jTextField2.getText().length() < 1 || questionField.getText().length() < 3
                 || option1.getText().length() < 3 || option2.getText().length() < 3 || option3.getText().length() < 3 || option4.getText().length() < 3)
                 JOptionPane.showMessageDialog(null, "Please fill all the required field!", "Empty Fields", JOptionPane.INFORMATION_MESSAGE);
               else {
                    questions.add(questionField.getText());
                    for(int i = 0; i < 4; i++) 
                        (options[questions.size() - 1][i]) = optionFields[i].getText();
                    
                    correctOptions[questions.size() - 1] = Integer.valueOf(jTextField2.getText()) - 1;
                    time = Integer.parseInt(jTextField1.getText()); 
                    Object[] qtns = questions.toArray();
                    Questions q = new Questions(qtns, options, correctOptions, time);
                    JOptionPane.showMessageDialog(null, "Questions have been added succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    thisFrame.dispose();
                    try {
                        Connection con = null;
                        con = ObjectToSQL.getConnection();
                        con.setAutoCommit(false);
                        int id = ObjectToSQL.writeTheObject(con, q);
                        con.commit();
                        File f = new File(System.getProperty("user.dir") + "\\src\\index\\info\\id.dat");
                        FileOutputStream fStream = new FileOutputStream(f);
                        DataOutputStream datStream = new DataOutputStream(fStream);
                        datStream.writeInt(id);
                        datStream.flush();
                        datStream.close();
                        fStream.close();
                    } catch(Exception e) { e.printStackTrace(); }
               }
           }
        });
    }
    
    private void addQuestionActionPerformed(ActionEvent evt) {
        if(jTextField1.getText().length() < 1 || jTextField2.getText().length() == 0 || questionField.getText().length() < 3
           || option1.getText().length() < 3 || option2.getText().length() < 3 || option3.getText().length() < 3 || option4.getText().length() < 3)
            JOptionPane.showMessageDialog(null, "Please fill all the required field!", "Empty Fields", JOptionPane.INFORMATION_MESSAGE);
        else if(jTextField2.getText().length() != 1)
            JOptionPane.showMessageDialog(null, "Please provide a correct option");
        else {
            int time = 0;
            questions.add(questionField.getText());
            int currentIndex = questions.size() - 1;
            for(int i = 0; i < 4; i++) {
                (options[currentIndex][i]) = optionFields[i].getText();
                optionFields[i].setText(i+1 + ".");
            }
            correctOptions[currentIndex] = Integer.valueOf(jTextField2.getText()) - 1;
            time = Integer.parseInt(jTextField1.getText());
            questionField.setText(new String());
            jTextField2.setText(new String());
            questionField.setText(String.valueOf(++questionNum) + ". ");
        }
    }

    private void setComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(18, 127, 126));

        questionField.setColumns(20);
        questionField.setRows(5);
        questionField.setFont(new Font("Aerial", Font.BOLD, 18));
        jScrollPane1.setViewportView(questionField);

        jLabel1.setFont(new Font("Times New Roman", 1, 12));
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setText("WRITE YOUR QUESTION HERE:");

        jLabel2.setFont(new Font("Times New Roman", 1, 14));
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setText("Specify time for the quiz in minutes");

        option1.setText("1. ");
        option2.setText("2. ");
        option3.setText("3. ");
        option4.setText("4. ");

        provideOpLabel.setFont(new Font("Times New Roman", 1, 14)); 
        provideOpLabel.setForeground(new Color(255, 255, 255));
        provideOpLabel.setText("Write which option is correct:");

        addQuestion.setBackground(new Color(153, 204, 255));
        addQuestion.setFont(new Font("Arial", 1, 14)); 
        addQuestion.setText("Add another question");
        addQuestion.setBorder(null);
        addQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addQuestionActionPerformed(evt);
            }
        });

        submitQuestion.setBackground(new Color(153, 204, 255));
        submitQuestion.setFont(new Font("Arial", 1, 14)); 
        submitQuestion.setText("finish & submit");
        submitQuestion.setBorder(null);
        submitQuestion.setFocusable(false);

        provideOpLabel1.setFont(new Font("Times New Roman", 1, 14)); 
        provideOpLabel1.setForeground(new Color(255, 255, 255));
        provideOpLabel1.setText("Provide 4 Options for your question:");

        jTextField2.setText("1");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 518, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(provideOpLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addComponent(option2, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                    .addComponent(option1, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(option3, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                            .addComponent(option4, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addQuestion, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(submitQuestion, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(321, 321, 321)
                    .addComponent(provideOpLabel1, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(322, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(option1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                    .addComponent(option3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(option2, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                    .addComponent(option4, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addQuestion, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitQuestion, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(provideOpLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(279, Short.MAX_VALUE)
                    .addComponent(provideOpLabel1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addGap(177, 177, 177)))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
}
