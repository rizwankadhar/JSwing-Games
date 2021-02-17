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
public class GreedyPlayer extends Player{
    public GreedyPlayer(String name){
        super(name, "GP",10000);
    }
   @Override
   public boolean willBuy(Field f){
       return this.hasEnoughMoney(f);
   }

}
