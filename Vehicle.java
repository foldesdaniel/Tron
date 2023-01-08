/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;
import java.net.*;  

/**
 *
 * @author foldesdaniel
 */
public class Vehicle {
    
    private final Color color;
    private int x, y, width, height;
    private char direction;
    private ArrayList<Rectangle> rect; //Storing for hitbox
    private int index;
    private final String name;
    private BufferedImage image;
    
    public Vehicle(Color color, int x, int y, int width, int height, char direction, String name, String imageFileName) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.index = 0;
        this.rect = new ArrayList<>();
        readImage(imageFileName);
        int x1 = x + width / 3;
        int y1 = y + height / 3;
        int x2 = width / 3;
        int y2 = height / 3;
        this.rect.add(new Rectangle(x1, y1, x2, y2));
        this.name = name;
    }
    
    public void setImage(String imageFileName) {
        readImage(imageFileName);
    }
    
    public BufferedImage getImage() {
        return this.image;
    }
    
    private void readImage(String imageFileName) {
        URL resource = getClass().getResource("Images/"+imageFileName);
        try {
            this.image = ImageIO.read(resource);
        } catch (IOException e) {
            System.out.println("Hiba");
        }
    }
    
    public String getName() {
        return name;
    }
    
    public void addRect(Rectangle rect) {
        this.rect.add(rect);
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
    
    public ArrayList<Rectangle> getRect() {
        return rect;
    }
    
    public char getDirection() {
        return direction;
    }
    
    public void setDirection(char direction) {
        this.direction = direction;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getY() {
        return y;
    }
    
    public int getX() {
        return x;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void stepX(int x) {
        this.x += x;
    }
    
    public void stepY(int y) {
        this.y += y;
    }
}
