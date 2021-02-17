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
public abstract class Field {
    protected Player owner;
    private String type;
    protected String status;
    public Field(String type){
        this.owner = null;
        this.type = type;
        this.status = "free";
        
    }
    public Player getOwner(){
        return owner;
    }
    public int getCost(){
        return 0;
    }
    public void setOwner(Player own){
        owner = own;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String stat){
        status = stat;
    }
    /*public void changeOwnerShipTo(Player new_owner){
        if (this.status.equals("free")){
            this.setStatus("owned");
            this.setOwner(new_owner);
            new_owner.setMoney(-1000);
            ///this.setCost(4000);
        }else if (this.status.equals("owned")){
            
        }
    }
    */
    public void stepOver(Player p){
        
    }
    public void addToBank(){
        
    }
    
    @Override
    public String toString(){
        return "Field type " + type +  " owned by " + owner + " has Status :" + status; 
    }
    
}
