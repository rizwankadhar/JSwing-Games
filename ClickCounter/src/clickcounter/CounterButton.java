package clickcounter;
import java.awt.event.*;
import javax.swing.*;

public class CounterButton extends JButton implements ActionListener
{
    private ClickCounter clickCounter;
    
    public CounterButton(ClickCounter clickCounter)
    {
        this.clickCounter = clickCounter;
        setText("Click");
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        clickCounter.incrementCounter();
    }
}
