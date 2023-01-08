/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author foldesdaniel
 */
public class GUI extends JFrame {
    
    private Menu menu;
    private Game game;
    private final Database db;
    
    public GUI() {
        super("Tron");
        this.db = new Database();
        setPreferredSize(new Dimension(1200, 800));
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setMinimumSize(new Dimension(450, 550));
        setFocusable(true);
        
        displayMenu(); //START
    }
    
    /*
    *----------------
    * Displaying menu
    *----------------
    */
    public final void displayMenu() {
        clearWindow();
        setResizable(true);
        setPreferredSize(new Dimension(450, 550));
        
        this.menu = new Menu(this, this.db);
        this.add(menu);
        
        pack();
        setVisible(true);
    }
    
    /*
    *----------------
    * Displaying game
    *----------------
    */
    public final void displayGame(String name1, String name2, Color color1, Color color2) {
        clearWindow();
        setResizable(false);
        setPreferredSize(new Dimension(1400, 900));
        pack(); //Frissíti az ablakméretet
        
        int width = getBounds().width;
        int height = getBounds().height;
        
        this.game = new Game(name1, name2, color1, color2, width, height, this, this.db);
        this.add(game);
        game.run();
        
        pack();
        setVisible(true);
    }
    
    /*
    *--------------------------------
    * Setting window default settings
    *--------------------------------
    */
    private final void clearWindow() {
        this.menu = null;
        this.game = null;
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setForeground(Color.WHITE);
        getContentPane().removeAll();
        repaint();
    }
}
