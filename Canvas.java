/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author foldesdaniel
 */
public class Canvas extends JPanel implements KeyListener, ActionListener {

    private final JLabel time;
    private final Vehicle player1, player2;
    private Timer timer;
    private final int speed;
    private final GUI frame;
    private boolean end;
    private final Database db;
    private long startTime1; //To calculate player1 delay
    private long startTime2; //To calculate player2 delay
    private ArrayList<KeyEvent> keys = new ArrayList(); //Storing pressed keys to work sync

    public Canvas(JLabel time, Vehicle player2, Vehicle player1, GUI frame, Database db) {
        super();
        this.time = time;
        this.player1 = player1;
        this.player2 = player2;
        this.speed = 4;
        this.frame = frame;
        this.end = false;
        this.db = db;
        setBackground(Color.BLACK);

        setFocusable(true);
        requestFocusInWindow();
        
        addListener();

        run();
    }
    
    private void addListener() {
        addKeyListener(this);
    }

    public boolean getEnd() {
        return end;
    }

    /*
    *------------------
    * Starting the game
    *------------------
     */
    private void run() {
        timer = new Timer(speed, this);
        timer.start();
        startTime1 = System.currentTimeMillis();
        startTime2 = System.currentTimeMillis();
    }

    /*
    *----------------------------------------
    * Checking collision
    * @param player player to check collision
    * @param rect to check collision
    *----------------------------------------
     */
    private boolean isCollision(Vehicle player, Rectangle rect) {
        if (player.getX() + player.getWidth() >= rect.x1 && player.getX() <= rect.x1 + rect.x2 && player.getY() + player.getHeight() >= rect.y1 && player.getY() <= rect.y1 + rect.y2) {
            return true;
        }
        else if (player.getX() < 0 || player.getX() + player.getWidth() > 1400 || player.getY() < 0 || player.getY() + player.getHeight() > 875) {
            return true;
        }
        return false;
    }
    
    /*
    *----------------------------------------
    * Checking collision
    * @param player1 player to check collision
    * @param player2 player to check collision
    *----------------------------------------
     */
    private boolean isCollision(Vehicle player1, Vehicle player2) {
        if (player1.getX() + player1.getWidth() >= player2.getX() && player1.getX() <= player2.getX() + player2.getWidth() && player1.getY() + player1.getHeight() >= player2.getY() && player1.getY() <= player2.getY() + player2.getHeight()) {
            return true;
        }
        return false;
    }

    /*
    *----------------------------------
    * Win logic
    * @param player megnyerte a játékot
    *----------------------------------
     */
    private void win(Vehicle player) {
        this.timer.stop();
        end = true;
        JOptionPane.showMessageDialog(this, player.getName() + " won!", "End", JOptionPane.INFORMATION_MESSAGE);
        db.insertPlayer(player.getName());
        frame.displayMenu();
    }
    
    /*
    *----------------------------------
    * Draw logic
    * @param player megnyerte a játékot
    *----------------------------------
     */
    private void draw() {
        this.timer.stop();
        end = true;
        JOptionPane.showMessageDialog(this, "Draw!", "End", JOptionPane.INFORMATION_MESSAGE);
        frame.displayMenu();
    }

    /*
    *--------------------------------
    * Checking all possible collision
    *--------------------------------
     */
    private void checkCollision() {
        int i = 0; //Current and current-1 index rect does not count
        if(isCollision(player1, player2)) {
            if(player1.getDirection() == 'u' && player2.getDirection() == 'd') draw();
            else if(player1.getDirection() == 'd' && player2.getDirection() == 'u') draw();
            else if(player1.getDirection() == 'l' && player2.getDirection() == 'r') draw();
            else if(player1.getDirection() == 'r' && player2.getDirection() == 'l') draw();
        }
        for (Rectangle r : player1.getRect()) {
            if (isCollision(player2, r) && !end) {
                win(player1);
            }
            if (i != player1.getIndex() && i != player1.getIndex() - 1 && isCollision(player1, r) && !end) {
                win(player2);
            }
            ++i;
        }
        i = 0;
        for (Rectangle r : player2.getRect()) {
            if (isCollision(player1, r) && !end) {
                win(player2);
            }
            if (i != player2.getIndex() && i != player2.getIndex() - 1 && isCollision(player2, r) && !end) {
                win(player1);
            }
            ++i;
        }
    }

    /*
    *----------------------------------------------
    * Increasing the currently indexed rectangle
    * @param player 's rectangle will be increased
    *----------------------------------------------
     */
    private void stepRect(Vehicle player) {
        switch (player.getDirection()) {
            case 'u':
                --player.getRect().get(player.getIndex()).y1;
                ++player.getRect().get(player.getIndex()).y2;
                break;
            case 'd':
                ++player.getRect().get(player.getIndex()).y2;
                break;
            case 'l':
                --player.getRect().get(player.getIndex()).x1;
                ++player.getRect().get(player.getIndex()).x2;
                break;
            case 'r':
                ++player.getRect().get(player.getIndex()).x2;
                break;
        }
    }

