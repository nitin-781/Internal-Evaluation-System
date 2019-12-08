/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluator;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.CustomPasswordField;

/**
 * FXML Controller class
 *
 * @author VASPAR
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private Label labelOldPassword;
    @FXML
    private Label labelNewPassword;
    @FXML
    private CustomPasswordField oldPasswordField;
    @FXML
    private CustomPasswordField newPasswordField;
    @FXML
    private Button btnOK;

    Connection con = null;
    Statement stmt = null;
    PreparedStatement prstmt = null;
    ResultSet rs = null;
    
    Alert a=new Alert(Alert.AlertType.NONE);
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connectToDB();
    }

    public void connectToDB() {
        /*DB*/
        //assert rptTable != null : "fx:id=\"tableview\" was not injected: check your FXML file 'Evaluator.fxml'.";

        DBConnection objDbClass = new DBConnection();
        try {
            con = objDbClass.getConnection();
            System.out.println("Connection Done");
        } catch (ClassNotFoundException | SQLException ce) {
            System.out.println("Error");
            System.out.println(ce.getMessage());
        }
    }

    @FXML
    private void onbtnOK(ActionEvent event) {
        try {
            String sql = "UPDATE `CSPIT`.`CE_EV_SIGN` SET `PASSWORD`=? WHERE `EMAIL`=? AND `PASSWORD`=?";
            prstmt=con.prepareCall(sql);
            prstmt.setString(1, getMd5(newPasswordField.getText()));
            prstmt.setString(2, new EvaluatorController().getEmail());
            prstmt.setString(3, getMd5(oldPasswordField.getText()));
            int row=prstmt.executeUpdate();
            System.out.println(row);
            if(row==0){
                
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Wrong Credentials");
                a.setTitle("Error");
                a.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void onNewPassKeyPressed(KeyEvent event) {
        switch(event.getCode()){
            case UP:
                oldPasswordField.requestFocus();
                oldPasswordField.selectAll();
        }
    }
    
    public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}
