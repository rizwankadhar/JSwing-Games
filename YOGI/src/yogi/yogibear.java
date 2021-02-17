/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogi;

import java.awt.Image;

/**
 *
 * @author EliteBook
 */
public class yogibear extends sprite{
    private double velx;
    private double vely;
    private String blocked;

    public yogibear(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }

    public void moveX() {
        if (((int)velx < 0 && x > 0) || ((int)velx > 0 && x + width + velx <= 400)) {
            x += (int)velx;
        }

    }
    
    public void moveY() {
        if ((vely < 0 && y > 0) || (vely > 0 && y + height + vely <= 400)) {
            y += vely;
        }
    }
    
    public void move(){
        moveX();
        moveY();
    }
    public double getVelx() {
        return velx;
    }

    public void setVelx(double velx) {
        this.velx = velx;
    }
    
    public double getVely() {
        return vely;
    }

    public void setVely(double vely) {
        this.vely = vely;
    }
    
    public void setBlocked(String block){
        blocked = block;
    }
    public String getBlocked(){
        return blocked;
    }
    
    public Image getImage(){
        return image;
    }
}
