/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048.model;

/**
 *
 * @author oneiroi
 */
public class Case implements Parametres {
   /*Pour les coordonnées, nous considérerons que  le  point  d’origine  est  en  
   haut  à  gauche. Une  case  située tout  en  haut  à  gauche aura  pour coordonnées 
   (0;0).L’axe des abscisses va de la gauche vers la droite et la valeur en abscisse va 
   de 0 à 3. L’axe des ordonnéesva du haut vers le bas et la valeur en ordonnée 
   est comprise entre 0 et 3.*/
    private int x;
    private int y;
    private int valeur;
    private Grille maGrille;
    private boolean fusion;
    private int id;
    
    public Case(int abscisse,int ordonnee, int maValeur){
        this.x=abscisse;
        this.y=ordonnee;
        this.valeur=maValeur;
        this.fusion=false;
    }

    Case() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int i){
        this.id=i;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
    public boolean isFusion() {
        return fusion;
    }

    public void setFusion(boolean f) {
        this.fusion = f;
    }
    
    public void setMaGrille(Grille g) {
        this.maGrille = g;
    }
    
    public String toString(){
        String str;
        str="Cette case a pour coordonnées ("+this.x+";"+this.y+") et pour valeur "+ this.valeur+ " Son id est "+this.id;
        
        return str;
    }
    
    public int hashCode(){
        return this.getX()*17+this.getY()*13;
    }
    
    public boolean egals(Object o){
        boolean b=false;
            if(o instanceof Case){
                if(((Case)o).hashCode()==this.hashCode()){
                    b=true;
                }
            }
        return b;
    }
    
    public boolean valeurEgale(Case c){
        boolean b=false;
        if(c!=null){
            if(this.getValeur()==c.getValeur()){
                b=true;
            }
        }
            
        return b;
    }
    
    public Case getVoisinDirect(int d){
        Case c=null;
        int i=1;
        boolean trouve=false;
        switch(d){
            case 1: //Haut
                    
                    while(this.y+i<=0&&!trouve){
                        if(this.maGrille.getCase(this.x, this.y+i).valeur!=0){
                            c=this.maGrille.getCase(this.x, this.y+i);
                            trouve=true;
                        }
                        i++;
                    }
                
                break;
            case 2:// Droite
                while(this.x+i<=(taille-1)&&!trouve){
                        if(this.maGrille.getCase(this.x+i, this.y).valeur!=0){
                            c=this.maGrille.getCase(this.x+i, this.y);
                            trouve=true;
                        }
                        i++;
                    }
                break;
            case -1:// Bas
                while(this.y-i>=((-1)*(taille-1))&&!trouve){
                        if(this.maGrille.getCase(this.x, this.y-i).valeur!=0){
                            c=this.maGrille.getCase(this.x, this.y-i);
                            trouve=true;
                        }
                        i++;
                    }
                break;
            case -2: // Gauche
                while(this.x-i>=0&&!trouve){
                        if(this.maGrille.getCase(this.x-i, this.y).valeur!=0){
                            c=this.maGrille.getCase(this.x-i, this.y);
                            trouve=true;
                        }
                        i++;
                    }
                
                break;
        
        }
        return c;
    }
    
}

