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
public class Top10Btn extends JButton implements ActionListener {
    
    private final Database db;

    public Top10Btn(String title, Database db) {
        super(title);
        this.db = db;
        //HOOVER EFFECT
        addMouseListener(new MouseAdapter() {
            Color color = getForeground();

            @Override
            public void mouseEntered(MouseEvent me) {
                color = getForeground();
                setForeground(Color.ORANGE); // change the color to green when mouse over a button
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
    *----------------------------
    * Reading file / apply data
    *----------------------------
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JOptionPane.showMessageDialog(this, db.getTop10(), "End", JOptionPane.INFORMATION_MESSAGE);
    }
}
