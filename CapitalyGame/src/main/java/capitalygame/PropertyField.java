/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitalygame;

/**
 *
 * @author Rizwan Hussain
 */
public class PropertyField extends Field {
    public PropertyField(String type){
        super(type);
    }
    @Override
    public int getCost(){
        if (this.status.equals("free")){
            return 0;
        }else if (this.status.equals("owner")){
            return 1000;
        }else {
            return 4000;
        }
    }
    @Override 
    public void stepOver(Player p){
        p.buyOrBuild(this);
    }
}
