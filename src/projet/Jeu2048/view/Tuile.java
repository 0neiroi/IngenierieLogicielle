/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048.view;
// mettre les threads ici ? 
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.effect.BlendMode;

/**
 *
 * @author oneiroi
 */
public final class Tuile extends Label {
    int x;
    int y;
    int value;
    int id;
    //Label tuileDeJeu;
    
    public Tuile(int value){
        //this.tuileDeJeu = new Label();
        //this.value = value;
        this.setText(""+value);
        this.setProprieties();
    }
    
    public Tuile(int value,int x,int y,int id){
        //this.tuileDeJeu = new Label();
        //this.value = value;
        this.setText(""+value);
        this.x=x;
        this.y=y;
        this.id=id;
        this.setProprieties();
    }
    
    
    public void setProprieties(){
        this.setAlignment(Pos.CENTER);
        this.setBlendMode(BlendMode.GREEN);
        this.setGraphicTextGap(5.0);
        this.setPrefSize(60.0, 60.0);
        this.setStyle("-fx-background-color: #f5f5dc; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 25%; ");
        this.setTextOverrun(OverrunStyle.CLIP);
        this.setVisible(true);
        
    }
    
  
   public int getIdCase(){
        return this.id;
   }
   public int getX(){
       return this.x;
   }
       
   public void setX(int x){
       this.x=x;
   }
   
   public void setY(int y){
       this.y=y;
   }
       public int getY(){
           return this.y;
   }
   
   
             //styleClass="case" 

    
}
