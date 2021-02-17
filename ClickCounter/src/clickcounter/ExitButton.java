package clickcounter;
import java.awt.event.*;
import javax.swing.*;

public class ExitButton extends JButton
{
    public ExitButton()
    {
        super(new AbstractAction("Exit") 
        {          
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                new Exit().windowClosing(null); // System.exit(0);
            }
        });
    }
}

