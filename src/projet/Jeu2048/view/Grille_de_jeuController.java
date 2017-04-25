
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

import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import projet.Jeu2048.Fenetre2048;
import projet.Jeu2048.model.Case;
import projet.Jeu2048.model.Grille;
import projet.Jeu2048.model.Parametres;
import static projet.Jeu2048.model.Parametres.bas;
import static projet.Jeu2048.model.Parametres.droite;
import static projet.Jeu2048.model.Parametres.gauche;
import static projet.Jeu2048.model.Parametres.haut;
import static projet.Jeu2048.model.Parametres.objectif;


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
    Pane myPane;
    
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
    
    @FXML
    Label scoreFinish;
    
    @FXML
    Label resultat; 
    
    @FXML
    Label quit;
    
    @FXML
    Label retry;
    
    @FXML
    Pane paneFinish; 
    
    
    private Pane p = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    //private final Label c = new Label("2");
    Tuile t;// = new Tuile("2");
    private int x = 0, y = 0;
    private int objectifx = 0, objectify =0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setMainApp(Fenetre2048 mainApp) {
        this.mainApp = mainApp;
        if(mainApp.getPrimaryStage().getTitle().equals("2048")){
        int k=0;
        for(int j=0;j>-4;j--){
            p = new Pane();
            p.getStyleClass().add("pane");
            x= 0;
            y=k*60;
            t = new Tuile(this.mainApp.getMaGrille().getCase(0,j).getValeur(),x,y,this.mainApp.getMaGrille().getCase(0,j).getId());

            p.getChildren().add(t);
            p.setStyle(p.getChildren().get(0).getStyle());
            p.setLayoutX(x);
            p.setLayoutY(y);
            //p.setVisible(true);
            myPane.getChildren().add(p);
            for(int i=1;i<4;i++){
                    p = new Pane();
                    p.getStyleClass().add("pane");
            
                    
                    x= i*60;
                    y=k*60;
                    t = new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur(),x,y,this.mainApp.getMaGrille().getCase(i,j).getId());
                    p.getChildren().add(t);
                    p.setStyle(p.getChildren().get(0).getStyle());
                    p.setLayoutX(x);
                    p.setLayoutY(y);
                    myPane.getChildren().add(p);
              //      p.setVisible(true);
                  //  this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur()).tuileDeJeu,i,j*-1);
                    
                }
            k+=1;
            }
        
     }
    }
     
    @FXML 
    private void handleButtonAction(Event event ) throws IOException {
        String str="";
        if(event instanceof MouseEvent){
            str = ((Label)event.getSource()).getId();
        }else if(event instanceof KeyEvent){
            //str = ((KeyEvent)event).getCharacter();
            InputContext context = InputContext.getInstance(); 

            System.out.println(context.getLocale().getCountry());
            
            if(context.getLocale().getCountry().equals("FR")){
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
                    myPane.getChildren().forEach((Node monPane) -> {
                        Grille_de_jeuController.this.callTuile((Pane)(monPane), Parametres.haut);
        });
                    break;
            case "downBtn":
               
                    mainApp.getMaGrille().seDeplacer(Parametres.bas);
                    myPane.getChildren().forEach((monPane) -> {
                        this.callTuile(((Pane)(monPane)),Parametres.bas);
        });
                    break;
            case "leftBtn":
                    mainApp.getMaGrille().seDeplacer(Parametres.gauche);
                    myPane.getChildren().forEach((monPane) -> {
                        this.callTuile(((Pane)(monPane)),Parametres.gauche);
        });
                    break;
            case "rightBtn":
                    mainApp.getMaGrille().seDeplacer(Parametres.droite);
                    myPane.getChildren().forEach((monPane) -> {
                        this.callTuile(((Pane)(monPane)),Parametres.droite);
        });
                    break;
            default: break;
                    
        } 
     
        if(!grilleCopie.equals(mainApp.getMaGrille().getGrille())){
            mainApp.getMaGrille().nouvelleCase();
            
        }

        
       // for(this.myGridPane.getChildren())
      
        //this.myGridPane.getChildren().removeAll();
        //this.myPane.getChildren().removeAll();
        //this.myPane.getChildren().forEach((monPane)->((Pane)monPane).setVisible(true));
       // System.out.println(myPane.getChildren());
        
        System.out.println(this.mainApp.getMaGrille().getCases());
        int k=0;
        
        for(int j=0;j>-4;j--){
            p = new Pane();
            p.getStyleClass().add("pane");
            
            x= 0;
            y=k*60;
            boolean trouveL= false;
                        for(Node monPane : myPane.getChildren()){
                        if(this.mainApp.getMaGrille().getCase(0,j).getId()==((Tuile)((Pane)monPane).getChildren().get(0)).getIdCase()){
                            trouveL = true;
                        }
                    }
                    
                    //if(!trouveL){ // ligne à décommenter pour activer les animations
                           t = new Tuile(this.mainApp.getMaGrille().getCase(0,j).getValeur(),x,y,this.mainApp.getMaGrille().getCase(0,j).getId());
            p.getChildren().add(t);
            p.setStyle(p.getChildren().get(0).getStyle());
            p.setLayoutX(x);
            p.setLayoutY(y);
            //p.setVisible(true);
            myPane.getChildren().add(p);

                    //}    // ligne à décommenter pour activer les animations    
            
         
            
           // this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(0,j).getValeur()).tuileDeJeu,0,j*-1);
          for(int i=1;i<4;i++){
                    p = new Pane();
                    p.getStyleClass().add("pane");
                    boolean trouve = false;
                    
                    
                    
                    
                    x= i*60;
                    y=k*60;
                    for(Node monPane : myPane.getChildren()){
                        if(this.mainApp.getMaGrille().getCase(i,j).getId()==((Tuile)((Pane)monPane).getChildren().get(0)).getIdCase()){
                            trouve = true;
                        }
                    }
                    
                    //if(!trouve){ // ligne à décommenter pour activer les animations
                    t = new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur(),x,y,this.mainApp.getMaGrille().getCase(i,j).getId());
                    p.getChildren().add(t);
                    p.setStyle(p.getChildren().get(0).getStyle());
                    p.setLayoutX(x);
                    p.setLayoutY(y);
                    myPane.getChildren().add(p);
                    //p.setVisible(true);

                  //  this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur()).tuileDeJeu,i,j*-1);
                    //} // ligne à décommenter pour activer les animations
                    
                    
                }
            k+=1;
            
            }
        resultat = new Label();
        scoreFinish = new Label();
        //paneFinish = new Pane();
        //myPane.setVisible(true);
        score.setText(""+mainApp.getMaGrille().getScore());
        //mainApp.getMaGrille().setValeurMax(2048); // test écran de fin
        if(objectif <= mainApp.getMaGrille().getValeurMax()){
            
            
             FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Fenetre2048.class.getResource("view/finishPanel.fxml"));
            Pane personOverview = (Pane) loader.load();
            Scene scene = new Scene(personOverview);
            mainApp.getPrimaryStage().setScene(scene);
            mainApp.getPrimaryStage().setTitle("Final");
            mainApp.getPrimaryStage().show();
            Grille_de_jeuController controller = loader.getController();
            controller.setMainApp(mainApp);
            //mainApp.getBorderPane().setCenter(personOverview);
            resultat.setText("Victoire");
            scoreFinish.setText(""+mainApp.getMaGrille().getScore());
            //paneFinish.getChildren().add(resultat);
            //paneFinish.getChildren().add(scoreFinish);
            scoreFinish.setVisible(true);
            resultat.setVisible(true);
            
            
            mainApp.showOverview();
        }else if(mainApp.getMaGrille().partieFinie()){
            
             FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Fenetre2048.class.getResource("view/finishPanel.fxml"));
            Pane personOverview = (Pane) loader.load();
            mainApp.getBorderPane().setCenter(personOverview);
            scoreFinish.setText(""+mainApp.getMaGrille().getScore());
            resultat.setText("Défaite");
            scoreFinish.setVisible(true);
            resultat.setVisible(true);
            mainApp.showOverview();
        }
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


    public void callTuile(Pane paneTmp,int d){
     /*   // ligne à décommenter pour activer les animations
        
        Case c= this.mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase());
            //System.out.println("ID:"+((Tuile)(paneTmp.getChildren().get(0))).getIdCase());
            //System.out.println("Xcase: "+c.getX()+" Ycase: "+c.getY()+" Xtuile: "+((Tuile)(paneTmp.getChildren().get(0))).getX()+" Ytuile: "+((Tuile)(paneTmp.getChildren().get(0))).getY());
            int xtmp=c.getX();
            objectifx=(xtmp*60);
            
            int ytmp=c.getY();
            if(ytmp<0)ytmp*=-1;
            objectify=(ytmp*60);
            //System.out.println("ObjX: "+objectifx+" ObjY: "+objectify);
            Task task = null;
           //System.out.println(((Tuile)(paneTmp.getChildren().get(0))).getY());
            //System.out.println(((Tuile)(paneTmp.getChildren().get(0))).getX());
            if(((d==haut&&((Tuile)(paneTmp.getChildren().get(0))).getY()==0)||(d==bas&&((Tuile)(paneTmp.getChildren().get(0))).getY()==240)||(d==-2&&((Tuile)(paneTmp.getChildren().get(0))).getX()==0)||(d==2&&((Tuile)(paneTmp.getChildren().get(0))).getX()==240))||((Tuile)(paneTmp.getChildren().get(0))).getValue()==0){
               // System.out.println(((Tuile)(paneTmp.getChildren().get(0))).getValue());
            }else{  
                
                
                         task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
                            @Override
                            public Void call() throws Exception {
                               // System.out.println("AH QUE COUCOU");
                // implémentation de la méthode protected abstract V call() dans la classe Task
                                //while ((((Tuile)(paneTmp.getChildren().get(0))).getY() != objectify)||((((Tuile)(paneTmp.getChildren().get(0))).getX() != objectifx))) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                                System.out.println(((((mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase())).getY())*60)));
                                while (((((Tuile)(paneTmp.getChildren().get(0))).getY()<=180))&&((((Tuile)(paneTmp.getChildren().get(0))).getY()>=0))&&(((Tuile)(paneTmp.getChildren().get(0))).getY() != ((((mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase())).getY())*60)))) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                                    //System.out.println("Y:"+ (((Tuile)(paneTmp.getChildren().get(0))).getY()));
                                    if ((((Tuile)(paneTmp.getChildren().get(0))).getY() > ((((mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase())).getY())*60))&&(d==haut))) {
                                        //System.out.println(((Tuile)(paneTmp.getChildren().get(0))).getY());
                                        ((Tuile)(paneTmp.getChildren().get(0))).setY((((Tuile)(paneTmp.getChildren().get(0))).getY() - 1)); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                                        //System.out.println("apres : "+((Tuile)(paneTmp.getChildren().get(0))).getY());
                                    }else if ((((Tuile)(paneTmp.getChildren().get(0))).getY() > ((((mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase())).getY())*60))&&(d==bas))) {
                                        //System.out.println(" Get Y :"+ ((Tuile)(paneTmp.getChildren().get(0))).getIdCase()+ " " +((Tuile)(paneTmp.getChildren().get(0))).getY());
                                        ((Tuile)(paneTmp.getChildren().get(0))).setY((((Tuile)(paneTmp.getChildren().get(0))).getY() + 1)); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                                    }/*else if(((Tuile)(paneTmp.getChildren().get(0))).getX() < objectifx){
                                        ((Tuile)(paneTmp.getChildren().get(0))).setX((((Tuile)(paneTmp.getChildren().get(0))).getX() + 1)); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                                    }else if(((Tuile)(paneTmp.getChildren().get(0))).getX() > objectifx){
                                        ((Tuile)(paneTmp.getChildren().get(0))).setX((((Tuile)(paneTmp.getChildren().get(0))).getX() - 1)); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                                    }
                                    // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                                    Platform.runLater(() -> {
                                            
                                            paneTmp.relocate(((mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase())).getX())*60, ((Tuile)(paneTmp.getChildren().get(0))).getY());
                                            myPane.getChildren().remove(paneTmp);
                                            myPane.getChildren().add(paneTmp);
                                            paneTmp.setVisible(true);
                                            //myPane.getChildren().remove(paneTmp);
                                    } // classe anonyme
                                    );
                                    Thread.sleep(5);
                                } // end while
                                return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                            } // end call
                        };
                         Thread th = new Thread(task); // on crée un contrôleur de Thread
            th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
            th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
                System.out.println("ValueTuile: "+((Tuile)(paneTmp.getChildren().get(0))).getValue()+" ValueCase: "+(this.mainApp.getMaGrille().getCase(((mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase())).getX())/60,(((mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase())).getY()))/-60)).getValeur());
                //System.out.println("Xcase: "+(this.mainApp.getMaGrille().getCase(objectifx/60,(objectify)/-60)).getX()+" Ycase: "+(this.mainApp.getMaGrille().getCase(objectifx/60,(objectify)/-60)).getY()+" Xtuile: "+((Tuile)(paneTmp.getChildren().get(0))).getX()+" Ytuile: "+((Tuile)(paneTmp.getChildren().get(0))).getY());
                System.out.println(objectifx);
                System.out.println(objectify);
                if(((Tuile)(paneTmp.getChildren().get(0))).getValue()!=(this.mainApp.getMaGrille().getCase(objectifx/60,(objectify)/-60)).getValeur()){
                    System.out.println("coucou");
                    //paneTmp.setVisible(false);
                //myPane.getChildren().remove(paneTmp);
                paneTmp.getChildren().add(new Tuile((this.mainApp.getMaGrille().getCase(((mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase())).getX())/60,(((mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase())).getY()))/-60)).getValeur(),objectifx,objectify,(this.mainApp.getMaGrille().getCase(objectifx/60,(objectify)/-60)).getId()));
                //myPane.getChildren().add(paneTmp);
                //paneTmp.setVisible(true);
                //((Tuile)(paneTmp.getChildren().get(0))).setValue((this.mainApp.getMaGrille().getCase(objectifx/60,(objectify)/-60)).getValeur());
                //myPane.getChildren().remove(paneTmp);
                //myPane.setVisible(true);Grille_de_jeuController.java:179
                //myPane.getChildren().add(paneTmp);
                paneTmp.setStyle(paneTmp.getChildren().get(0).getStyle());
                
                paneTmp.setVisible(true);
            }   
                
                
              
        
    }
            //verifPane();       
            //myPane.getChildren().forEach((monpane)->System.out.println("ID: "+((Tuile)((Pane)monpane).getChildren().get(0)).getIdCase()+"Value :"+((Tuile)((Pane)monpane).getChildren().get(0)).getValue()+" X: "+((Tuile)((Pane)monpane).getChildren().get(0)).getX()+" Y: "+ ((Tuile)((Pane)monpane).getChildren().get(0)).getY() ));       
*/ // ligne à décommenter pour activer les animations
}
    public boolean verifPane(){
        System.out.println("Coucou");
        for(Node monPane : myPane.getChildren()){
            int idTmp= ((Tuile)(((Pane)monPane).getChildren().get(0))).getIdCase();
            for(Node monPane2 : myPane.getChildren()){
                if(idTmp==((Tuile)(((Pane)monPane2).getChildren().get(0))).getIdCase()){
                    if(((Tuile)(((Pane)monPane).getChildren().get(0))).getValue()!=((Tuile)(((Pane)monPane2).getChildren().get(0))).getValue()){
                        if(((Tuile)(((Pane)monPane).getChildren().get(0))).getValue()<((Tuile)(((Pane)monPane2).getChildren().get(0))).getValue()){
                            monPane.setVisible(false);
                            myPane.getChildren().remove(monPane);
                            break;
                        }else{
                            monPane2.setVisible(false);
                            myPane.getChildren().remove(monPane2);
                        }
                    }else{
                        monPane2.setVisible(false);
                        myPane.getChildren().remove(monPane2);
                    }
                }
            }
          
        }
           
        
        return false;
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

    
    
    
    
    
    @FXML
    public void quit(){
        System.exit(0);
    }
    
    @FXML
    public void retry(){
//    mainApp.getPrimaryStage().close();
      Platform.runLater( () -> {
        try {
            new Fenetre2048().start( new Stage() );
        } catch (IOException ex) {
            Logger.getLogger(Grille_de_jeuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } );
    }
    
    @FXML
    public void pressMouse(MouseEvent event){
    
        if(((Label)(event.getSource())).getId().equals("retry")){
            retry();
        }else if(((Label)(event.getSource())).getId().equals("quit")){
            quit();
        }
    }
    

}
