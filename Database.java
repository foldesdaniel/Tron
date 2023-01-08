/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author foldesdaniel
 */
public class Database {

    private final String tableName = "myTable";
    private Connection conn;
    private final String URL = "jdbc:mysql://localhost:3306/top10" + "?serverTimezone=UTC&user=dani&password=asd123";

    public Database() {
        this.conn = null;
        try {
            conn = DriverManager.getConnection(URL);

        } catch (Exception e) {
            System.out.println("No connection...");
        }
    }

    public JTable getTop10() {
        String d[][] = new String[10][2];
        int i = 0;
        try ( Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " ORDER BY score DESC");
            while (rs.next() && i < 10) {
                String name = rs.getString("player_name");
                int score = rs.getInt("score");
                d[i][0] = name;
                d[i][1] = Integer.toString(score);
                ++i;
            }
        } catch (Exception e) {
            System.out.println("getTop10 error");
            System.out.println(e);
        }
        String ret[][] = new String[i+1][2];
        String column[]={"name", "score"}; 
        for(int j = 0; j < i; ++j) {
            ret[j][0] = d[j][0];
            ret[j][1] = d[j][1];
        }
        
        return new JTable(ret, column);
    }
    
    public void insertPlayer(String name) {
        try ( Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE player_name = '" + name + "';");
            int value = 1;
            if(rs.next()) {
                value = rs.getInt("score");
            }
        stmt.execute("INSERT INTO " + tableName + " (player_name, score) VALUES ('" + name + "', '" + Integer.toString(value) + "') ON DUPLICATE KEY UPDATE score = '" + ++value + "';");
        } catch (Exception e) {
            System.out.println("insertPlayer error");
            System.out.println(e);
        }
    }
}
