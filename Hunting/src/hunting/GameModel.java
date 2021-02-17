/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunting;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author EliteBook
 */
public class GameModel {
    private int size;
    private Player[][] table;
    private Player currentPlayer;
    private int stepsCount;
    private Point fugitivePosition;
    private ArrayList<Point> hunterPositions;
    private boolean isSelected;
    private Point selectedPosition;
    
    public GameModel(int size){
        this.size = size;
        currentPlayer = Player.FUGITIVE;
        stepsCount = 0;
        fugitivePosition = new Point(size/2, size/2);
        hunterPositions = new ArrayList<>(Arrays.asList(new Point(0,0), new Point(0,size-1), new Point(size-1, 0), new Point(size-1, size-1)));
  
        table = new Player[size][size];
        
        for (int i=0; i< size; ++i){
            for (int j=0;j<size;++j){
                table[i][j] = Player.NULL;
            }
        }
        table[fugitivePosition.x][fugitivePosition.y] = Player.FUGITIVE;
        for (Point p : hunterPositions){
            table[p.x][p.y] = Player.HUNTER;
        }
        
    }
    
    public void step(int row, int col){
  
        if (currentPlayer == Player.FUGITIVE){
            if (isAdjacent(fugitivePosition, new Point(row, col)) && table[row][col] == Player.NULL){
                System.out.println("Yes they are adjacent");
                table[fugitivePosition.x][fugitivePosition.y] = Player.NULL;
                fugitivePosition.move(row, col);
                table[row][col] = currentPlayer;
                currentPlayer = Player.HUNTER;
            }
        }else{
            if (isSelected){
                if (isAdjacent(selectedPosition, new Point(row, col)) && table[row][col] == Player.NULL){
                    System.out.println("Yes they are adjacent");
                    table[(int)selectedPosition.getX()][(int)selectedPosition.getY()] = Player.NULL;
                    table[row][col] = currentPlayer;
                    currentPlayer = Player.FUGITIVE;
                    stepsCount++;
                    isSelected = false;

                }else {
                    System.out.println("NO they are not adjacent!");
                }
            }else {
                if (table[row][col] == Player.HUNTER){
                    isSelected = true;
                    selectedPosition = new Point(row, col);
                    //table[row][col] = Player.NULL;
                    System.out.println("Selected");
                }else {
                    System.out.println("empty box clicked");
                }
                
            }
            
        }
    }
    public Player findWinner(){
        hunterPositions.clear();
        for (int i=0;i<size;++i){
            for (int j=0;j<size;++j){
                if (table[i][j] == Player.HUNTER){
                    hunterPositions.add(new Point(i,j));
                }
            }
        }
        if (stepsCount <= 4*size){
            boolean flag = true;
            for (int i=0;i<size;++i){
                for (int j=0;j<size;++j){
                    Point p = new Point(i, j);
                    if (isAdjacent(fugitivePosition,p )){
                        if (!hunterPositions.contains(p)){
                            flag = false;
                        }
                    }
                }
            }

            return flag ? Player.HUNTER : Player.NULL;
            
        }
        return Player.FUGITIVE;
    }
    
    private boolean isAdjacent(Point p, Point q){
        if (p.equals(q)){
            return false;
        }
        if (p.x != q.x && p.y != q.y){
            return false;
        }
        return close(p.x,q.x) || close(p.y,q.y);
    }
    private boolean close(int i, int j){
        return Math.abs(i-j) == 1;
    }
    
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    
    public int getSize(){
        return size;
    }
    /*
    public Point getFugitivePosition(){
        return fugitivePosition;
    }
    public ArrayList<Point> getHunterPositions(){
        return hunterPositions;
    }
    public Point getSelectedPosition(){
        return new Point(selectedPosition);
    } 
    
    
    */
    public int stepsCount(){
        return stepsCount;
    }
    public Player get(int x, int y){
        return table[x][y];
    }
    
}
