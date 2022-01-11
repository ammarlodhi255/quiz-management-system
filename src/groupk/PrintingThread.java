package groupk;
import javax.swing.JTextField;
public class PrintingThread implements Runnable {
    Thread t1;
    private String text;
    private JTextField g1;

    public PrintingThread (JTextField g1, String text) {
        this.text = text;
        this.g1 = g1;
        t1 = new Thread(this, "PrintingThread");
    }
    
    public void setText(String text) {
        this.text = text;
    }

    public void run()
    {
        String[] splitText = text.split("");
        String newString = new String();
        for (String s : splitText) 
        {
            newString += s;
            g1.setText(newString);
            try { 
                t1.sleep(50); 
            }
            catch(InterruptedException e) {}
        }
    }
}
