package clickcounter;
import javax.swing.JLabel;

public class CounterLabel extends JLabel {
    public CounterLabel(){
        setText("Clicks: 0");
    }
    
    public void setNumClicks(int clicks) {
        setText("Clicks: " + clicks);
    }
}
