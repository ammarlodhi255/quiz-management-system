package groupk;
import javax.swing.*;
import java.sql.*;

public class ViewStudents extends JFrame {

    public String[] Header = {"Name  ","  Username  ","  Department  ", "Email", "  CMS-ID  ","  Score  "};
    String[][] Rows;
    JTable table;
    JScrollPane scrollpane;

    ViewStudents() {
      setTitle("View Students");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1000,600);
      addTable();
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setLocationRelativeTo(null);
    }


     public void addTable(){
         try {
                Connection myCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users?useSSL=false", "root", "sqlConnectivity123456");
                Statement stmt = myCon.createStatement();
                ResultSet rs = stmt.executeQuery("select * from stdInfo");
                int i = 0;
                while(rs.next()) {
                    i++;
                }

                Rows = new String[i][Header.length];
                i = 0;
                rs = stmt.executeQuery("select * from stdInfo");
                while(rs.next()) {
                    Rows[i][0] = rs.getString("name");
                    Rows[i][1] = rs.getString("username");
                    Rows[i][2] = rs.getString("department");
                    Rows[i][3] = rs.getString("email");
                    Rows[i][4] = rs.getString("cmdid");
                    Rows[i][5] = String.valueOf(rs.getInt("score"));
                    i++;
                }
                myCon.close();
                
                table = new JTable(Rows, Header);
                scrollpane = new JScrollPane(table);
                add(scrollpane);
         } catch(Exception e) { e.printStackTrace(); }
     }
}

