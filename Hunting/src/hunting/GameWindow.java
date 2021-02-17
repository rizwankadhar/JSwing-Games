/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author EliteBook
 */
public class GameWindow extends BaseWindow {
    
    private GameModel model;
    private JButton[][] buttons;
    private JLabel label;
    private JLabel steps;
    private JPanel panel; 
    private JPanel bottom;
    private mainWindow mainWindow;
    
    public GameWindow(int size, mainWindow mainWindow){
        this.mainWindow = mainWindow;
        mainWindow.addWindow(this);
        
        model = new GameModel(size);
        
        bottom = new JPanel();
        JPanel top = new JPanel();
        
        label = new JLabel();
        steps = new JLabel();
        updateLabelText();
        
        JButton newGame = new JButton();
        newGame.setText("New Game");
        newGame.addActionListener(e -> newGame());
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(size, size));
        
        top.add(label);
        top.add(newGame);
        
        bottom.add(steps);
        buttons = new JButton[size][size];
        for (int i=0;i<size;++i){
            for (int j=0;j<size;++j){
                JButton button = addButton(i, j);
                buttons[i][j] = button;
            }
        }
        
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);
        this.pack();
        
    }
    
    private void newGame(){
        GameWindow newGameWindow = new GameWindow(model.getSize(), mainWindow);
        newGameWindow.setVisible(true);
        this.dispose();
        mainWindow.removeWindow(this);
    }
    
    
    private JButton addButton(int x, int y){
        JButton button = new JButton();
        if (x == (model.getSize())/2 && y == (model.getSize())/2){
            button.setText(Player.FUGITIVE.name());
        }
        if ((x==0 && y == model.getSize()-1) || (x == model.getSize()-1 && y == 0) || (x == 0 && y == 0)
                || (x == model.getSize()-1 && y == model.getSize()-1)){
            button.setText(Player.HUNTER.name());
        } 
        button.addActionListener(e -> {
            model.step(x, y);
            for (int i = 0; i < model.getSize(); ++i) {
            for (int j = 0; j < model.getSize(); ++j) {
                Player player = model.get(i, j);
                JButton newButton = buttons[i][j];
                if (player != Player.NULL) {
                    newButton.setText(player.name());
                } else {
                    newButton.setText("");
                }
            }
            }
            Player winner = model.findWinner();
            System.out.println("Winner is:" + winner.name());
            if (winner != Player.NULL){
                gameOver(winner);
            }
            updateLabelText();
        });
        button.setPreferredSize(new Dimension(100, 40));
        panel.add(button);
        return button;
        
    }
    
    private void updateLabelText(){
        label.setText("Current Player :" + model.getCurrentPlayer().name());
        steps.setText("StepsCount: " + model.stepsCount() );
    }
    
   
    private void gameOver(Player winner){
        JOptionPane.showMessageDialog(this, "Game Over! Winner is: " + winner.name());
        //newGame();
    }
    /*
    private void doUponExit(){
        this.dispose();
        mainWindow.removeWindow(this);
    }
    */
   
}
