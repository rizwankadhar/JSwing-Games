/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bag;

import java.awt.BorderLayout;

/**
 *
 * @author bli
 */
public class TestBag {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bag bag = new Bag();
        Bag bag2 = new Bag();
        bag.add("a", 121);
        bag.add("b", 33);
        bag.add("c", 32);
        bag2.add("a", 42);
        bag2.add("c", 12);
        System.out.println("The union of " + bag + " and " + bag2 + " is " + bag.union(bag2) + ".");
        System.out.println("The intersection of " + bag + " and " + bag2 + " is " + bag.intersection(bag2) + ".");
        try{
            System.out.println("The difference of " + bag + " and " + bag2 + " is " + bag.difference(bag2) + ".");        
        }catch(Exception e){
            System.err.println("Exception");
        }
    }
    
}
