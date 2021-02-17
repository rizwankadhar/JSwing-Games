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
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

public class DataInput {
    private final ArrayList<Player> players;
    private final ArrayList<Field> fields;
    private final ArrayList<Integer> diceRolls;
    
    public DataInput(){
        players = new ArrayList<>();
        fields = new ArrayList<>();
        diceRolls = new ArrayList<>();
    }
    public ArrayList<Player> getAllPlayers(){
        return players;
    }
    public ArrayList<Field> getAllFields(){
        return fields;
    }
    
    public void readInput(String filename) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int noOfFields = sc.nextInt();
        //System.out.println(noOfFields);
        Field field;
        for (int i=0;i<noOfFields; ++i){
            String type = sc.next();
            //System.out.println(i);
            //System.out.println(type);
            switch(type){
                case "S":
                    field = new ServiceField(type, sc.nextInt());
                    break;
                case "L":
                    field = new LuckyField(type, sc.nextInt());
                    break;
                case "P":
                    field = new PropertyField(type);
                    break;
                default:
                    throw new InvalidInputException(); 
            }
            System.out.println(field);
            fields.add(field);
        }
            int noOfPlayers = sc.nextInt();
            System.out.println("number of players: " + noOfPlayers);
            Player player;
            for (int i=0;i<noOfPlayers;i++){
                String strategy = sc.next();
                switch(strategy){
                    case "GP":
                       player = new GreedyPlayer(sc.next());
                       break;
                    case "CP":
                        player = new CarefulPlayer(sc.next());
                        break;
                    case "TP":
                        player = new TacticalPlayer(sc.next());
                        break;
                    default:
                        throw new InvalidInputException();
                }
                //System.out.println(player);
                players.add(player);
            }
            //System.out.println(players);
            //int bank_amount = 0;
            
            int noOfdiceRolls = sc.nextInt();
            //System.out.println(diceRolls);
            //int i = 0
            for (int i=0;i<noOfdiceRolls;++i){
                int roll = sc.nextInt();
                while(roll > noOfFields){
                    roll -= noOfFields;
                }
                diceRolls.add(roll);
            }
            System.out.println(" DiceRolls " + diceRolls);
            int noOfRounds = diceRolls.size() / noOfPlayers;
            int round = 0;
            for (int i=0;i < noOfRounds; ++i){
                System.out.println(" Round no: " + (i+1) + " ");
                for (Player p : players){
                    int turn = diceRolls.get(round);
                    //System.out.println(p.getName() + " " + "Turn: " + turn + " round: " + (i+1));
                    if ((p.getPos() + turn) > noOfFields ){
                        p.setPos((p.getPos() + turn) - noOfFields );
                    }else{
                        p.setPos(p.getPos() + turn);
                    }
                    Field curr_field = fields.get(p.getPos()-1);
                    System.out.println("Player " + p.getName() + " has stepped over : ("+ curr_field + ")");
                    curr_field.stepOver(p);
                    //System.out.println("Turn no: " + round + p.getName());
                    round++;
                    System.out.println(p);
                }
                System.out.println(" End of Round no: " + (i+1) + " ");

    }
}
}
