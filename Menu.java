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
public class Menu extends JComponent {

    private final JTextField name1, name2;
    private final JComboBox color1, color2;
    private final JButton start, top10;
    
    public Menu(GUI frame, Database db) {
        super();
        this.name1 = new JTextField(16);
        this.name2 = new JTextField(16);
        
        String colors[]={"Blue","Green","Orange","Yellow","Red","White","Pink"};
        this.color1 = new JComboBox(colors);
        this.color2 = new JComboBox(colors);
        
        this.start = new StartBtn("START", color1, color2, name1, name2, frame);
        this.top10 = new Top10Btn("TOP 10", db);
        
        style();
        makeComponents();
    }
    
    /*
    * Styling
    */
    private void style() {
        //START BUTTON
        this.start.setBorderPainted(false);
        this.start.setBackground(Color.BLACK);
        this.start.setForeground(Color.WHITE);
        this.start.setOpaque(true);
        this.start.setFont(new Font("Arial", Font.PLAIN, 28));
        this.start.setPreferredSize(new Dimension(150, 60));
        
        //TOP10 BUTTON
        this.top10.setBorderPainted(false);
        this.top10.setBackground(Color.BLACK);
        this.top10.setForeground(Color.WHITE);
        this.top10.setOpaque(true);
        this.top10.setFont(new Font("Arial", Font.PLAIN, 22));
        this.top10.setPreferredSize(new Dimension(150, 60));
        
        //JCOMBOBOX
        color1.setFont(new Font("Arial", Font.PLAIN, 15));
        color2.setFont(new Font("Arial", Font.PLAIN, 15));
    }
    
    /*
    * Making menu components and adding it to itself
    */
    private void makeComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;  
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel l = new JLabel("First player name ");
        l.setFont(new Font("Arial", Font.PLAIN, 12));
        l.setForeground(Color.WHITE);
        this.add(l, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(name1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        l = new JLabel("Second player name ");
        l.setForeground(Color.WHITE);
        this.add(l, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(name2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        l = new JLabel("First player color ");
        l.setForeground(Color.WHITE);
        this.add(l, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(color1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        l = new JLabel("Second player color ");
        l.setForeground(Color.WHITE);
        this.add(l, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(color2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.add(top10, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        this.add(start, gbc);
    }
}
