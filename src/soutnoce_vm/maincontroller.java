
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private ImageView image_menu;
    @FXML
    BorderPane br;
    
    @FXML
    ImageView github1;
    
    String z;

    public void setZ(String z) {
        this.z = z;
    }

    public String getZ() {
        return z;
    }
  
    @FXML
    void home(MouseEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       webView.setVisible(false);
        br.setCenter(view);
    }
    
    /*void page2() throws IOException  {
       AnchorPane view;
       view = FXMLLoader.load(getClass().getClassLoader().getResource("random.fxml"));
       home_tab.setContent(view);
       webView.setVisible(false);
       tabP.getTabs().add(home_tab);
        br.setCenter(tabP);
    }*/
    
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
           
            // Animation menu code
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.8),anchor1);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            
            image_menu.setOnMouseClicked(event ->{
                    FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.7),anchor1);
                    fadeTransition1.setFromValue(0);
                    fadeTransition1.setToValue(1);
                    fadeTransition1.play();
            });
            // i finishe the animation code here 
            github1.setOnMouseClicked(event->{
                webView.setVisible(true);
                webView.getEngine().load("https://github.com/abderrahim-bgh/Energy-aware-virtual-machine-allocation-for-cloud");
            });
    
    }
}