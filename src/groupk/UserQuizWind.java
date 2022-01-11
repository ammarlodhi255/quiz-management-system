package groupk;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UserQuizWind extends JFrame {

    private UserInfo user;
    public static int rMarks = 0;
    
    public UserQuizWind(UserInfo user) {
        initComponents();
        this.user = user;
        addWindowListener(new setMyFrame());
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public class setMyFrame extends WindowAdapter {
        public void windowOpened(WindowEvent we) {
            NameOfUser.setText("WELCOME: " + user.getName());
            cmsId.setText("CMS_ID: " + user.getCMS_ID());
            dep.setText("Department: " + user.getDepartment());
            emailLabel.setText("Email: " + user.getEmail());
            if(user.getUserPic() != null)
                jLabel1.setIcon(user.getUserPic());
            new PrintingThread(welcomeField, "QUIZ MANAGEMENT SYSTEM").t1.start();
        } 
    }
    
    public void setUp() {
        NameOfUser.setText("WELCOME: " + user.getName());
        cmsId.setText("CMS_ID: " + user.getCMS_ID());
        dep.setText("Department: " + user.getDepartment());
        emailLabel.setText("Email: " + user.getEmail());
        if(user.getUserPic() != null)
            jLabel1.setIcon(user.getUserPic());
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        NameOfUser = new javax.swing.JLabel();
        dep = new javax.swing.JLabel();
        cmsId = new javax.swing.JLabel();
        welcomeField = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        logOut = new javax.swing.JButton();
        emailLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(950, 591));

        jPanel1.setBackground(new java.awt.Color(18, 127, 126));

        NameOfUser.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        NameOfUser.setForeground(new java.awt.Color(255, 255, 255));
        NameOfUser.setText("preview of name");

        dep.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        dep.setForeground(new java.awt.Color(255, 255, 255));
        dep.setText("preview of dep");

        cmsId.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        cmsId.setForeground(new java.awt.Color(255, 255, 255));
        cmsId.setText("preview of cms");

        welcomeField.setEditable(false);
        welcomeField.setBackground(new java.awt.Color(18, 127, 126));
        welcomeField.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        welcomeField.setForeground(new java.awt.Color(255, 255, 255));
        welcomeField.setText("welcome logo");
        welcomeField.setBorder(null);

        update.setBackground(new java.awt.Color(0, 51, 51));
        update.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Update your profile");
        update.setFocusable(false);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 51, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("View your scores");
        jButton2.setActionCommand("Take a quiz");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 51, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Take a quiz");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        logOut.setBackground(new java.awt.Color(0, 51, 51));
        logOut.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        logOut.setForeground(new java.awt.Color(255, 255, 255));
        logOut.setText("Log Out");
        logOut.setActionCommand("Take a quiz");
        logOut.setFocusable(false);
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });

        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("emailLabel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(NameOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(welcomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmsId, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 280, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmsId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dep)
                        .addGap(49, 49, 49)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NameOfUser)
                            .addComponent(welcomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root" , "sqlConnectivity123456");
            Statement stmt = myCon.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from stdInfo");
            
            while(rs.next()) {
                if(rs.getString("username").equals(user.getUserName())) {
                    user.setTotalMarks(rs.getInt("score"));
                    user.setRecentMarks(rMarks);
                    break;
                }
            }
            
        } catch(Exception e) {e.printStackTrace();}
            
        JOptionPane.showMessageDialog(null, "Your total score: " + String.valueOf(user.getTotalMarks())
         + "\nRecent marks: " + user.getRecentMarks(), "Score", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void setRecentMarks(int r) {
        rMarks = r;
    }
    
    public void setUpUser() {
        try {
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", 
                "root" , "sqlConnectivity123456");
            Statement stmt = myCon.createStatement();
            ResultSet rs = stmt.executeQuery("select * from stdInfo");
            while(rs.next()) {
                if(rs.getString("username").equals(user.getUserName())) {
                    user.setQuestionIdx(rs.getInt("questionIdx"));
                    break;
                }
            }
        } catch(Exception e) { e.printStackTrace(); }
        
    }
    
    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        new UpdateTheProfile(user, this).setVisible(true);
    }//GEN-LAST:event_updateActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        this.dispose();
        new UserLoginWindow().setVisible(true);
    }//GEN-LAST:event_logOutActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       try {
        new AttemptQuiz(user, this).setVisible(true);
       } catch(Exception e) {
           JOptionPane.showMessageDialog(null, "No Quiz is Availabe At this moment");
       }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NameOfUser;
    private javax.swing.JLabel cmsId;
    private javax.swing.JLabel dep;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logOut;
    private javax.swing.JButton update;
    private javax.swing.JTextField welcomeField;
    // End of variables declaration//GEN-END:variables
}
