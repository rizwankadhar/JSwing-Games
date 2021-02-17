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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.ImageIcon;

public class level {

    // each brick is 40x20, so there can be at most 20 bricks side by side
    // the last 10 rows will be empty, so there can be at most 20 rows of bricks
    private final int SPRITE_WIDTH = 40;
    private final int SPRITE_HEIGHT = 40;
    ArrayList<tree> trees;
    ArrayList<mountain> mountains;
    ArrayList<ranger> rangers;
    ArrayList<basket> baskets;
//    private int lives;
//    private int collectedBaskets;
    

    public level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }

    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        trees = new ArrayList<>();
        mountains = new ArrayList<>();
        rangers = new ArrayList<>();
        baskets = new ArrayList<>();
        //lives = 3;
        int y = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int x = 0;
            for (char blockType : line.toCharArray()) {
                Image image;
                switch (blockType){
                    case 'T':
                        image = new ImageIcon("data/images/tree.png").getImage();
                        trees.add(new tree(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, image));
                        break;
                    case 'M':
                        image = new ImageIcon("data/images/mountain.png").getImage();
                        mountains.add(new mountain(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, image));
                        break;
                        
                    case 'R':
                        image = new ImageIcon("data/images/ranger.png").getImage();
                        rangers.add(new ranger(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, image));
                        break;
                    case 'B':
                        image = new ImageIcon("data/images/basket.png").getImage();
                        baskets.add(new basket(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, image));
                        break;
                    default:
                        
                }
                    
                x++;
            }
            y++;
        }
    }

    public boolean collides(yogibear bear) {
            for (mountain mount: mountains){
                if (bear.collides(mount)){
                    return true;
                }
            }
            for (tree tree: trees){
                if (bear.collides(tree)){
                    return true;
                }
            }

            return false;
        
    }
    
    public void pickBasket(yogibear bear){
        basket picked = null;
        for (basket basket : baskets){
            if (bear.collides(basket)){
                picked = basket;
                break;
            }
        }
        if (picked != null) {
            baskets.remove(picked);
        }
        
    }
    
    public boolean gotBusted(yogibear bear){
        yogibear[] array = {
        new yogibear(bear.getX() + 40, bear.getY(), bear.getWidth(), bear.getHeight(), bear.getImage()),
        new yogibear(bear.getX()-40, bear.getY(), bear.getWidth(), bear.getHeight(), bear.getImage()),
        new yogibear(bear.getX(), bear.getY() + 40, bear.getWidth(), bear.getHeight(), bear.getImage()),
        new yogibear(bear.getX(), bear.getY() - 40, bear.getWidth(), bear.getHeight(), bear.getImage()),
        new yogibear(bear.getX() + (2*40), bear.getY(), bear.getWidth(), bear.getHeight(), bear.getImage()),
        new yogibear(bear.getX()-(2*40), bear.getY(), bear.getWidth(), bear.getHeight(), bear.getImage()),
        new yogibear(bear.getX(), bear.getY() + (2*40), bear.getWidth(), bear.getHeight(), bear.getImage()),
        new yogibear(bear.getX(), bear.getY() - (2*40), bear.getWidth(), bear.getHeight(), bear.getImage())
       };
       for (ranger ranger : rangers){
            for (yogibear newbear : array){
                if (newbear.collides(ranger)){
                    if (!isObstacleBetween(bear, ranger)){
                        System.out.println("ranger width:" + ranger.getWidth() + " ranger height:"+ ranger.getHeight()+
                                "ranger.x: "+ ranger.getX() + "ranger.y: " + ranger.getY());
                       return true; 
                    }
                    
                }
            }
        }
       return false;
    }
    
    public boolean isObstacleBetween(yogibear yogi, ranger rang){
        for (basket basket : baskets){
            if (basket.isBetween(yogi, rang)) return true;
        }
        for (tree tree : trees){
            if (tree.isBetween(yogi, rang)) return true;
        }
        for (mountain hill : mountains){
            if (hill.isBetween(yogi, rang)){
                System.out.println("hill.x :" + hill.getX() + " hill.y: " + hill.getY());
                return true;
            }
            
        }
        return false;
    }
    
    public boolean collides(ranger ranger) {
        for (basket basket : baskets) {
            if (ranger.collides(basket)) {
                return true;
            }
        }
        for (mountain mount: mountains){
            if (ranger.collides(mount)){
                return true;
            }
        }
        for (tree tree: trees){
            if (ranger.collides(tree)){
                return true;
            }
        }    
        for (ranger newranger: rangers){
            if (!ranger.equals(newranger)){
                if (ranger.collides(newranger)){
                    return true;
                }
            }
        }
        return false;
        
    }
    
    public boolean isOver() {
        return baskets.isEmpty();
    }
 

    public void draw(Graphics g) {
        for (basket basket : baskets) {
            basket.draw(g);
        }
        for (mountain mount : mountains){
            mount.draw(g);
        }
        for (tree tree : trees){
            tree.draw(g);
        }
        for (ranger ranger : rangers){
            ranger.draw(g);
        }
    }

    public ArrayList<tree> getTrees() {
        return trees;
    }

    public ArrayList<mountain> getMountains() {
        return mountains;
    }

    public ArrayList<ranger> getRangers() {
        return rangers;
    }

    public ArrayList<basket> getBaskets() {
        return baskets;
    }

}

