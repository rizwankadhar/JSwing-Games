package clickcounter;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class ClickCounter extends JFrame
{
    private CounterButton    counterButton;
    private CounterLabel   counterLabel;
    private int             counter = 0;
    private JMenuItem       menuReset;
    
    public ClickCounter()
    {
        addWindowListener(new Exit());
        setTitle("Click Counter");
        setSize(400, 150);
       
        counterLabel = new CounterLabel();
        counterButton = new CounterButton(this);
        JComponent exitButton = new ExitButton();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(counterLabel)
                    .addComponent(counterButton))
            .addComponent(exitButton)
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(counterLabel)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(counterButton)
                .addComponent(exitButton))
        );
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu    menuActivities = new JMenu("Activities");
        menuActivities.setMnemonic('A');
        menuReset = new JMenuItem();
        menuReset.setAction(new AbstractAction() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                counterLabel.setNumClicks(counter = 0);
                menuReset.setEnabled(false);
            }
        });
        menuReset.setText("Reset");
        menuReset.setMnemonic('R');
        menuReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        menuReset.setEnabled(false);
        

        JMenuItem menuExit = new JMenuItem(new AbstractAction() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Exit().windowClosing(null); // System.exit(0);
            }
        });
        menuExit.setText("Exit");
        menuExit.setMnemonic('K');
        menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_DOWN_MASK));

        menuActivities.add(menuReset);
        menuActivities.addSeparator();
        menuActivities.add(menuExit);
        menuBar.add(menuActivities);
        setJMenuBar(menuBar);

        java.net.URL url = ClickCounter.class.getResource("icon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        setVisible(true);
        
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);
    }
    
    private final MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mouseMoved(MouseEvent e) {
            setTitle("Counter - " + e.getX() + "," + e.getY());
            super.mouseMoved(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            setTitle("Counter - " + e.getPreciseWheelRotation());
            super.mouseWheelMoved(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setTitle("Counter - MouseOut");
            super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setTitle("Counter - MouseIn");
            super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
        }
            
    };
    
    public void incrementCounter()
    {
        menuReset.setEnabled(true);
        counterLabel.setNumClicks(++counter);
    }
    
    public static void main(String[] args) {
        new ClickCounter();
    }   
}
