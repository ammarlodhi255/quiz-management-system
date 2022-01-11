package groupk;
import java.sql.Statement;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ObjectToSQL {
    static final String TO_WRITE_OBJECT = "INSERT INTO JAVAOBJECTS(OBJECTID, OBJECT_NAME, OBJECT_VALUE) VALUES (?, ?, ?)";
    static final String TO_READ_OBJECT = "SELECT OBJECT_VALUE FROM JAVAOBJECTS WHERE OBJECTID = ?";

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://127.0.0.1:3306/objectdat?useSSL=false";
    static String username = "root";
    static String password = "sqlConnectivity123456";


    public static Connection getConnection() throws Exception {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static int writeTheObject(Connection conn, Object object) throws Exception {
        String className = object.getClass().getName();
        PreparedStatement pstmt = conn.prepareStatement(TO_WRITE_OBJECT);
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("select * from javaobjects");
        int id = 0;
        while(r.next()) id++;

        pstmt.setInt(1, id);
        pstmt.setString(2, className);
        pstmt.setObject(3, object);
        pstmt.executeUpdate();
        pstmt.close();
        return id;
    }

    public static Object readTheObject(Connection conn, int id) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(TO_READ_OBJECT);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        byte[] buf = rs.getBytes("OBJECT_VALUE");
        ObjectInputStream objectIn = null;

        if (buf != null)
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        Object object = objectIn.readObject();
        String className = object.getClass().getName();
        rs.close();
        pstmt.close();
        return object;
    }
}
