/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048.view;

import java.awt.im.InputContext;
import java.io.File;
import java.io.FileOutputStream;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import projet.Jeu2048.Fenetre2048;
import projet.Jeu2048.model.Case;
import projet.Jeu2048.model.Grille;
import projet.Jeu2048.model.Parametres;
import static projet.Jeu2048.model.Parametres.*;

/**
 * FXML Controller class
 *
 * @author oneiroi
 */
public class Grille_de_jeuController implements Initializable {
    
    @FXML 
    Fenetre2048 mainApp;
    
    @FXML
    GridPane myGridPane;
    
    @FXML
    Text score;
    
    @FXML
    Label upBtn;
    
    @FXML
    Label leftBtn;
    
    @FXML
    Label rightBtn;
    
    @FXML
    Label downBtn;
    
    @FXML
    Button save;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setMainApp(Fenetre2048 mainApp) {
        this.mainApp = mainApp;
        for(int j=0;j>-4;j--){
                this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(0,j).getValeur()).tuileDeJeu,0,j*-1);
                for(int i=1;i<4;i++){
                    this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur()).tuileDeJeu,i,j*-1);
                }
            }

        // Add observable list data to the table
        
    }
     
    @FXML 
    private void handleButtonAction(Event event ) {
        String str="";
        if(event instanceof MouseEvent){
            str = ((Label)event.getSource()).getId();
        }else if(event instanceof KeyEvent){
            //str = ((KeyEvent)event).getCharacter();
            InputContext context = InputContext.getInstance(); 
            
            if(context.getLocale().getCountry().toString().equals("FR")){
                switch(((KeyEvent) event).getCharacter()){
            case "z":
                    str="upBtn";
                    break;
            case "s":
                    str="downBtn";
                    break;
            case "q":
                    str="leftBtn";
                    break;
            case "d":
                    str="rightBtn";
                    break;
            case "e":
                    str="save";
                    break;
            default:
                    System.out.println("NOP!");
                   break;
                    
        }
            
            }else{
                    switch(((KeyEvent) event).getCharacter()){
            case "w":
                    str="upBtn";
                    break;
            case "s":
                    str="downBtn";
                    break;
            case "a":
                    str="leftBtn";
                    break;
            case "d":
                    str="rightBtn";
                    break;
            default:
                    System.out.println("NOP!");
                   break;
                    
        }
            }
            
            
        }
        HashSet<Case> grilleCopie=new HashSet<>();
        grilleCopie.addAll(mainApp.getMaGrille().getGrille());
        
        
        
        switch(str){
            case "upBtn":
                    mainApp.getMaGrille().seDeplacer(Parametres.haut);
                    break;
            case "downBtn":
                    mainApp.getMaGrille().seDeplacer(Parametres.bas);
                    break;
            case "leftBtn":
                    mainApp.getMaGrille().seDeplacer(Parametres.gauche);
                    break;
            case "rightBtn":
                    mainApp.getMaGrille().seDeplacer(Parametres.droite);
                    break;
            default: break;
                    
        } 
        if(!grilleCopie.equals(mainApp.getMaGrille().getGrille())){
            mainApp.getMaGrille().nouvelleCase();
        }
        this.myGridPane.getChildren().removeAll();
        for(int j=0;j>-4;j--){
                this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(0,j).getValeur()).tuileDeJeu,0,j*-1);
                for(int i=1;i<4;i++){
                    this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur()).tuileDeJeu,i,j*-1);
                }
            }
        score.setText(""+mainApp.getMaGrille().getScore());
    }
    @FXML
    public void saveButton(){
        // popup pour demander le nom du joueur
        // Récupération du pseudo du joueur 
        String pseudo = "test";
        // Récupération de l'objet grille que l'on veut sauvegarder
        Grille serGrille = this.mainApp.getMaGrille();
        try {
            File mySave = new File("./saves/" + pseudo);
            if(mySave.exists()){
                //popup pour demander la confirmation d'écraser la sauvegarde précédente + date
            }
            FileOutputStream fileOut = new FileOutputStream(mySave);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(serGrille);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
      }
    }
    
}
