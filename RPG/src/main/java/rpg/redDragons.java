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
public class redDragons extends dragon {
    public redDragons(String nm, int hp, int AV){
        super(nm,hp, AV);
    }   
    
    @Override
    public void invaded(Character invader){
        if (invader.getAV() > 60){
            this.setHP(this.getHP() - invader.getAV());
        }
    }
}
