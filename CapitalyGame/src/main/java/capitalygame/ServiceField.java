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
public class ServiceField extends Field {
    private int cost;
    private int bank_balance;
    public ServiceField(String type, int cost){
        super(type);
        this.cost = cost;
        this.bank_balance = 0;
    }
    @Override
    public int getCost(){
        return cost;
    }
    @Override
    public void addToBank(){
        bank_balance += cost;
    }
    @Override 
    public void stepOver(Player p){
        p.payPenalty(this);
    }
    @Override
    public String toString(){
        return super.toString() + " and its cost is :" + cost;
    }
}
