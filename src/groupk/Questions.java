package groupk;
import java.io.Serializable;

public class Questions implements Serializable {
    
    protected Object[] questions;
    protected String[][] options;
    protected int[] correctOptions;
    protected int time;

    public Questions(Object[] questions, String[][] options, int[] correctOptions, int time) {
        this.questions = questions;
        this.options = options;
        this.correctOptions = correctOptions;
        this.time = time;
    }

    public Object[] getQuestions() {
        return questions;
    }

    public void setQuestions(Object[] questions) {
        this.questions = questions;
    }

    public String[][] getOptions() {
        return options;
    }

    public void setOptions(String[][] options) {
        this.options = options;
    }

    public int[] getCorrectOptions() {
        return correctOptions;
    }

    public void setCorrectOptions(int[] correctOptions) {
        this.correctOptions = correctOptions;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    
    
}
