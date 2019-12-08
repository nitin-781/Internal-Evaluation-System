/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author VASPAR
 */
public class SignInController implements Initializable {

    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPassword;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button btnSignIn;

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
    private void onSignIn(ActionEvent event) throws IOException {
        if(acExists() && isAdmin()){
            new AdminController().setEmail(emailField.getText());
            AdminDriver.copyStage.close();
            Stage stage = new Stage();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("Admin.css").toExternalForm();
            scene.getStylesheets().addAll(css);
            stage.getIcons().add(new Image("file:C:\\Users\\VASPAR ASPI\\Documents\\NetBeansProjects\\Admin\\dist\\Admin.png"));
            stage.setScene(scene);
            stage.setTitle("Admin");
            stage.setResizable(false);
            stage.setWidth(1320.0);
            stage.setHeight(879.0);
            stage.show();
        }
        else{
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Wrong Credentials");
                a.setTitle("Error");
                a.show();
        }
    }
    
    @FXML
    private void onPassKeyPressed(KeyEvent event) {
        switch(event.getCode()){
            case UP:
                emailField.requestFocus();
                emailField.selectAll();
        }
    }
    
    public boolean isAdmin(){
        return "admin@gmail.com".equals(emailField.getText());
    }
    public boolean acExists() {
        try {
            String sql = "SELECT `EMAIL`, `PASSWORD` FROM `CSPIT`.`CE_EV_SIGN` WHERE `EMAIL`=? AND `PASSWORD`=? ";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, emailField.getText());
            prstmt.setString(2, getMd5(passwordField.getText()));
            rs = prstmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Wrong Credentials");
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
