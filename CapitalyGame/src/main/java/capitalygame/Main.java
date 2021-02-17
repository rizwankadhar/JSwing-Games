/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitalygame;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Rizwan Hussain
 */
public class Main {
    public static void main(String[] args){
        DataInput data = new DataInput();
        try {
            data.readInput("inp7.txt");
            ArrayList<Player> allPlayers = data.getAllPlayers();
        
            Player winner = allPlayers.get(0);
        
            for (Player p : allPlayers ){
                if (p.noOfAssets() > winner.noOfAssets() || (p.noOfAssets() == winner.noOfAssets() && p.getMoney() > winner.getMoney())){
                winner = p;
            }
        }
        System.out.println("The winner after the simulation is " + winner);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch(InvalidInputException e){
            System.out.println("Invalid Input");
        }catch(Exception e){
            System.out.println("Invalid Input");
        }

    }
}
