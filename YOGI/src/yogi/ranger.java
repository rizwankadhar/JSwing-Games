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
public class ranger extends sprite {
    private double velx;
    private double vely;

    public ranger(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        double rand = Math.random()*2-1;
        System.out.println("rand is :" + rand);
        velx = rand >= 0 ? 1 : 0;
        vely = rand < 0 ? 1 : 0;
        System.out.println("xvel :" + velx + " vely :" + vely);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ranger other = (ranger) obj;
        if (Double.doubleToLongBits(this.velx) != Double.doubleToLongBits(other.velx)) {
            return false;
        }
        if (Double.doubleToLongBits(this.vely) != Double.doubleToLongBits(other.vely)) {
            return false;
        }
        return true;
    }

    public void moveX() {
        x += velx;
        if (x + width >= 400 || x <= 0) {
            invertVelX();
        }
    }

    public void moveY() {
        y += vely;
        if (y + height >= 400 || y <= 0) {
            invertVelY();
        }
    }
    
    public void move(){
        if (velx != 0 ) moveX();
        else moveY();
    }
    public void invertVel(){
        if (velx != 0) invertVelX();
        else invertVelY();
    }

    public void invertVelX() {
        velx = -velx;
    }

    public void invertVelY() {
        vely = -vely;

    }
}
