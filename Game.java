/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author foldesdaniel
 */
public class Game extends JComponent implements ActionListener {
    
    private final String name1, name2;
    private final Color color1, color2;
    private final JLabel time;
    private long startTime;
    private Timer timer;
    private final Canvas canvas;
    private final Vehicle player1, player2;
    private final int width, height;
    
    public Game(String name1, String name2, Color color1, Color color2, int width, int height, GUI frame, Database db) {
        super();
        this.name1 = name1;
        this.name2 = name2;
        this.color1 = color1;
        this.color2 = color2;
        this.time = new JLabel("0");
        int size = 48;
        this.player1 = new Vehicle(color1, 100, 60, size, size, 'r', name1, "vehicle_2_r.png");
        this.player2 = new Vehicle(color2, width - 100 - size, height - 60 - size, size, size, 'l', name2, "vehicle_1_l.png");
        this.canvas = new Canvas(time, player1, player2, frame, db);
        this.width = width;
        this.height = height;
        
        setLayout(new GridLayout());
        
        this.add(canvas);
    }
    
    /*
    *-------------------
    * Starting the timer
    *-------------------
    */
    public void run() {
        startTime = System.currentTimeMillis();
        timer = new Timer(1, this);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        long now = System.currentTimeMillis();
        long elapsed = now - startTime;
        if(canvas.getEnd()) timer.stop();
        this.time.setText(String.valueOf(elapsed));
    }
}
