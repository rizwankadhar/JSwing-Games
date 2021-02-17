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
public class defenders extends orc {
    public defenders(String nm, int hp, int AV){
        super(nm,hp,AV);
    }
        
    @Override
    public void invaded(Character invader){
       this.setHP(this.getHP() - (invader.getAV()/2));
    }
}
