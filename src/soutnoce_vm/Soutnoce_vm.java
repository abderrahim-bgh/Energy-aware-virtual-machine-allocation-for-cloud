/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package soutnoce_vm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author abderrahim
 */
public class Soutnoce_vm extends Application {
    double x=0,y=0;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        root.setOnMousePressed(event->{
            x=event.getSceneX();
            y=event.getSceneY();
        });
        root.setOnMouseDragged(event->{
           stage.setX(event.getSceneX()-x);
           stage.setY(event.getSceneY()-y);
           
        });
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
