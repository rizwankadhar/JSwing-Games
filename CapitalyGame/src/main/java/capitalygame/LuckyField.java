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
public class LuckyField extends Field {
    private int cost;
    public LuckyField(String type, int cost){
        super(type);
        this.cost = cost;
    }
    @Override
    public int getCost(){
        return cost;
    }
    @Override 
    public void stepOver(Player p){
        p.getReward(this);
    }
    
    @Override
    public String toString(){
        return super.toString() + " and its cost is :" + cost;
    }
    
}
