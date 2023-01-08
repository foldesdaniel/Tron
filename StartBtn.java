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
public class StartBtn extends JButton implements ActionListener {
    
    private final JComboBox color1, color2;
    private final JTextField name1, name2;
    private final Color[] colors;
    private final GUI frame;
    
    public StartBtn(String title, JComboBox color1, JComboBox color2, JTextField name1, JTextField name2, GUI frame) {
        super(title);
        this.color1 = color1;
        this.color2 = color2;
        this.name1 = name1;
        this.name2 = name2;
        this.frame = frame;
        this.colors = new Color[] {Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.RED, Color.WHITE,Color.PINK};

        //HOOVER EFFECT
        addMouseListener(new MouseAdapter() {
         Color color = getForeground();
         @Override
         public void mouseEntered(MouseEvent me) {
            color = getForeground();
            setForeground(Color.GREEN);
         }
         @Override
         public void mouseExited(MouseEvent me) {
            setForeground(color);
         }
      });
        
        addListener();
    }
    
    private void addListener() {
        addActionListener(this);
    }
    
    /*
    *-------------------------------------------------------------------------------------
    * Starting the game if everything is ready (different name values and not empty values)
    *-------------------------------------------------------------------------------------
    */
    @Override
    public void actionPerformed(ActionEvent event) {
        String n1 = name1.getText();
        String n2 = name2.getText();
        Color c1 = getColor(color1);
        Color c2 = getColor(color2);
        boolean good = n1.length() != 0 && n2.length() != 0;
        good = good & !n1.equals(n2);
        good = good & c1 != c2;
        if(good) {
            this.frame.displayGame(n1, n2, c1, c2);
        }
        else {
            String output = "";
            //JOptionPane.showMessageDialog(this, "Különbözni kell a színeknek!", "TOP10", JOptionPane.WARNING_MESSAGE);
            if(n1.length() == 0 || n2.length() == 0) output+= "Meg kell adni a neveket!\n";
            else if(n1.equals(n2)) output+= "Különbözni kell a neveknek!\n";
            if(c1 == c2) output += "Különbözni kell a színeknek!\n";
            JOptionPane.showMessageDialog(this, output, "TOP10", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /*
    *--------------------------------------------------
    * Returning the selected color
    * @param box containing the currently selected color
    *--------------------------------------------------
    */
    private Color getColor(JComboBox box) {
        String colors[] = {"Blue","Green","Orange","Yellow","Red","White","Pink"};
        for(int i = 0; i < colors.length; ++i) {
            if(box.getSelectedItem().toString().equals(colors[i])) return this.colors[i];
        }
        return null;
    }
}
