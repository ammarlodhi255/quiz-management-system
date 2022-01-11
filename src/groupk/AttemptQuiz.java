package groupk;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class AttemptQuiz extends JFrame implements ItemListener, ActionListener {
    private UserInfo user;
    private JRadioButton buttonSelected;
    private Questions questions;
    private int score = 0;
    private boolean thereAreQuestions = true;
    private UserQuizWind qw;
    private int time;
    private int seconds =59;
    public AttemptQuiz(UserInfo user, UserQuizWind qw) throws Exception {
        this.user = user;
        this.qw = qw;
        if(user.getQuestionIdx() != getID())
            questions = (Questions) ObjectToSQL.readTheObject(ObjectToSQL.getConnection(), getID());
        else throw new Exception();
       
        if(thereAreQuestions) {
            time = questions.getTime();
            initComponents();
            new TimerThread().t.start();
            questionField.setText((String)questions.getQuestions()[0]);
        
            rb1.setText(questions.getOptions()[0][0]);
            rb2.setText(questions.getOptions()[0][1]);
            rb3.setText(questions.getOptions()[0][2]);
            rb4.setText(questions.getOptions()[0][3]);
        
            next.addActionListener(this);
            rb1.addItemListener(this);
            rb2.addItemListener(this);
            rb3.addItemListener(this);
            rb4.addItemListener(this);
            setLocationRelativeTo(null);
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void itemStateChanged(ItemEvent evt) {
        JRadioButton jrb = (JRadioButton) evt.getItem();
        if(jrb.isSelected()) 
            buttonSelected = jrb;
    }
    
    public int getID() {
        int id = -1;
        File f0 = new File(System.getProperty("user.dir") + "\\src\\index\\info\\id.dat");
        if(f0.exists()) {
            try(FileInputStream finStream = new FileInputStream(f0); 
                DataInputStream datInStream = new DataInputStream(finStream)) {
                id = datInStream.readInt();
            } catch(Exception e) {e.printStackTrace();}
        }
        return id;
    }
    
    public int getCurrentQuestionIndex() {
        int idx = -1;
        for(int i = 0; i < questions.getQuestions().length; i++) 
            if(questions.getQuestions()[i].equals(questionField.getText())) {
               idx = i;
               break;
            }
        return idx;
    }
    
    public void actionPerformed(ActionEvent evt) {
        if(buttonSelected.getText().equals(questions.getOptions()[getCurrentQuestionIndex()][questions.getCorrectOptions()[getCurrentQuestionIndex()]]))
                score++;
        
       if(getCurrentQuestionIndex() < questions.getQuestions().length - 1) {
           questionField.setText((String) questions.getQuestions()[getCurrentQuestionIndex() + 1]);
           rb1.setText(questions.getOptions()[getCurrentQuestionIndex()][0]);
           rb2.setText(questions.getOptions()[getCurrentQuestionIndex()][1]);
           rb3.setText(questions.getOptions()[getCurrentQuestionIndex()][2]);
           rb4.setText(questions.getOptions()[getCurrentQuestionIndex()][3]);
       } else {
           closeTheQuiz("You have successfully attempted the quiz.\nYour score for this quiz is: ");
       }
    }
    
    public void closeTheQuiz(String msg) {
        try {
               Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root" , "sqlConnectivity123456");
               Statement stmt = myCon.createStatement();
               int scr = (int)user.getTotalMarks() + score;
               String sql = "update stdInfo " + " set score='" + scr + "'" + " where username='" + user.getUserName() +"'";
               stmt.executeUpdate(sql);
               String sql2 = "update stdInfo " + " set questionIdx='" + getID() + "'" + " where username='" + user.getUserName() +"'";
               stmt.executeUpdate(sql2);
               myCon.close();
               UserQuizWind.setRecentMarks(score);
           } catch(Exception e) {e.printStackTrace();}
           JOptionPane.showMessageDialog(null, msg + score, "Quiz Info", JOptionPane.INFORMATION_MESSAGE);
           qw.setUpUser();
           this.dispose();
    }
    
    private class TimerThread implements Runnable {
        Thread t;

        TimerThread() { t = new Thread(this, "Timer_Thread"); }
        
        public void run() {
            while(time > -1) {
                seconds--;
                
                if(seconds == 0 || seconds < 0) {
                    time--;
                    seconds = 59;
                } 
                if(seconds >= 10)
                    timerLabel.setText("Time left: " + String.valueOf(time) + ":" + String.valueOf(seconds));
                else
                    timerLabel.setText("Time left: " + String.valueOf(time) + ":0" + String.valueOf(seconds));
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException exObj) {}
                if(time < 1) timerLabel.setForeground(new Color(232, 102, 88));
            }

            if(time == -1) {
                timerLabel.setText("0:00");
                closeTheQuiz("You ran out of time!.\nYour score for this quiz is: ");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        questionField = new javax.swing.JLabel();
        timerLabel = new javax.swing.JLabel();
        rb2 = new javax.swing.JRadioButton();
        rb3 = new javax.swing.JRadioButton();
        rb4 = new javax.swing.JRadioButton();
        rb1 = new javax.swing.JRadioButton();
        next = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(950, 591));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(882, 496));

        questionField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        questionField.setForeground(new java.awt.Color(255, 255, 255));
        questionField.setText("1.");
        questionField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        timerLabel.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        timerLabel.setForeground(new java.awt.Color(255, 255, 255));
        timerLabel.setText("TIME LEFT: ");

        rb2.setBackground(new java.awt.Color(0, 102, 102));
        buttonGroup4.add(rb2);
        rb2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rb2.setForeground(new java.awt.Color(255, 255, 255));
        rb2.setText("Op2");
        rb2.setFocusable(false);

        rb3.setBackground(new java.awt.Color(0, 102, 102));
        buttonGroup4.add(rb3);
        rb3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rb3.setForeground(new java.awt.Color(255, 255, 255));
        rb3.setText("Op3");
        rb3.setFocusable(false);

        rb4.setBackground(new java.awt.Color(0, 102, 102));
        buttonGroup4.add(rb4);
        rb4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rb4.setForeground(new java.awt.Color(255, 255, 255));
        rb4.setText("Op4");
        rb4.setFocusable(false);

        rb1.setBackground(new java.awt.Color(0, 102, 102));
        buttonGroup4.add(rb1);
        rb1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rb1.setForeground(new java.awt.Color(255, 255, 255));
        rb1.setText("Op1");
        rb1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        rb1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb1.setFocusable(false);

        next.setBackground(new java.awt.Color(0, 153, 153));
        next.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        next.setForeground(new java.awt.Color(255, 255, 255));
        next.setText("NEXT>");
        next.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        next.setFocusable(false);
        next.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rb2, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rb4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(questionField, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(393, 393, 393))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(timerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(292, 292, 292))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(timerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(questionField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton next;
    private javax.swing.JLabel questionField;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JRadioButton rb4;
    private javax.swing.JLabel timerLabel;
    // End of variables declaration//GEN-END:variables
}
