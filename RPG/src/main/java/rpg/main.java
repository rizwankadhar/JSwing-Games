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
public class main extends Character {
    private final float defVal;
    public main(String nm, int hp, int AV, float dV){
        super(nm,hp, AV);
        this.defVal = dV;
    }
    @Override
    public void invaded(Character invader){
        super.invaded(invader);
        int av = (int) defVal;
        invader.setAV(invader.getAV() / av);
    }
}
