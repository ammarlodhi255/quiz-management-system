package groupk;

import javax.swing.ImageIcon;

public class AdminInfo extends User {
    private String INS_ID;
    private ImageIcon adminPic;

    public ImageIcon getAdminPic() {
        return adminPic;
    }

    public void setAdminPic(ImageIcon adminPic) {
        this.adminPic = adminPic;
    }
    
    public AdminInfo(String userName, String userPassword, String name, String department, String INS_ID, String email) {
        super(userName, userPassword, name, department, email);
        this.INS_ID = INS_ID;
    }

    public String getINS_ID() {
        return INS_ID;
    }
    
    public void setINS_ID(String INS_ID) {
        this.INS_ID = INS_ID;
    }
    
    public void getUserInfo() {
          System.out.println("Name: " + getName() + "\nINS_ID: " + getINS_ID() + "\nDepartment: "
                + getDepartment());
    }
}
