/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animalfarmown;

import java.io.FileNotFoundException;

/**
 *
 * @author EliteBook
 */
public class Main {
    
    public static void main(String[] args){
        DataInput data = new DataInput();
        try {
            data.read("input.txt");
        }catch (FileNotFoundException e){
            System.err.println("File not Found!");
        }catch (InvalidInputException e){
            System.out.println("Invalid Input!");
        }
        System.out.println("Following animals are Malnourished:");
        System.out.println(data.reportMalnourished());
        System.out.println("Following animals have consumed more:");
        System.out.println(data.excessConsumers());
    }
}
