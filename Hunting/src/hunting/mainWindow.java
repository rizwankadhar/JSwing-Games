/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;


/**
 *
 * @author EliteBook
 */
public class mainWindow extends BaseWindow {
    private ArrayList<GameWindow> windows;
    
   public mainWindow(){
       windows = new ArrayList<>();
       
       JButton three = new JButton();
       three.setText("3x3");
       three.addActionListener(frameChooser(3));
       
       JButton five = new JButton();
       five.setText("5x5");
       five.addActionListener(frameChooser(5));
       
       JButton seven = new JButton();
       seven.setText("7x7");
       seven.addActionListener(frameChooser(7));
       
       getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
       getContentPane().add(three);
       getContentPane().add(five);
       getContentPane().add(seven);
       
   }
   private ActionListener frameChooser(int size){
       return new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               GameWindow window = new GameWindow(size, mainWindow.this);
               window.setVisible(true);
               windows.add(window);
           }
       };
   }
   
   public void addWindow(GameWindow window){
       windows.add(window);
   }
   
   public void removeWindow(GameWindow window){
       windows.remove(window);
   }
}
