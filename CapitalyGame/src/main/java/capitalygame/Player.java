/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitalygame;

import java.util.ArrayList;
/**
 *
 * @author Rizwan Hussain
 */
public class Player {
    private String name;
    private String strategy;
    private int money;
    private ArrayList<Field> assets;
    protected boolean tactics;
    private int position;
    public Player(){
        
    }
    public Player(String name, String strategy, int money){
        this.name = name;
        this.strategy = strategy;
        this.money = money;
        this.position = 0;
        this.assets = new ArrayList<>();
    }
    
    public String getName(){
        return name;
    }
    public int getMoney(){
        return money;
    }
    public void setMoney(int amount){
        money += amount;
    }
    
    public void addAsset(Field f){
        assets.add(f);
    }
    public void removeAllAssets(){
        for (Field f : assets){
            f.setStatus("free");
            f.setOwner(null);
        }
        assets.clear();
    }
    public boolean getTactics(){
        return tactics;
    }
    public void setTactics(){
        if (tactics){
            tactics = false;
        }else {
            tactics = true;
        }
    }
    public void setPos(int pos){
        this.position = pos;
    }
    public int getPos(){
        return position;
    }

    public int noOfAssets(){
        return assets.size();
    }
    public boolean hasEnoughMoney(Field f){
        return this.getMoney() >= f.getCost();
    }
    
    public boolean willBuy(Field f){
        return true;
    }
    public void buyOrBuild(Field f){
        if (f.getStatus().equals("free")){
            //System.out.println(" The property is free :" + f);
            if (this.willBuy(f)){
                //this.setMoney(-f.getCost());
                this.setMoney(-1000);
                this.addAsset(f);
                f.setOwner(this);
                f.setStatus("owned");
                
            }
        }
        else if (f.getStatus().equals("built")){
            if (this.getMoney() >= 2000){
                this.setMoney(-2000);
                f.getOwner().setMoney(2000);
            }else {
                this.loseTo(f.getOwner());
            }
        }else if (f.getStatus().equals("owned")){
            if (f.getOwner().getName().equals(name)){
                ///System.out.println("The property is owned by same person");
                if (this.willBuy(f)){
                    if (this.getMoney() >= 4000){
                    f.setStatus("built");
                    this.setMoney(-4000);
                }
                }

            }else{
                if (this.getMoney() >= 500){
                    this.setMoney(-500);
                    f.getOwner().setMoney(500);
                }   else   {
                    this.loseTo(f.getOwner());
            }
            }
        }
    }
    
    public void payPenalty(Field f){
        if (this.getMoney() >= f.getCost()){
            System.out.println(name + " has to pay" + f.getCost());
            this.setMoney(-f.getCost());
            f.addToBank();
        }else {
            this.removeAllAssets();
        }
    }
    
    public void getReward(Field f){
        this.setMoney(f.getCost());
    }
    
    public void loseTo(Player winner){
        winner.setMoney(this.getMoney());
        /*for (Field f : this.assets){
            f.setStatus("free");
            f.setOwner(null);
        }*/
        this.removeAllAssets();
    }
    
    @Override
    public String toString(){
        return "Player " + name + " with Strategy: " + strategy + " has " + money + " and "+ noOfAssets() + " properties.";
    }
    
    
    
    
}
