/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048.model;

import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author oneiroi
 */
public class Grille implements Parametres {
    /* 
        Notre jeu fonctionne grace à une grille contenant plusieurs cases(16 au maximum), elles sont contenues dans un ensemble de Case(HashSet<Case>).
        La variable valeurMax permet de connaitre à un instant t la case avec la plus haute valeur.
        La variable score permet de connaitre le score actuel de la grille.
    */
    private HashSet<Case> grille;
    private int valeurMax;
    private int score=0;
    
    
    /**
     * Constructeur de Grille
     * Lors de sa définition, une grille contient un ensemble vide donc il n'y a pas de case. La valeurMax est alors de 0.
     */
    public Grille(){
        this.grille=new HashSet<>();
        valeurMax=0;
    }

    /**
     * Cette méthode permet de retourner l'ensemble de case de la grille actuelle.
     * @return 
     */
    public HashSet<Case> getGrille() {
        return grille;
    }

    /**
     * Méthode qui retourner la valeur maximale dans la grille
     * @return 
     */
    public int getValeurMax() {
        return valeurMax;
    }
    
    /**
     * Méthode qui modifie la valeur maximale dans la grille
     * @param v 
     */
    public void setValeurMax(int v){
        this.valeurMax = v;
    }
    
    /**
     * Méthode qui retourne le score actuel de la grille
     * @return 
     */
    public int getScore(){
        return this.score;
    }
    
    /**
     * Méthode servant à afficher dans la console, l'étât de toutes les variables importantes d'une Grille.
     * @return 
     */
    public String toString(){
        String str;
        str="La taille est: "+this.grille.size()+"La valeur max de la grille est "+this.valeurMax+"\n"+this.getCases();
        
        return str;
    }
    
    /**
     * Méthode qui permet d'afficher l'étât actuel de la position des cases dans la grille
     * @return 
     */
    public String getCases(){
        String str="";
            for(int j=0;j>-4;j--){
                str+="["+this.getCase(0,j).getValeur();
                for(int i=1;i<4;i++){
                    str+=","+this.getCase(i,j).getValeur();
                }
                str+="]\n";
            }
            
            
        
        return str;
    }
    
    /**
     * Cette méthode permet de remettre toutes la variables fusion de toutes les cases à faux.
     */
    public void setFusionOff(){
        for(int j=0;j>-4;j--){
                this.getCase(0,j).setFusion(false);
                for(int i=1;i<4;i++){
                    this.getCase(i,j).setFusion(false);
                }
            }
    }
    
    /**
     * Retourne la case présente à la position {a,o} si elle existe.
     * @param a
     * @param o
     * @return 
     */
    public Case getCase(int a,int o){
        Case cF=new Case(a,o,0);
        for(Case c :this.grille){
            if(c.getX()==a&&c.getY()==o){
                cF= c;
            }
        }
        return cF;
    }
    
    /**
     * Méthode qui affiche un message de victoire dans la console avec la valeur maximale de la grille, soit 2048 pour une victoire.
     */
    public void victory(){
    
        System.out.println("Vous avez gagné : "+this.getValeurMax());
        System.exit(0);
    }
    
    /**
     * Méthode qui affiche un message de défaite dans la console avec la valeur maximale de la grille.
     * Ce message indique qu'il n'y a plus de mouvement possible.
     */
    public void gameOver(){
        System.out.println("Vous avez perdu : "+this.getValeurMax());
        System.exit(0);
    }
    
