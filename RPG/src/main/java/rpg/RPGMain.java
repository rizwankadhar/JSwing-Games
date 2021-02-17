/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author EliteBook
 */
public class RPGMain {
    public static void main(String[] args){
        ArrayList<Character> players = new ArrayList<>();
        players.add(new blackDragons("BlackDragon 1", 100, 1000));
        players.add(new blackDragons("BlackDragon 1", 100, 1000));
        players.add(new redDragons("RedDragon 1", 100, 1000));
        players.add(new redDragons("RedDragon 1", 100, 1000));
        players.add(new berserkers("berserker 1", 100, 1000));
        players.add(new fighter("Fighter 1", 100, 1000));
        players.add(new defenders("defender 1", 100, 1000));
        players.add(new main("mainCharacter 1", 100, 1000, (float) 98.6));
        Random rand = new Random();
        while (players.size() > 1){
            int invader = rand.nextInt(players.size());
            int invaded = rand.nextInt(players.size());
            while (invader == invaded){
                invaded = rand.nextInt(players.size());
            }
            Character attacker = players.get(invader);
            Character attacked = players.get(invaded);
            attacker.attack(attacked);
            if (!attacked.alive()){
                players.remove(attacked);
            }
        }
        System.err.println(players.size());
        System.out.println(players.get(0).getName());
        

    }
}
