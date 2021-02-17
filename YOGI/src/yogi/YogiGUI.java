/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogi;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author EliteBook
 */
public class YogiGUI {
    private JFrame frame;
    private Game gameArea;

    public YogiGUI() {
        System.out.println("YOGIGUI");
        frame = new JFrame("YogiBear");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameArea = new Game();
        
        frame.getContentPane().add(gameArea);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        
        JMenuItem restart = new JMenuItem(new AbstractAction("Restart") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.setLevel(0);
                gameArea.restart();
            }
        });
        menuGame.add(restart);
        
        JMenuItem displayScores = new JMenuItem(new AbstractAction("DisplayScores"){
           @Override
           public void actionPerformed(ActionEvent ae){
               ArrayList<HighScore> scores = gameArea.getScores();
               Object[][] rows = new Object[scores.size()][3];
               int i = 0;
               for (HighScore score : scores ){
                   rows[i][0] = i+1;
                   rows[i][1] = score.getName();
                   rows[i][2] = score.getScore();
                   i++;
               }
                Object[] cols = {"Serial .No",
                    "Name","Symbol"
                    };
                JTable table = new JTable(rows, cols);
                JOptionPane.showMessageDialog(gameArea, new JScrollPane(table), "HIGHSCORES", JOptionPane.PLAIN_MESSAGE);
           }
        });
        menuGame.add(displayScores);
        menuBar.add(menuGame);
        frame.setJMenuBar(menuBar);
        frame.setPreferredSize(new Dimension(415, 460));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
