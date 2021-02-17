package fives;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends BaseWindow {
    private JButton[][] buttons;
    private final int size;
    private final Model model;
    private final JLabel label;
    private final MainWindow mainWindow;
    
    private ArrayList<Point> xPositions;
    private ArrayList<Point> oPositions;
    
    private Random random = new Random();
    
    
    public Window(int size, MainWindow mainWindow) {
        
        xPositions = new ArrayList<>();
        oPositions = new ArrayList<>();
        this.size = size;
        this.mainWindow = mainWindow;
        mainWindow.getWindowList().add(this);
        model = new Model(size);
        buttons = new JButton[model.getSize()][model.getSize()];

        JPanel top = new JPanel();
        
        label = new JLabel();
        updateLabelText();
        
        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.addActionListener(e -> newGame());
        
        top.add(label);
        top.add(newGameButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(mainPanel, i, j);
            }
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, final int i,
            final int j) {
        final JButton button = new JButton();

        button.addActionListener(e -> {
            Player newValue = model.step(i, j);
            button.setText(newValue.name());
            
            if (newValue.name() == Player.X.name()){
                xPositions.add(new Point(i,j));
                System.out.println(xPositions);
                if (checkAdjacentSigns(xPositions)==3){
                    System.out.println("3 are adjacent");
                    removeSelection(xPositions, 1);
                }else if (checkAdjacentSigns(xPositions)==4){
                    removeSelection(xPositions, 2);
                    System.out.println("4 are adjacent");
                }
            }else if (newValue.name() == Player.O.name()){
                oPositions.add(new Point(i,j));
                if (checkAdjacentSigns(oPositions)==3){
                    removeSelection(oPositions, 1);
                }else if (checkAdjacentSigns(oPositions)==3){
                    removeSelection(oPositions, 2);
                }
            }
            
            
            updateLabelText();
            refresh();
            
            Player winner = model.findWinner();
            if (winner != Player.NOBODY) {
                showGameOverMessage(winner);
            }
        });
        buttons[i][j] = button;
        panel.add(button);
    }
    public void refresh() {
        for (int i = 0; i < model.getSize(); ++i) {
            for (int j = 0; j < model.getSize(); ++j) {
                Player player = model.get(i, j);
                JButton button = buttons[i][j];
                if (player.name() != Player.NOBODY.name() ) {
                    button.setText(player.name());
                } else {
                    button.setText("");
                }
            }
        }
    }
    
    private int checkAdjacentSigns(ArrayList<Point> positions){
        int count = 0;
        int i = 0;
        while (i<positions.size()){
            if (checkAdjacency(positions))
        }
//        for (Point p : positions){
//            for (int i = 0;i<positions.size();++i){
//                Point q = positions.get(i);
//                if (!p.equals(q)){
//                    if (checkAdjacency(p,q)){
//                        System.out.println(p.toString() + q.toString() + "are adjacent");
//                        count++;
//                    }
//                }
//            }
//        }
        System.out.println("Count is:" + count);
        return count;
    }
    
    private void sort(ArrayList<Point> positions){
        for (int i =0;i<positions.size();++i){
            
        }
    }
    private void removeSelection(ArrayList<Point> positions, int n){
        Collections.shuffle(positions);
        for (int i=0 ;i<n ;++i){
            Point remPoint = positions.remove(positions.size() - 1);
            model.setPlayer(remPoint.x, remPoint.y);
        }
//        if (n == 3){
//            
//        }else if (n==4){
//            Point remPoint = positions.remove(positions.size() - 1);
//            model.setPlayer(remPoint.x, remPoint.y);
//            Point remPoint2 = positions.remove(positions.size() - 1);
//            model.setPlayer(remPoint2.x, remPoint2.y);
//        }
    }
    
    private boolean checkAdjacency(Point a, Point b){
        if (a.x != b.x && !adjacent(a.x, b.x)){
            return false;
        }
        if (a.y != b.y && !adjacent(a.y, b.y)){
            return false;
        }
        return true;
    }
    
    private boolean adjacent(int x, int y){
        boolean result;
        if (x > y){
            result = ((x-y)==1);
        }else {
            result = ((y-x)==1);
        }
        return result;
    }

    private void showGameOverMessage(Player winner) {
        JOptionPane.showMessageDialog(this,
                "Game is over. Winner: " + winner.name());
        newGame();
    }
    
    
    
    private void newGame() {
        Window newWindow = new Window(size, mainWindow);
        newWindow.setVisible(true);
        
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    
    private void updateLabelText() {
        label.setText("Current player: "
                + model.getActualPlayer().name());
    }

    @Override
    protected void doUponExit() {
        super.doUponExit();
        mainWindow.getWindowList().remove(this);
    }
    
}
