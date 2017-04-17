/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048.model;

import java.io.Serializable;

/**
 *
 * @author oneiroi
 */
public class Case implements Parametres, Serializable {
   /*Pour les coordonnées, nous considérerons que  le  point  d’origine  est  en  
   haut  à  gauche. Une  case  située tout  en  haut  à  gauche aura  pour coordonnées 
   (0;0).L’axe des abscisses va de la gauche vers la droite et la valeur en abscisse va 
   de 0 à 3. L’axe des ordonnées va du haut vers le bas et la valeur en ordonnée 
   est comprise entre 0 et 3.*/
    private int x;
    private int y;
    private int valeur;
    private Grille maGrille;
    private boolean fusion;
    private int id;
    
    /**
     * Constructeur public d'une Case avec la valeur de son abscisse, son ordonnée et sa valeur en attributs,     * 
     * @param abscisse
     * @param ordonnee
     * @param maValeur 
     * 
     * en plus de cela, la valeur du booleen fusion est forcée à false.
     */
    public Case(int abscisse,int ordonnee, int maValeur){
        this.x=abscisse;
        this.y=ordonnee;
        this.valeur=maValeur;
        this.fusion=false;
    }


    /**
     * Méthode qui retourne la valeur de l'abscisse de la case.
     * @return 
     */

    Case() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public int getX() {
        return x;
    }

    /**
     * Méthode qui modifie la valeur de l'abscisse de la case par la valeur envoyée en atribut.
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    
    /**
     * Méthode qui retourne la valeur de l'ordonée de la case.
     * @return 
     */

    public int getId(){
        return this.id;
    }
    public void setId(int i){
        this.id=i;
    }


    public int getY() {
        return y;
    }

    /**
     * Méthode qui modifie la valeur de l'abscisse de la case par la valeur envoyée en atribut.
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Méthode qui retourne la valeur de la case.
     * @return 
     */
    public int getValeur() {
        return valeur;
    }
    
    /**
     * Méthode qui modifie la valeur de la case par la valeur envoyée en atribut.
     * @param valeur 
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
    /**
     * Méthode 
     * @return 
     */
    public boolean isFusion() {
        return fusion;
    }
    
    /**
     * Méthode qui modifie la valeur de fusion de la case par la valeur envoyée en atribut.
     * @param f 
     */
    public void setFusion(boolean f) {
        this.fusion = f;
    }
    
    /**
     * Méthode qui définie l'appartenance d'une case à une grille passée en attribut.
     * @param g 
     */
    public void setMaGrille(Grille g) {
        this.maGrille = g;
    }
    
    /**
     * Méthode servant à afficher dans la console, l'étât de toutes les variables importantes d'une case.
     * @return 
     */
    public String toString(){
        String str;
        str="Cette case a pour coordonnées ("+this.x+";"+this.y+") et pour valeur "+ this.valeur+ " Son id est "+this.id;
        
        return str;
    }
    
    /**
     * Un appel à cette méthode retourne le code hashCode actuel de la case.
     * Cette méthode permet de savoir si plusieurs cases ont EXACTEMENT les mêmes coordonées.
     * @return 
     */
    public int hashCode(){
        return this.getX()*17+this.getY()*13;
    }
    
    /**
     * Cette méthode renvoie la valeur de vérité de l'égalité des coordonées entre la case en attribut et celle qui appelle cette méthode.
     * Elle utilise pour cela la méthode hashCode.
     * @param o
     * @return 
     * 
     * Attention, deux cases dites égales par cette méthode n'ont pas forcément la même valeur, la méthode ne vérifie QUE les coordonnées.
     * 
     */
    public boolean egals(Object o){
        boolean b=false;
            if(o instanceof Case){ // Si o est une Case
                if(((Case)o).hashCode()==this.hashCode()){ // Si la méthode hashCode de la case o renvoie la même valeur que la méthode hashCode de cette case.
                    b=true;
                }
            }
        return b;
    }
    
    /**
     * Cette méthode renvoie la valeur de vérité de l'égalité des valeurs entre la case en attribut et celle qui appelle cette méthode.
     * @param c
     * @return 
     * 
     * Attention, deux cases dites égales par cette méthode n'ont pas forcément les mêmes coordonnées, la méthode ne vérifie QUE les valeurs.
     * 
     */
    public boolean valeurEgale(Case c){
        boolean b=false;
        if(c!=null){    // Si la case c n'est pas null, donc qu'elle a une valeur.
            if(this.getValeur()==c.getValeur()){ //Si les deux cases ont la même valeur.
                b=true;
            }
        }
            
        return b;
    }
    
    /**
     * Cette méthode prends une direction en paramètre et rends la première case de la grille rencontrée lors d'un mouvement de la case courante dans cette direction
     * @param d
     * @return 
     * Elle retournera une case temporaire (null) si elle n'en rencontre pas.
     */
    public Case getVoisinDirect(int d){
        Case c=null;
        int i=1;
        boolean trouve=false;
        switch(d){
            case haut:  //Si la direction choisie est Haut, on cherche donc une case avec la même abscisse et une ordonnée plus petite.
                    
                    while(this.y+i<=0&&!trouve){ //Tant que l'on est pas arrivé à l'abscisse minimale et que l'on a pas trouvé une case
                        if(this.maGrille.getCase(this.x, this.y+i).valeur!=0){  //Si il existe dans la grille de la case courante, une case correspondant à l'abscisse de la case courante et avec l'ordonnée modifiée
                            c=this.maGrille.getCase(this.x, this.y+i);  // Alors la case temporaire c prends l'adresse mémoire de cette case
                            trouve=true;      //Et le booleen trouvé annonce à la boucle while qu'une case voisine a été trouvée.
                        }
                        i++;    //La valeur de i s'incrémente.
                    }
                
                break;
            case droite:    //Si la direction choisie est Droite, on cherche donc une case avec la même ordonnée et une abscisse plus grande.
                while(this.x+i<=(taille-1)&&!trouve){
                        if(this.maGrille.getCase(this.x+i, this.y).valeur!=0){
                            c=this.maGrille.getCase(this.x+i, this.y);
                            trouve=true;
                        }
                        i++;
                    }
                break;
            case bas:   //Si la direction choisie est Bas, on cherche donc une case avec la même abscisse et une ordonnée plus grande.
                while(this.y-i>=((-1)*(taille-1))&&!trouve){
                        if(this.maGrille.getCase(this.x, this.y-i).valeur!=0){
                            c=this.maGrille.getCase(this.x, this.y-i);
                            trouve=true;
                        }
                        i++;
                    }
                break;
            case gauche:    //Si la direction choisie est Gauche, on cherche donc une case avec la même ordonnée et une abscisse plus petite.
                while(this.x-i>=0&&!trouve){
                        if(this.maGrille.getCase(this.x-i, this.y).valeur!=0){
                            c=this.maGrille.getCase(this.x-i, this.y);
                            trouve=true;
                        }
                        i++;
                    }
                
                break;
        
        }
        return c;   //On retourne la case temporaire qui sera traitée dirrérement si elle est toujours (null) ou si elle à pris une case en adresse.
    }
    
}