    /**
     * Méthode qui crée une nouvelle case de valeur 2 ou 4 et la place de manière aléatoire dans une case libre de la grille.
     * Elle retourner un booleen pour confirmer ou non la création de la case.
     * @return 
     */
    public boolean nouvelleCase(){
        boolean b=false;
        Random ra=new Random();
        int valeurNC;
        if(grille.size()<(taille*taille)){  //Si le nombre de case dans la grille est inférieur au nombre de cases possibles(donc que l'on peut ajouter une case) :
            valeurNC=(2*(1+ra.nextInt(2))); //Valeur aléatoire de la nouvelle case entre 2 ou 4.
            int a = ra.nextInt(4);          //Abscisse aléaoire de la nouvelle case 0,1,2 ou 3.
            int o = (-1)*ra.nextInt(4);     //Ordonnée aléaoire de la nouvelle case 0,-1,-2 ou -3.
            Case c=new Case(a,o,valeurNC);  //On applique les trois valeurs aléatoires ci-dessus à une case grâce à son constructeur.
            c.setMaGrille(this);            //Et on ajoute notre grille à la case.
            
            //Tant que l'on a pas reussi à ajouter la case
            while(!b){
                boolean b1=false;           //le booleen b1 prends la valeur fausse par défault.
                for(Case c1 : this.grille){ //Pour toutes les cases de l'ensemble.
                    if(c.egals(c1)){        //Si une case à les même coordonnées.  
                        b1=true;            //le booleen b1 prends la valeur vrai.
                    }
                }
                if(!b1){                    //Si b1 n'est pas vrai, alors cela veut dire que l'emplacement choisi au hasard est libre.
                    this.grille.add(c);     //Donc on ajoute la case à l'ensemble des cases présentes dans la grille.
                    
                    //Pour toute les cases de la grille, on vérifie que sa valeur n'est pas plus grande que la valeur maximale de la grille
                    //Si elle l'est, la valeur maximale de la grille devient la valeur de cette case.
                    for(Case c1 : grille){  
                        if(c1.getValeur()>this.valeurMax){
                            this.valeurMax = c1.getValeur();
                        }
                    }
                    
                    b=true;                 //b passe à la valeur vraie pour valider l'ajout de la case et sortir du while
                }else{                      //Si b1 est vrai alors les valeurs choisies au hasard ne sont pas disponible, il faut donc en choisir de nouvelles:
                    a = ra.nextInt(4);
                    o = (-1)*ra.nextInt(4); 
                    c.setX(a);
                    c.setY(o);
                }
            }
        }
        
        for(Case c1:grille){
            for(Case c2:grille){
                if(c1.egals(c2)){
                    if(c1.getValeur()<c2.getValeur()){
                        grille.remove(c1);
                    }else{
                        if(c1.getValeur()>c2.getValeur()){
                            grille.remove(c2);
                        }
                        
                    }
                }
            }
        }
        
        
        return b;
    }
    
    
    /**
     * Cette méthode determine si une partie est finie ou non et retourne le resultat sous la forme d'un booleen.
     * @return 
     */
    public boolean partieFinie(){
        boolean b=true;                             //On force b à vrai
        
        if(this.grille.size()==(taille*taille)){    //Si l'ensemble des cases est égal au nombre de cases maximum possible dans la grille
            
            for(Case c : this.grille){              //Pour toutes les cases,
                int i =gauche;                      //i prends la valeur gauche puis sera incrémenter afin de passer par les 4 directions
                Case c1;                            // On crée une case vide c1
                while(i<3&&b){                      //Tant que i est plus petit que 3 et que b est à Vrai
                    c1=c.getVoisinDirect(i);        //c1 prends la valeur du voisin direct à i
                    
                    //Si c1 n'est plus nulle, donc qu'il y a une case du coté i
                    if(c1!=null){
                        System.out.println(c.toString()+"\n"+i+" : "+c1.toString()); 
                        //Si c et c1 on la même valeur, 
                        if(c.valeurEgale(c1)){
                            b=false;//alors b passe à faux car on peut fusionner ces deux cases en cas de mouvement dans la bonne direction.
                        }       
                    }
                    i++;    
                    if(i==0){   //Si i vaut 0, il n'y a pas de direction associé alors on incrémente à nouveau.
                        i++;
                    }
                }
            }
        }else{  //si la grille n'est pas pleine alors la partie n'est pas finie
            b=false;
        }
        return b;
    }
    
