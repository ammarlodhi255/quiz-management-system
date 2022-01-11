package groupk;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UpdateTheProfile extends JFrame {
    private UserInfo user;
    private UserQuizWind quizWind;
    private AdminInfo admin;
    private AdminInitWind initWind;
    private ImageIcon currentImg;
    private ImageIcon imgSet;
    boolean isSet = false;
    boolean adminState = false;
    private ArrayList<UserInfo> userObjects = new ArrayList<UserInfo>();
    private ArrayList<AdminInfo> adminObjects = new ArrayList<AdminInfo>();
    
    public UpdateTheProfile(UserInfo user, UserQuizWind quizWind) {
        initComponents();
        setTitle("Update your profile");
        this.user = user;
        this.quizWind = quizWind;
        if(user.getUserPic() != null) {
            currentImg = user.getUserPic();
            userPic.setIcon(currentImg);
        }
        readUsersToArr();
        
        initFields();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public UpdateTheProfile(AdminInfo admin, AdminInitWind initWind) {
        initComponents();
        this.admin = admin;
        this.initWind = initWind;
        adminState = true;
        if(admin.getAdminPic() != null) {
            currentImg = admin.getAdminPic();
            userPic.setIcon(currentImg);
        }
        readAdminsToArr();
        
        initFieldsAdmin();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public void deleteCurrentUser() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/users?useSSL=false";
        String username = "root";
        String password = "sqlConnectivity123456";
        String sql = "delete from userInfo where username="+ "'"+ user.getUserName() + "'";
        
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
            Statement stmt = conn.createStatement()) {
      
        stmt.executeUpdate(sql);
        System.out.println("Record deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            }
        }
    
    public void deleteCurrentAdmin() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/adminInfo?useSSL=false";
        String username = "root";
        String password = "sqlConnectivity123456";
        String sql = "delete from admin where username=" + "'"+ admin.getUserName() + "'";
        
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
            Statement stmt = conn.createStatement()) {
      
            stmt.executeUpdate(sql);
            System.out.println("Record deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readUsersToArr() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users?useSSL=false", "root" , "sqlConnectivity123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from stdInfo");
            
            while(rs.next()) {
                int id = rs.getInt(1);
                UserInfo user = new UserInfo(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                userObjects.add(user);
            }
        } catch(Exception exObj) { System.out.println(exObj) ; }
    }
    
    public void readAdminsToArr() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/adminInfo?useSSL=false", "root" , "sqlConnectivity123456");
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery("select * from admin");
            
           while(rs.next()) {
                int id = rs.getInt(1);
                AdminInfo user = new AdminInfo(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), null, rs.getString(6));
                adminObjects.add(user);
            }
        } 
        catch(Exception exObj) 
        { 
            System.out.println(exObj); 
        }
    }
    
    
    public void UpdateInfo() {
        try{
            int id = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users?useSSL=false", "root" , "sqlConnectivity123456");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from stdInfo");
            if(rs.next()) {
                while(rs.next()) 
                    id = rs.getInt(1);
            }
            else
                id = 1;
            
            String str = "insert into stdInfo values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(str);
            ps.setInt(1, ++id);
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getUserPassword());
            ps.setString(4, user.getName());
            ps.setString(5, user.getDepartment());
            ps.setString(6, user.getCMS_ID());
            ps.setString(7, user.getEmail());
            
            ps.executeUpdate();
            con.close();
        } catch(Exception exObj) { System.out.println("I/O Exception Occurred!" + exObj); }
            JOptionPane.showMessageDialog(null, "User Info Updated");
            quizWind.setUp();  
    }
    
    public void UpdateInfoForAdmin() {
        try{
            int id = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/adminInfo?useSSL=false", "root" , "sqlConnectivity123456");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from admin");
            if(rs.next()) {
                while(rs.next()) 
                    id = rs.getInt(1);
            }
            else
                id = 1;
            
            String str = "insert into admin values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(str);
            ps.setInt(1, ++id);
            ps.setString(2, admin.getUserName());
            ps.setString(3, admin.getUserPassword());
            ps.setString(4, admin.getName());
            ps.setString(5, admin.getDepartment());
            ps.setString(6, admin.getEmail());
            
            ps.executeUpdate();
            con.close();
        } catch(Exception exObj) { System.out.println("I/O Exception Occurred!" + exObj); }
            JOptionPane.showMessageDialog(null, "Admin Info Updated");
            initWind.setUp();  
    }
    
    public void initFields() {
        name.setText(user.getName());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
        phNo.setText(user.getPhNo());
        if(user.getUserPic() != null)
            userPic.setIcon(user.getUserPic());
    }
    
    public void initFieldsAdmin() {
        name.setText(admin.getName());
        email.setText(admin.getEmail());
        address.setText(admin.getAddress());
        phNo.setText(admin.getPhNo());
        if(admin.getAdminPic() != null)
            userPic.setIcon(admin.getAdminPic());
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        phNo = new javax.swing.JTextField();
        userPic = new javax.swing.JLabel();
        browse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("E-mail:");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(0, 102, 102));
        saveButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("SAVE");
        saveButton.setToolTipText("");
        saveButton.setFocusable(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Phone/Mobile No:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Address:");

        browse.setText("Browse Profile Pic");
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phNo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userPic, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(browse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phNo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(userPic, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(browse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if(name.getText().isEmpty() || email.getText().isEmpty() || address.getText().isEmpty() 
                || phNo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty fields!");
        } else if (adminState){
            deleteCurrentAdmin();
            admin.setName(name.getText());
            admin.setEmail(email.getText());
            admin.setAddress(address.getText());
            admin.setPhNo(phNo.getText());
            if(isSet)
                admin.setAdminPic((ImageIcon)userPic.getIcon());
            
            adminObjects.set(getAdminIndex(), admin);
            UpdateInfoForAdmin();
            this.dispose();
        } else {
            deleteCurrentUser();
            user.setName(name.getText());
            user.setEmail(email.getText());
            user.setAddress(address.getText());
            user.setPhNo(phNo.getText());
            if(isSet)
                user.setUserPic((ImageIcon)userPic.getIcon());
            
            userObjects.set(getUserIndex(), user);
            UpdateInfo();
            this.dispose();
        }
    }//GEN-LAST:event_saveButtonActionPerformed
    
    public int getUserIndex() {
        for(int i = 0; i < userObjects.size(); i++) {
            if(user.getUserName().equals(userObjects.get(i).getUserName())) {
                return i;
            }
        }
        return -1;
    }
    
    public int getAdminIndex() {
        for(int i = 0; i < adminObjects.size(); i++) {
            if(admin.getUserName().equals(adminObjects.get(i).getUserName())) {
                return i;
            }
        }
        return -1;
    }
    
    
    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            userPic.setIcon(ResizeImage(path));
            isSet = true;
        }
        else if(result == JFileChooser.CANCEL_OPTION){
           System.out.println("No File Selected");
        }
    }//GEN-LAST:event_browseActionPerformed
        
     public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(userPic.getWidth(), userPic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
     
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JButton browse;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField phNo;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel userPic;
    // End of variables declaration//GEN-END:variables
}
