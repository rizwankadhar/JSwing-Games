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
public class CarefulPlayer extends Player {
   public CarefulPlayer(String name){
       super(name, "CP", 10000);
   }
   @Override
   public boolean willBuy(Field f){
       return this.getMoney() >= 2*f.getCost();
   }
}
