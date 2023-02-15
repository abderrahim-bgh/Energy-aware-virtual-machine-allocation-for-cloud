
package soutnoce_vm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.util.Duration;


/**
 *
 * @author abderrahim
 */

public class maincontroller implements Initializable  {
    @FXML
    private AnchorPane anchor1;

    @FXML
    private AnchorPane anchor2;
    @FXML
    private ImageView image_menu;
    @FXML
    BorderPane br;
    
    @FXML
    Button github1;
    
    String z;

    public void setZ(String z) {
        this.z = z;
    }

    public String getZ() {
        return z;
    }
    
    @FXML
    void home(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       webView.setVisible(false);
        br.setCenter(view);
    }
    @FXML
    void VmP(ActionEvent event) throws IOException {
        if(getZ().equals("1")){
            webView.setVisible(false);
            AnchorPane view = FXMLLoader.load(getClass().getResource("random.fxml"));
            br.setCenter(view);
        }
        
    }
     @FXML
     WebView webView ;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webView.setVisible(false);
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            br.setCenter(view);
             } catch (IOException ex) {
            Logger.getLogger(maincontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
            anchor2.setVisible(false);
            // Animation menu code
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),anchor1);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            
            TranslateTransition transition= new TranslateTransition(Duration.seconds(0.5),anchor2);
            transition.setByX(-600);
            transition.play();
            
            
            image_menu.setOnMouseClicked(event ->{
                if(!anchor2.isVisible()){
                    anchor2.setVisible(true);
                    FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),anchor1);
                    fadeTransition1.setFromValue(0);
                    fadeTransition1.setToValue(0.15);
                    fadeTransition1.play();
                    
                    TranslateTransition transition1= new TranslateTransition(Duration.seconds(0.5),anchor2);
                    transition1.setByX(+600);
                    transition1.play();
                }
                else{
                    FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),anchor1);
                    fadeTransition1.setFromValue(0.15);
                    fadeTransition1.setToValue(0);
                    fadeTransition1.play();
                    fadeTransition1.setOnFinished(event1->{
                        anchor2.setVisible(false);
                    });
                    TranslateTransition transition1= new TranslateTransition(Duration.seconds(0.5),anchor2);
                    transition1.setByX(-600);
                    transition1.play();
                }
            });
            
            anchor1.setOnMouseClicked(event1 ->{
                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),anchor1);
                fadeTransition1.setFromValue(0.15);
                fadeTransition1.setToValue(0);
                fadeTransition1.play();
                fadeTransition1.setOnFinished(event->{        
                    anchor2.setVisible(false);
                });
                TranslateTransition transition1= new TranslateTransition(Duration.seconds(0.5),anchor2);
                transition1.setByX(-600);
                transition1.play();

            });
            // i finishe the animation code here    }
       

           
            github1.setOnMouseClicked(event->{
                webView.setVisible(true);
                webView.getEngine().load("https://github.com/abderrahim-bgh/Energy-aware-virtual-machine-allocation-for-cloud");
            });
    

    }
}