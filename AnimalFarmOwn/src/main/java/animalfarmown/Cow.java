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
public class Cow extends Animal {
    public Cow(String name, int weight, int nofmeals, ArrayList<Integer> meals) {
        super("C", name, weight, nofmeals, meals, 100);
    }
}
