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
public abstract class Animal {
    private String type;
    private String name;
    private int weight;
    private int NoOfMeals;
    private ArrayList<Integer> meals;
    protected int Malnourishment_Thareshold;
    
    public Animal(String type, String name, int weight, int nofmeals, ArrayList<Integer> meals, int mal){
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.NoOfMeals = nofmeals;
        this.meals = new ArrayList<>(meals);
        this.Malnourishment_Thareshold = mal;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getNoOfMeals() {
        return NoOfMeals;
    }

    public ArrayList<Integer> getMeals() {
        return meals;
    }
    public boolean isMalnourished(){
        return weight < Malnourishment_Thareshold;
    }
    
    public boolean consumedMore(){
        int sum = 0;
        sum = meals.stream().map(meal -> meal).reduce(sum, Integer::sum);
        return sum > 100;
    }
    @Override
    public String toString(){
        return "Animal Type: " + type + " Name: " + name + " weight: " + weight + " has " + NoOfMeals + " meals ";
    }
}
