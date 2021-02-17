/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animalfarmown;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author EliteBook
 */
public class DataInput {
    private ArrayList<Animal> animals;
    
    public DataInput(){
        animals = new ArrayList<>();
    }
    
    public void read(String filename) throws FileNotFoundException, InvalidInputException{
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int noOfAnimals = sc.nextInt();
        Animal animal;
        ArrayList<Integer> mealsTaken;
        while (sc.hasNext()){
            mealsTaken = new ArrayList<>();
            String type = sc.next();
            String name = sc.next();
            int weight = sc.nextInt();
            int mealsNo = sc.nextInt();
            for (int i = 0; i < mealsNo; ++i){
                mealsTaken.add(sc.nextInt());
            }
            switch (type){
                case "C":
                    animal = new Cow(name, weight, mealsNo, mealsTaken);
                    break;
                case "H":
                    animal = new Horse(name, weight, mealsNo, mealsTaken);
                    break;
                case "G":
                    animal = new Goat(name, weight, mealsNo, mealsTaken);
                    break;
                case "E":
                    animal = new Emu(name, weight, mealsNo, mealsTaken);
                    break;
                default:
                    throw new InvalidInputException();
            }
            animals.add(animal);
        }        
    }
    
    public ArrayList<Animal> reportMalnourished(){
        ArrayList<Animal> mal = new ArrayList<>();
        for (Animal animal : animals){
            if (animal.isMalnourished()){
                mal.add(animal);
            }
        }
        return mal;
    }
    public ArrayList<Animal> excessConsumers(){
        ArrayList<Animal> excessors = new ArrayList<>();
        animals.stream().filter(animal -> (animal.consumedMore())).forEachOrdered(animal -> {
            excessors.add(animal);
        });
        return excessors;
    }
}
