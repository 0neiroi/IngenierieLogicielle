/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048.view;
// mettre les threads ici ? 
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.effect.BlendMode;

/**
 *
 * @author oneiroi
 */
public final class Tuile {
    int x;
    int y;
    int value;
    Label tuileDeJeu;
    
    public Tuile(int value){
        this.tuileDeJeu = new Label();
        //this.value = value;
        this.tuileDeJeu.setText(""+value);
        this.setProprieties();
    }
    
    public Tuile(int value,int x,int y){
        this.tuileDeJeu = new Label();
        //this.value = value;
        this.tuileDeJeu.setText(""+value);
        this.x=x;
        this.y=y;
        this.setProprieties();
    }
    
    
    public void setProprieties(){
        tuileDeJeu.setAlignment(Pos.CENTER);
        tuileDeJeu.setBlendMode(BlendMode.GREEN);
        tuileDeJeu.setGraphicTextGap(5.0);
        tuileDeJeu.setPrefSize(60.0, 60.0);
        tuileDeJeu.setStyle("-fx-background-color: #f5f5dc; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 25%;");
        tuileDeJeu.setTextOverrun(OverrunStyle.CLIP);
        tuileDeJeu.setVisible(true);
        
    }
             //styleClass="case" 
}
