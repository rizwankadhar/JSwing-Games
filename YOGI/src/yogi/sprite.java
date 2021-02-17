/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogi;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author EliteBook
 */
public class sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    public sprite(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
    
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
    
    /**
     * Returns true if this sprite collides with the other sprite
     * @param other
     * @return 
     */
    public boolean collides(sprite other) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(other.x, other.y, other.width, other.height);        
        return rect.intersects(otherRect);
    }
    
    public boolean isBetween(sprite s1, sprite s2){
        return isAdjacent(s1) && isAdjacent(s2);
    }
    
    public boolean isAdjacent(sprite other){
       sprite[] array = {
        new sprite(other.getX() + 40, other.getY(), other.getWidth(), other.getHeight(), other.getImage()),
        new sprite(other.getX()-40, other.getY(), other.getWidth(), other.getHeight(), other.getImage()),
        new sprite(other.getX(), other.getY() + 40, other.getWidth(), other.getHeight(), other.getImage()),
        new sprite(other.getX(), other.getY() - 40, other.getWidth(), other.getHeight(), other.getImage()),
        new sprite(other.getX() + (2*40), other.getY(), other.getWidth(), other.getHeight(), other.getImage()),
        new sprite(other.getX()-(2*40), other.getY(), other.getWidth(), other.getHeight(), other.getImage()),
        new sprite(other.getX(), other.getY() + (2*40), other.getWidth(), other.getHeight(), other.getImage()),
        new sprite(other.getX(), other.getY() - (2*40), other.getWidth(), other.getHeight(), other.getImage())
       };
       for (sprite spr : array) {
           if (this.collides(spr)){
               return true;
           }
       }
       return false;
       
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public Image getImage(){
        return image;
    }
}
