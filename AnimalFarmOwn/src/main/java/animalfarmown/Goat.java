/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animalfarmown;

import java.util.ArrayList;

/**
 *
 * @author EliteBook
 */
public class Goat extends Animal {
    public Goat(String name, int weight, int nofmeals, ArrayList<Integer> meals) {
        super("G", name, weight, nofmeals, meals, 12);
    }
}