    /*
    *----------------------------
    * Painting the graphics
    *----------------------------
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //PLAYER 1
        gg.setColor(player1.getColor());
        //line
        ArrayList<Rectangle> rect = player1.getRect();
        for (Rectangle r : rect) {
            gg.fillRect(r.x1, r.y1, r.x2, r.y2);
        }
        gg.drawImage(player1.getImage(), player1.getX(), player1.getY(), this);

        //PLAYER 2
        gg.setColor(player2.getColor());
        //line
        rect = player2.getRect();
        for (Rectangle r : rect) {
            gg.fillRect(r.x1, r.y1, r.x2, r.y2);
        }
        gg.drawImage(player2.getImage(), player2.getX(), player2.getY(), this);

        gg.setColor(Color.ORANGE);

        //TIMER
        g.setFont(new Font("TimesRoman", Font.PLAIN, 28));
        int s = Integer.parseInt(time.getText());
        s /= 1000;
        gg.drawString(Integer.toString(s), 10, 40);

        requestFocusInWindow(); //Pressing button will work with this

        movePlayers();

        repaint();
    }

    /*
    *------------------------------
    * Stepping the player
    * @param player step
    *------------------------------
     */
    private void step(Vehicle player) {
        switch (player.getDirection()) {
            case 'u':
                player.stepY(-1);
                break;
            case 'd':
                player.stepY(1);
                break;
            case 'l':
                player.stepX(-1);
                break;
            case 'r':
                player.stepX(1);
                break;
        }
    }

    /*
    *----------------------------------------
    * Adding new rectangle to the player
    * @param direction does direction change
    * @param player if yes, player is moving
    *----------------------------------------
     */
    private void addRect(char direction, Vehicle player) {
        if (player.getDirection() != direction) {
            player.setIndex(player.getIndex() + 1);
            int x1 = player.getX() + player.getWidth() / 3;
            int y1 = player.getY() + player.getHeight() / 3;
            int x2 = player.getWidth() / 3;
            int y2 = player.getHeight() / 3;
            player.addRect(new Rectangle(x1, y1, x2, y2));
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        step(player1);
        step(player2);

        stepRect(player1);
        stepRect(player2);

        checkCollision();
    }

    /*
    *----------------------------------------------------------
    * This is needed to be able to press multiple keys at once
    *----------------------------------------------------------
     */
    private void movePlayers() {
        for (KeyEvent e : keys) {
            long now = System.currentTimeMillis();
            long elapsed1 = now - startTime1;
            if (elapsed1 >= 150) {
                //PLAYER 1
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    addRect('u', player1);
                    if (player1.getDirection() != 'd') {
                        player1.setDirection('u');
                        startTime1 = System.currentTimeMillis();
                        player1.setImage("vehicle_1_u.png");
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    addRect('d', player1);
                    if (player1.getDirection() != 'u') {
                        player1.setDirection('d');
                        startTime1 = System.currentTimeMillis();
                        player1.setImage("vehicle_1_d.png");
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    addRect('l', player1);
                    if (player1.getDirection() != 'r') {
                        player1.setDirection('l');
                        startTime1 = System.currentTimeMillis();
                        player1.setImage("vehicle_1_l.png");
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    addRect('r', player1);
                    if (player1.getDirection() != 'l') {
                        player1.setDirection('r');
                        startTime1 = System.currentTimeMillis();
                        player1.setImage("vehicle_1_r.png");
                    }
                }
            }
            now = System.currentTimeMillis();
            long elapsed2 = now - startTime2;
            if (elapsed2 >= 150) {
                //PLAYER 2
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    addRect('u', player2);
                    if (player2.getDirection() != 'd') {
                        player2.setDirection('u');
                        startTime2 = System.currentTimeMillis();
                        player2.setImage("vehicle_2_u.png");
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    addRect('d', player2);
                    if (player2.getDirection() != 'u') {
                        player2.setDirection('d');
                        startTime2 = System.currentTimeMillis();
                        player2.setImage("vehicle_2_d.png");
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    addRect('l', player2);
                    if (player2.getDirection() != 'r') {
                        player2.setDirection('l');
                        startTime2 = System.currentTimeMillis();
                        player2.setImage("vehicle_2_l.png");
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    addRect('r', player2);
                    if (player2.getDirection() != 'l') {
                        player2.setDirection('r');
                        startTime2 = System.currentTimeMillis();
                        player2.setImage("vehicle_2_r.png");
                    }
                }
            }
        }

        keys = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.add(e);
    }
}
