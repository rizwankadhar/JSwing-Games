package sudoku.view;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import sudoku.model.Difficulty;

public class MenuBar extends JMenuBar {
    
    private Difficulty difficulty = Difficulty.EASY;
    
    public MenuBar(Action saveAction, Action loadAction,
            Action startNewGameAction, Action checkAction) {
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        JMenuItem save = new JMenuItem(saveAction);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        save.setText("Save");
        save.setMnemonic('S');
        fileMenu.add(save);

        JMenuItem load = new JMenuItem(loadAction);
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));
        load.setText("Load");
        load.setMnemonic('L');
        fileMenu.add(load);

        JMenu newGameMenu = new JMenu("New game");
        
        JMenuItem startNewGame = new JMenuItem(startNewGameAction);
        startNewGame.setText("Start");
        
        newGameMenu.add(startNewGame);
        
        newGameMenu.addSeparator();
        
        ButtonGroup group = new ButtonGroup();
        
        JRadioButtonMenuItem easy = new JRadioButtonMenuItem();
        easy.setText("Easy");
        easy.setSelected(true);
        easy.setActionCommand(Difficulty.EASY.name());
        easy.addActionListener(actionListener);
        group.add(easy);
        
        JRadioButtonMenuItem medium = new JRadioButtonMenuItem();
        medium.setText("Medium");
        medium.setActionCommand(Difficulty.MEDIUM.name());
        medium.addActionListener(actionListener);
        group.add(medium);
        
        JRadioButtonMenuItem hard = new JRadioButtonMenuItem();
        hard.setText("Hard");
        hard.setActionCommand(Difficulty.HARD.name());
        hard.addActionListener(actionListener);
        group.add(hard);
        
        newGameMenu.add(easy);
        newGameMenu.add(medium);
        newGameMenu.add(hard);
        
        JMenuItem checkMenuItem = new JMenuItem(checkAction);
        checkMenuItem.setText("Verify");
        
        add(fileMenu);
        add(newGameMenu);
        add(checkMenuItem);
    }

    private ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            difficulty = Difficulty.valueOf(actionCommand);
        }
        
    };

    public Difficulty getDifficulty() {
        return difficulty;
    }    
}
