package groupk;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainWind extends JFrame {
    private JButton adminButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JPanel jPanel4;
    public JTextField threadWelcome1;
    private JButton userButton1;
    
    public MainWind() {
        super("IBA Quiz Management System");
        WindowListen w = new WindowListen();
        addWindowListener(w);
        setComponents();
        setLocationRelativeTo(null);
    }
    
    private void adminButton1ActionPerformed(ActionEvent evt) {
        new AdminLoginWindow().setVisible(true);
        this.dispose();
    }

    private void userButton1ActionPerformed(ActionEvent evt) {
        new UserLoginWindow().setVisible(true);
        this.dispose();
    }
    
    private class WindowListen extends WindowAdapter {
        public void windowOpened(WindowEvent e) {
            PrintingThread pt = new PrintingThread(threadWelcome1, "* Welcome to IBA Quiz Management System *");
            pt.t1.start();
        }
    }
    
    private void setComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jPanel3 = new JPanel();
        threadWelcome1 = new JTextField();
        adminButton1 = new JButton();
        userButton1 = new JButton();
        jPanel4 = new JPanel();
        jLabel2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setResizable(false);

        jLabel1.setBackground(new Color(0, 153, 153));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new Color(255, 255, 255));

        threadWelcome1.setEditable(false);
        threadWelcome1.setBackground(new Color(255, 255, 255));
        threadWelcome1.setFont(new Font("Times New Roman", 1, 24)); // NOI18N
        threadWelcome1.setForeground(new Color(51, 51, 51));
        threadWelcome1.setHorizontalAlignment(JTextField.CENTER);
        threadWelcome1.setText("..................");
        threadWelcome1.setBorder(null);

        adminButton1.setBackground(new Color(18, 127, 126));
        adminButton1.setFont(new Font("Times New Roman", 1, 14));
        adminButton1.setForeground(new Color(255, 255, 255));
        adminButton1.setText("Login as Administrator");
        adminButton1.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        adminButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                adminButton1ActionPerformed(evt);
            }
        });

        userButton1.setBackground(new Color(18, 127, 126));
        userButton1.setFont(new Font("Times New Roman", 1, 14)); 
        userButton1.setForeground(new Color(255, 255, 255));
        userButton1.setText("Login as User");
        userButton1.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                userButton1ActionPerformed(evt);
            }
        });

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(threadWelcome1, GroupLayout.Alignment.TRAILING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(userButton1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminButton1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
                .addGap(368, 368, 368))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(threadWelcome1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminButton1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(userButton1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\index\\sourceimgs\\1_1zm3-a2CU7_8OZNpxgJaHA.jpeg")); // NOI18N

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(106, 106, 106)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }
    
    public static void main(String args[]) {
        new MainWind().setVisible(true);
    }
}
