/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048.view;

import java.awt.im.InputContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
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
    
    @FXML
    TextField pseudo;
    
    @FXML
    Button load;
    
    
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
        // Récupération du pseudo du joueur 
        File mySave = new File("./saves/" + pseudo.getText() + ".sv");
        
        if(mySave.exists()){
            //popup pour demander la confirmation d'écraser la sauvegarde précédente
            FlowPane flowPane = new FlowPane();
            Button confirm = new Button("Oui");
            Label texte = new Label("Écraser la sauvegarde de " + pseudo.getText() + " ?");
            
            flowPane.getChildren().addAll(texte, confirm);
            Scene scene = new Scene(flowPane, 200, 100);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Écraser sauvegarde ?");
            stage.show();
            confirm.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    sauvegarder(mySave);
                    stage.close();
                }
            }) ;
        } else {
            sauvegarder(mySave);
        }
    }
    
    public void sauvegarder(File mySave){
        try {
            // Objet à sérialiser
            Grille serGrille = this.mainApp.getMaGrille();
            
            FileOutputStream fileOut = new FileOutputStream(mySave);
            ObjectOutputStream out;
            out = new ObjectOutputStream(fileOut);
            out.writeObject(serGrille);
            out.close();
            fileOut.close();
            System.out.println("La sauvegarde a bien été effectuée.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Fonction appelée au click du bouton load
     */
    
    public void loadButton(){
        FlowPane listFP = new FlowPane();
        File dir = new File("./saves/");
        ObservableList<String> saves = trierListe(new ArrayList(Arrays.asList(dir.list())));
        ListView<String> listView = new ListView<String>(saves);
        
        listFP.getChildren().addAll(listView);
        Scene scene = new Scene(listFP, 200, 500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Choisir la sauvegarde");
        stage.show();
        
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ouvrir(listView.getSelectionModel().getSelectedItem());
                stage.close();
            }});
    }
    
    /**
     * Fonction qui permet de ne garder que les fichiers de sauvegarde
     * Sur mac, le fichier .DS_Store apparaissait dans la liste alors qu'il ne contient pas de sauvegarde
     * @param list
     * @return
     */
    public ObservableList<String> trierListe(ArrayList<String> list){
        
        for(int i=0; i < list.size() ; i++){
            String sub = list.get(i).substring(list.get(i).length() - 3);
            if(sub.compareTo(".sv") != 0){
                list.remove(i);
                i -= 1;
            }
        }
        return observableArrayList(observableArrayList(list));
    }
    
    public void ouvrir (String fileName){
        try {
            FileInputStream fis = new FileInputStream("./saves/" + fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Grille deserGrille = (Grille)ois.readObject();
            fis.close();ois.close();
            mainApp.loadGrille(deserGrille);
            mainApp.showOverview();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
