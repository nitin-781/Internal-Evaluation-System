/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author VASPAR
 */
public class AdminDriver extends Application {

    public static Stage copyStage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root1 = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene1 = new Scene(root1);
        String css=this.getClass().getResource("Admin.css").toExternalForm();
        scene1.getStylesheets().addAll(css);
        stage.getIcons().add(new Image("file:C:\\Users\\VASPAR ASPI\\Documents\\NetBeansProjects\\Admin\\dist\\Admin.png"));
        stage.setScene(scene1);
        stage.setTitle("Sign In");
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
        System.out.println(stage.getWidth());
        System.out.println(stage.getHeight());
        copyStage=stage;
    }
    
    public void isSignedIn(){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
