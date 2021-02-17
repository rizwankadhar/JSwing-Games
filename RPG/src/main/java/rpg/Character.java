/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

/**
 *
 * @author EliteBook
 */
public class Character {
    private String name;
    private int HP;
    private int attackVal;
    
    public Character(String nm, int hit, int atk){
        this.name = nm;
        this.HP = hit;
        this.attackVal = atk;
    }
    public int getHP(){
        return HP;
    }
    public int getAV(){
        return attackVal;
    }
    public String getName(){
        return name;
    }
    public void setHP(int dx){
        HP = dx;
    }
    public void setAV(int dx){
        attackVal = dx;
    }
    
    public void attack(Character attacked){
        if (alive()){
            attacked.invaded(this);

        }
    }
    
    public void invaded(Character invader){
        if (alive()){
         this.setHP(this.getHP() - invader.getAV());   
        }
    }
    public boolean alive(){
        return this.HP >= 0;
    }
}
