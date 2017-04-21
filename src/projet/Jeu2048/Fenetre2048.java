/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048;


import java.util.Random;

import projet.Jeu2048.model.Grille;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import projet.Jeu2048.view.Grille_de_jeuController;

/**
 *
 * @author oneiroi
 */
public class Fenetre2048 extends Application {
    
    Grille maGrille=new Grille();
    Random ra=new Random();

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    public Grille getMaGrille(){
        return this.maGrille;
    }
    public Fenetre2048() {
        this.maGrille.nouvelleCase();
        this.maGrille.nouvelleCase();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("2048");
        initRootLayout();

        showOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Fenetre2048.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Fenetre2048.class.getResource("view/grille_de_jeu.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            // Give the controller access to the main app.
            Grille_de_jeuController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void loadGrille(Grille chargee){
        maGrille = chargee;
    }
    
}
