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
public class TacticalPlayer extends Player {
    public TacticalPlayer(String name){
        super(name, "TP", 10000);
        tactics = false;
    }
   @Override
   public boolean willBuy(Field f){
       boolean result = false;
       if (this.hasEnoughMoney(f)){
           if (this.getTactics()){
               result = true;
           }
           this.setTactics();
       }
       return result;

   }

}
