/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author VASPAR
 */
public class DBConnection {
    public Connection getConnection() throws ClassNotFoundException, SQLException{       
           //Class.forName("com.mysql.jdbc.Driver");
           return DriverManager.getConnection("jdbc:mysql://sgp-2.ckltggvsspz7.ap-south-1.rds.amazonaws.com:3306/","admin","12345678");
     }
    
}