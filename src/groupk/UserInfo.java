package groupk;
import javax.swing.ImageIcon;

public class UserInfo extends User {
    private String CMS_ID;
    private double totalMarks, recentMarks;
    private ImageIcon picture;
    private int questionIdx;

    public ImageIcon getPicture() {
        return picture;
    }

    public void setPicture(ImageIcon picture) {
        this.picture = picture;
    }

    public int getQuestionIdx() {
        return questionIdx;
    }

    public void setQuestionIdx(int questionIdx) {
        this.questionIdx = questionIdx;
    }
    
    public double getTotalMarks() {
        return totalMarks;
    }

    public ImageIcon getUserPic() {
        return picture;
    }

    public void setUserPic(ImageIcon userPic) {
        this.picture = userPic;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public double getRecentMarks() {
        return recentMarks;
    }

    public void setRecentMarks(double recentMarks) {
        this.recentMarks = recentMarks;
    }
    public UserInfo(String userName, String userPassword, String name, String department, String CMS_ID, String email) {
        super(userName, userPassword, name, department, email);
        this.CMS_ID = CMS_ID;
    }

    public String getCMS_ID() {
        return CMS_ID;
    }

    public void setCMS_ID(String CMS_ID) {
        this.CMS_ID = CMS_ID;
    }
    
    public void getUserInfo() {
        System.out.println("Name: " + getName() + "\nCMS_ID: " + getCMS_ID() + "\nDepartment: "
                + getDepartment());
    }
}

