/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048.view;

import java.awt.im.InputContext;
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
import java.util.HashSet;
import java.util.Iterator;
import static javafx.application.ConditionalFeature.FXML;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import projet.Jeu2048.Fenetre2048;
import projet.Jeu2048.model.Case;
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
    
    
    private Pane p = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    //private final Label c = new Label("2");
    Tuile t = new Tuile(2);
    private int x = 0, y = 80;
    private int objectifx = 0, objectify = 80;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setMainApp(Fenetre2048 mainApp) {
        this.mainApp = mainApp;
        int k=0;
        for(int j=0;j>-4;j--){
            p = new Pane();
            p.getStyleClass().add("pane");
            t = new Tuile(this.mainApp.getMaGrille().getCase(0,j).getValeur(),0,k,this.mainApp.getMaGrille().getCase(0,j).getId());
            x= 0;
            y=k*60+80;
            p.getChildren().add(t);
            p.setLayoutX(x);
            p.setLayoutY(y);
            p.setVisible(true);
            myPane.getChildren().add(p);
            for(int i=1;i<4;i++){
                    p = new Pane();
                    p.getStyleClass().add("pane");
                    t = new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur(),i,k,this.mainApp.getMaGrille().getCase(i,j).getId());
                    p.getChildren().add(t);
                    
                    x= i*60;
                    y=k*60+80;
                    p.setLayoutX(x);
                    p.setLayoutY(y);
                    myPane.getChildren().add(p);
                    p.setVisible(true);
                  //  this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur()).tuileDeJeu,i,j*-1);
                    
                }
            k+=1;
            }
        
        
    }
     
    @FXML 
    private void handleButtonAction(Event event ) {
        String str="";
        if(event instanceof MouseEvent){
            str = ((Label)event.getSource()).getId();
        }else if(event instanceof KeyEvent){
            //str = ((KeyEvent)event).getCharacter();
            InputContext context = InputContext.getInstance(); 
            System.out.println(context.getLocale().getCountry().toString());
            
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
                    for(Node monPane: myPane.getChildren()) {
                       this.callTuile(((Pane)(monPane)),Parametres.haut);
                    }
                    break;
            case "downBtn":
               
                    mainApp.getMaGrille().seDeplacer(Parametres.bas);
                    for(Node monPane: myPane.getChildren()) {
                        this.callTuile(((Pane)(monPane)),Parametres.bas);
                    }
                    break;
            case "leftBtn":
                    mainApp.getMaGrille().seDeplacer(Parametres.gauche);
                    for(Node monPane: myPane.getChildren()) {
                        this.callTuile(((Pane)(monPane)),Parametres.gauche);
                    }
                    break;
            case "rightBtn":
                    mainApp.getMaGrille().seDeplacer(Parametres.droite);
                    for(Node monPane: myPane.getChildren()) {
                        this.callTuile(((Pane)(monPane)),Parametres.droite);
                    }
                    break;
                    
        } 
     
        if(!grilleCopie.equals(mainApp.getMaGrille().getGrille())){
            mainApp.getMaGrille().nouvelleCase();
            
        }

        
       // for(this.myGridPane.getChildren())
      
        this.myGridPane.getChildren().removeAll();
        this.myPane.getChildren().removeAll();
       // System.out.println(myPane.getChildren());
        
        
        int k=0;
 
        for(int j=0;j>-4;j--){
            p = new Pane();
            p.getStyleClass().add("pane");
            t = new Tuile(this.mainApp.getMaGrille().getCase(0,j).getValeur(),0,k,this.mainApp.getMaGrille().getCase(0,j).getId());
            x= 0;
            y=k*60+80;
            p.getChildren().add(t);
            p.setLayoutX(x);
            p.setLayoutY(y);
            p.setVisible(true);
            myPane.getChildren().add(p);

            
           // this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(0,j).getValeur()).tuileDeJeu,0,j*-1);
            for(int i=1;i<4;i++){
                    p = new Pane();
                    p.getStyleClass().add("pane");
                    t = new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur(),i,k,this.mainApp.getMaGrille().getCase(i,j).getId());
                    p.getChildren().add(t);
                    
                    x= i*60;
                    y=k*60+80;
                    p.setLayoutX(x);
                    p.setLayoutY(y);
                    myPane.getChildren().add(p);
                    p.setVisible(true);

                  //  this.myGridPane.add(new Tuile(this.mainApp.getMaGrille().getCase(i,j).getValeur()).tuileDeJeu,i,j*-1);
                    
                }
            k+=1;
            
            }
        myPane.setVisible(true);
        score.setText(""+mainApp.getMaGrille().getScore());
    }
    public void callTuile(Pane paneTmp,int d){
     /*   //System.out.println(((Tuile)(paneTmp.getChildren().get(0))).getIdCase());
        Case c;
        if(((Tuile)(paneTmp.getChildren().get(0))).getIdCase()!=0){
            c= this.mainApp.getMaGrille().getCase(((Tuile)(paneTmp.getChildren().get(0))).getIdCase());
            int xtmp=c.getX();
            System.out.println("Sans calcul Yobj: "+(c.getY())+" Xobj: "+c.getX()+" Xactu: "+((Tuile)(paneTmp.getChildren().get(0))).getX()+" Yactu: "+(((Tuile)(paneTmp.getChildren().get(0))).getY()));
          
            int ytmp=c.getY();
            
            objectifx=(xtmp*60);
            
            if(ytmp<0)ytmp*=-1;
            objectify=ytmp*60+80;
            
            Task task = null;
            switch (d){
                case haut:
                     task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
                        @Override
                        public Void call() throws Exception {
            // implémentation de la méthode protected abstract V call() dans la classe Task
                            while (((Tuile)(paneTmp.getChildren().get(0))).getY() != objectify) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                                if (((Tuile)(paneTmp.getChildren().get(0))).getY() > objectify) {
                                    
                                    ((Tuile)(paneTmp.getChildren().get(0))).setY((((Tuile)(paneTmp.getChildren().get(0))).getY() - 1)); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                                } 
                                // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        //javaFX operations should go here

                                        for(Node monPane: myPane.getChildren()) {
                                            ((Pane)(monPane)).relocate(((Pane)(monPane)).getLayoutX(), ((Tuile)(paneTmp.getChildren().get(0))).getY());
                                            ((Pane)(monPane)).setVisible(true);
                                        }      
                                    }
                                });
                                Thread.sleep(5);
                            } // end while
                            return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                        } // end call
                    };
                    break;
                case droite:
                    task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
                        @Override
                        public Void call() throws Exception {
            // implémentation de la méthode protected abstract V call() dans la classe Task
                            while (((Tuile)(paneTmp.getChildren().get(0))).getX() != objectifx) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                                if (((Tuile)(paneTmp.getChildren().get(0))).getX() < objectifx) {
                                    ((Tuile)(paneTmp.getChildren().get(0))).setX(((Tuile)(paneTmp.getChildren().get(0))).getX() + 1); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                                } 
                                // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        //javaFX operations should go here

                                        for(Node monPane: myPane.getChildren()) {
                                            ((Pane)(monPane)).relocate(((Tuile)(paneTmp.getChildren().get(0))).getX(), ((Pane)(monPane)).getLayoutY());
                                            ((Pane)(monPane)).setVisible(true);
                                        }      
                                    }
                                });
                                Thread.sleep(5);
                            } // end while
                            return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                        } // end call
                    };
                    break;
                case bas:
                     task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
                        @Override
                        public Void call() throws Exception {
            // implémentation de la méthode protected abstract V call() dans la classe Task
                            while (((Tuile)(paneTmp.getChildren().get(0))).getY() != objectify) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                                if (((Tuile)(paneTmp.getChildren().get(0))).getY() < objectify) {
                                    ((Tuile)(paneTmp.getChildren().get(0))).setY(((Tuile)(paneTmp.getChildren().get(0))).getY() + 1); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                                }
                                // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        //javaFX operations should go here

                                        for(Node monPane: myPane.getChildren()) {
                                            ((Pane)(monPane)).relocate(((Pane)(monPane)).getLayoutX(), ((Tuile)(paneTmp.getChildren().get(0))).getY());
                                            ((Pane)(monPane)).setVisible(true);
                                        }      
                                    }
                                });
                                Thread.sleep(5);
                            } // end while
                            return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                        } // end call
                    };
                    break;
                case gauche:
                    task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
                        @Override
                        public Void call() throws Exception {
            // implémentation de la méthode protected abstract V call() dans la classe Task
                            while (((Tuile)(paneTmp.getChildren().get(0))).getX() != objectifx) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                                if (((Tuile)(paneTmp.getChildren().get(0))).getX() > objectifx) {
                                   
                                   ((Tuile)(paneTmp.getChildren().get(0))).setX(((Tuile)(paneTmp.getChildren().get(0))).getX() - 1); // si on va vers la gauche, idem en décrémentant la valeur de x
                                }
                                // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        //javaFX operations should go here

                                        for(Node monPane: myPane.getChildren()) {
                                            ((Pane)(monPane)).relocate(((Tuile)(paneTmp.getChildren().get(0))).getX(), ((Pane)(monPane)).getLayoutY());
                                            ((Pane)(monPane)).setVisible(true);
                                        }      
                                    }
                                });
                                Thread.sleep(5);
                            } // end while
                            return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                        } // end call
                    };
                    break;
            }   
            
            

            Thread th = new Thread(task); // on crée un contrôleur de Thread
            th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
            th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
            //System.out.println("Yobj: "+(c.getY()*-60+80)+" Xobj: "+c.getX()*60+" Xactu: "+((Tuile)(paneTmp.getChildren().get(0))).getX()*60+" Yactu: "+(((Tuile)(paneTmp.getChildren().get(0))).getY()*60+80));
        }
        */
    }
    
    
     @FXML
    public void keyPressed(KeyEvent ke) {
        /* y=80;
        //if (x>0) x-=60;
        System.out.println("touche appuyée");
        String touche = ke.getText();
        System.out.println("objectifx=" + objectifx);
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            if (objectifx > 0) { // possible uniquement si on est pas dans la colonne la plus à gauche
                objectifx -= (int) 240 / 4; // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1)); // mise à jour du compteur de mouvement
            }
        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            if (objectifx < (int) 240 - 240 / 4 ) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectifx += (int) 240 / 4;
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }
        }
        System.out.println("objectifx=" + objectifx);
            Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
                @Override
                public Void call() throws Exception {
    // implémentation de la méthode protected abstract V call() dans la classe Task
                    
                    while (x != objectifx) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                        if (x < objectifx) {
                            x += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                        } else {
                            x -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                        }
                        // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                        Platform.runLater(new Runnable() { // classe anonyme
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                
                                for(Node monPane: myPane.getChildren()) {
                                   
                                   
                                            ((Pane)(monPane)).relocate(x, ((Pane)(monPane)).getLayoutY());
                                   
                                    ((Pane)(monPane)).setVisible(true);
                                    
                                }
                                    
                            }
                        }
                        );
                        Thread.sleep(5);
                    } // end while
                    return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                } // end call

            };
            Thread th = new Thread(task); // on crée un contrôleur de Thread
            th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
            th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
        */
    }
}