    /**
     * Cette méthode permet de gérer le déplacement des tuiles lors d'un mouvement.
     * @param d 
     */
    public void seDeplacer(int d){
        
        for(Case c1:grille){
            for(Case c2:grille){
                if(c1.egals(c2)){
                    if(c1.getValeur()<c2.getValeur()){
                        grille.remove(c1);
                    }else{
                        if(c1.getValeur()>c2.getValeur()){
                            grille.remove(c2);
                        }
                    }
                }
            }
        }
        
        Case c;
        switch(d){
            case haut:
                for(int i=0;i<taille;i++){
                    for(int j=-1;j>-taille;j--){
                        if(this.getCase(i, j).getValeur()!=0){
                            c=this.getCase(i, j);
                            this.grille.remove(c);
                            if(c.valeurEgale(c.getVoisinDirect(d))&&!c.getVoisinDirect(d).isFusion()){
                                c.getVoisinDirect(d).setValeur(c.getVoisinDirect(d).getValeur()*2);
                                this.score+=c.getVoisinDirect(d).getValeur();
                                c.getVoisinDirect(d).setFusion(true);
                                
                                if(c.getVoisinDirect(d).getValeur()>this.valeurMax){
                                    this.valeurMax = c.getVoisinDirect(d).getValeur();
                                }
                            }else{
                                if(c.getVoisinDirect(d)==null){
                                    c.setY(0);
                                }else{
                                    if(c.getY()!=(c.getVoisinDirect(d).getY()-1)){
                                        c.setY(c.getVoisinDirect(d).getY()-1);
                                    }
                                }
                                grille.add(c);
                            }
                            
                            
                            
                        }
                        
                    }
                }
                
                break;
            case droite:
                
                for(int i=0;i>-taille;i--){
                    for(int j=taille-2;j>-1;j--){
                        if(this.getCase(j, i).getValeur()!=0){
                            c=this.getCase(j, i);
                            this.grille.remove(c);
                            
                            if(c.valeurEgale(c.getVoisinDirect(d))&&!c.getVoisinDirect(d).isFusion()){
                                c.getVoisinDirect(d).setValeur(c.getVoisinDirect(d).getValeur()*2);
                                this.score+=c.getVoisinDirect(d).getValeur();
                                c.getVoisinDirect(d).setFusion(true);
                                if(c.getVoisinDirect(d).getValeur()>this.valeurMax){
                                    this.valeurMax = c.getVoisinDirect(d).getValeur();
                                }
                                
                            }else{
                                if(c.getVoisinDirect(d)==null){
                                    c.setX(taille-1);
                                }else{
                                    if(c.getX()!=(c.getVoisinDirect(d).getX()-1)){
                                        c.setX(c.getVoisinDirect(d).getX()-1);
                                    }

                                }
                                grille.add(c);
                            }
                            
                            
                            
                        }
                        

                    }
                }
                
                break;
            case bas:
                
                for(int i=0;i<taille;i++){
                    for(int j=(-taille)+2;j<1;j++){
                        if(this.getCase(i, j).getValeur()!=0){
                            c=this.getCase(i, j);
                            this.grille.remove(c);
                            if(c.valeurEgale(c.getVoisinDirect(d))&&!c.getVoisinDirect(d).isFusion()){
                                c.getVoisinDirect(d).setValeur(c.getVoisinDirect(d).getValeur()*2);
                                this.score+=c.getVoisinDirect(d).getValeur();
                                c.getVoisinDirect(d).setFusion(true);
                                if(c.getVoisinDirect(d).getValeur()>this.valeurMax){
                                    this.valeurMax = c.getVoisinDirect(d).getValeur();
                                }
                            }else{
                                if(c.getVoisinDirect(d)==null){
                                    c.setY(-taille+1);
                                }else{
                                    if(c.getY()!=(c.getVoisinDirect(d).getY()+1)){
                                        c.setY(c.getVoisinDirect(d).getY()+1);
                                    }

                                }
                                grille.add(c);
                            }
                            
                            
                        }
                    }
                }
                break;
            case gauche:
                
                for(int i=0;i>-taille;i--){
                    for(int j=1;j<taille;j++){
                        if(this.getCase(j, i).getValeur()!=0){
                            c=this.getCase(j, i);
                            this.grille.remove(c);
                            if(c.valeurEgale(c.getVoisinDirect(d))&&!c.getVoisinDirect(d).isFusion()){
                                c.getVoisinDirect(d).setValeur(c.getVoisinDirect(d).getValeur()*2);
                                this.score+=c.getVoisinDirect(d).getValeur();
                                c.getVoisinDirect(d).setFusion(true);
                                if(c.getVoisinDirect(d).getValeur()>this.valeurMax){
                                    this.valeurMax = c.getVoisinDirect(d).getValeur();
                                }
                            }else{
                                if(c.getVoisinDirect(d)==null){
                                    c.setX(0);
                                }else{
                                    if(c.getX()!=(c.getVoisinDirect(d).getX()+1)){
                                        c.setX(c.getVoisinDirect(d).getX()+1);
                                    }

                                }
                                grille.add(c);
                            }
                            
                            
                        }
                    }
                }
                break;
        }
        this.setFusionOff();
        
    }
    private void fusion(Case c){
        c.setValeur(c.getValeur()*2);
        if(c.getValeur()>this.valeurMax){
            this.valeurMax = c.getValeur();
        }
    }
    
    public Case[] getCasesExtremites(int direction){
        Case[] c= new Case[4];
        switch(direction){
            case haut:
                for(int i=0;i<taille;i++){
                        c[i]=this.getCase(i, 0).getVoisinDirect(-direction);
                }
                break;
            case droite:
                for(int i=0;i>-taille;i--){
                    c[-i]=this.getCase(taille-1, i).getVoisinDirect(-direction);
                }    
                break;
            case bas:
                for(int i=0;i<taille;i++){
                    c[i]=this.getCase(i,-taille+1).getVoisinDirect(-direction);
                }
                break;
            case gauche:
                for(int i=0;i>-taille;i--){
                    c[-i]=this.getCase(0,i).getVoisinDirect(-direction);
                }
                break;   
             
        }
        return c;
    }
    
    public boolean lanceurDeplacerCases(int direction){
        boolean b=false;
        switch(direction){
            case haut:
                break;
            case droite:
                break;
            case bas:
                break;
            case gauche:
                break;
        }
        return b;
    }
    
    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur){
        for(int i=0; i<extremites.length;i++){
            this.grille.remove(extremites[i]);
            if(extremites[i].getVoisinDirect(direction)==null){
                                extremites[i].setY(-taille+1);
                            }else{
                                if(extremites[i].getY()!=(extremites[i].getVoisinDirect(direction).getY()+1)){
                                    extremites[i].setY(extremites[i].getVoisinDirect(direction).getY()+1);
                                }
                                
                            }
            this.grille.add(extremites[i]);
        }
    }
}
