/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qkboard;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author EliteBook
 */
public class KeyBoard {
    private JFrame frame;
    private JMenuBar menuBar;
    private JPanel letters;
    private JPanel keys;
    private JPanel displayPanel;
    private JTextField display;
    
    public KeyBoard(int fieldWidth){
        frame = new JFrame("Qwerty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu calcMenu = new JMenu("QWERTY");
        menuBar.add(calcMenu);
        JMenuItem exit = new JMenuItem("Exit");
        calcMenu.add(exit);
        exit.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e){
             System.exit(0);
         }
        });
        ArrayList<String> qwerty = new ArrayList<>(Arrays.asList("Q", "W", "E", "R", "T", "Y"));
        letters = new JPanel();
        letters.setLayout(new GridLayout(1,6));
        for (String key : qwerty){
            JButton button = new JButton(key);
            button.addActionListener(new KeyActionListener(key));
            letters.add(button);
        }
        
        keys = new JPanel();
        keys.setLayout(new GridLayout(2,1));
        JButton backspace = new JButton("Backspace");
        backspace.addActionListener(new BackSpaceActionListener());

        keys.add(backspace);
        
        JButton clear = new JButton("CLR");
        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                display.setText("");
            }
        });
        keys.add(clear);

        
       displayPanel = new JPanel();
        display = new JTextField(fieldWidth);
        displayPanel.add(display);
        frame.getContentPane().add(BorderLayout.NORTH, displayPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, letters);
        frame.getContentPane().add(BorderLayout.EAST, keys);
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
    class KeyActionListener implements ActionListener{
        private final String key;
        public KeyActionListener(String key){
            this.key = key;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            display.setText(display.getText() + key);
        }
        
    }
    
    class BackSpaceActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String text = display.getText();
            if (!text.isEmpty()){
                display.setText(text.substring(0, text.length()-1));

            }
        }
    }
}
