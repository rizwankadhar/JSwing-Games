/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogi;

/**
 *
 * @author EliteBook
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Game extends JPanel {

    private final int FPS = 240;
    private final int BEAR_MOVEMENT = 40;
    private boolean paused = false;
    private Image background;
    private int levelNum = 0;
    private int lives;
    private level level;
    private yogibear bear;
    private Timer newFrameTimer;
    
    HighScores highscores;

    public Game() {
        super();
        lives = 3;
        try {
            highscores = new HighScores(10);
            System.out.println("highscores are :" + highscores.getHighScores());
        }catch (SQLException ex){
            
        }
        background = new ImageIcon("data/images/background.png").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogibear newBear = new yogibear(bear.getX()-BEAR_MOVEMENT, bear.getY(), bear.getWidth(), bear.getWidth(), bear.getImage());
                if (!level.collides(newBear)){
                   bear.setVelx(-BEAR_MOVEMENT);
                    bear.moveX();
                    level.pickBasket(bear);
                    System.out.println("bear.x : " + bear.getX() + " bear.y: " + bear.getY());
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogibear newBear = new yogibear(bear.getX()+BEAR_MOVEMENT, bear.getY(), bear.getWidth(), bear.getWidth(), bear.getImage());
                if (!level.collides(newBear)){
                   bear.setVelx(BEAR_MOVEMENT);
                    bear.moveX();
                    level.pickBasket(bear);
                    System.out.println("bear.x : " + bear.getX() + " bear.y: " + bear.getY());
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogibear newBear = new yogibear(bear.getX(), bear.getY()+BEAR_MOVEMENT, bear.getWidth(), bear.getWidth(), bear.getImage());
                if (!level.collides(newBear)){
                   bear.setVely(BEAR_MOVEMENT);
                   bear.moveY(); 
                   level.pickBasket(bear);
                   System.out.println("bear.x : " + bear.getX() + " bear.y: " + bear.getY());
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogibear newBear = new yogibear(bear.getX(), bear.getY()-BEAR_MOVEMENT, bear.getWidth(), bear.getWidth(), bear.getImage());
                if (!level.collides(newBear)){
                   bear.setVely(-BEAR_MOVEMENT);
                    bear.moveY();
                    level.pickBasket(bear);
                    System.out.println("bear.x : " + bear.getX() + " bear.y: " + bear.getY());
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
        
        
    }

    public void restart() {
        //levelNum = 0;
        try {
            level = new level("data/levels/level0" + levelNum + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Image bearImage = new ImageIcon("data/images/bear.png").getImage();
        bear = new yogibear(0, 0, 40, 40, bearImage);

    }
    public void setLevel(int level){
        levelNum = level;
    }
    
    public ArrayList<HighScore> getScores(){
        ArrayList<HighScore> scores = null;
        try {
            scores=  highscores.getHighScores();   
        }catch (SQLException ex){
            
        }
        return scores;
        
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 400, 400, null);
        level.draw(grphcs);
        bear.draw(grphcs);
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                for (ranger ranger : level.getRangers()){
                   ranger.move();
                   if (level.collides(ranger)) ranger.invertVel();
                }
            }
            if (level.gotBusted(bear)){
                lives--;
                if (lives == 0){
                    String msg = "Oops, You have lost the Game!";
                    JOptionPane.showMessageDialog(Game.this, msg, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        System.out.println(highscores.getHighScores());
                        String name = JOptionPane.showInputDialog("Enter your name");
                        highscores.putHighScore(name, levelNum);
                        System.out.println(highscores.getHighScores());
                    }catch (SQLException ex){
                        
                    }
                    
                    levelNum = 0;
                    restart();
                    lives = 3;
                }
                else {
                    String msg = "You have lost a life. You have only " + lives + " lives left!";
                    JOptionPane.showMessageDialog(Game.this, msg, "LIFE LOST MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    Image bearImage = new ImageIcon("data/images/bear.png").getImage();
                    bear = new yogibear(0, 0, 40, 40, bearImage);
                }
                
            }
            if (level.isOver()) {
                if (levelNum == 9){
                    String msg = "Wow. You are a Pro!";
                    JOptionPane.showMessageDialog(Game.this, msg, "PRO Spotted", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                else{
                    levelNum = (levelNum+1) % 9;
                    restart();
                }
                
            }
            repaint();
        }

    }
}

