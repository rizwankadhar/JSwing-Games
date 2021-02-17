package clickcounter;
import java.awt.event.*;

public class Exit extends WindowAdapter
{
    @Override
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
}

