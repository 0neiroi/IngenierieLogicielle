/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu2048;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author oneiroi
 */
public class Main implements Parametres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Grille maGrille=new Grille();
        Random ra=new Random();

        Scanner sc=new Scanner(System.in);
        maGrille.nouvelleCase();
        maGrille.nouvelleCase();
        while(!maGrille.partieFinie()&&maGrille.getValeurMax()<objectif){
            System.out.println(maGrille.toString());
            
            
            int i =0;
            boolean b=false;
            while(!b){
            switch(sc.next().charAt(0)){
                case 'h':
                        i=1;
                        b=true;
                    break;
                case 'd':
                    i=2;
                    b=true;
                    break;
                case 'b':
                    i=-1;
                    b=true;
                    break;
                case 'g':
                    i=-2;
                    b=true;
                    break;
                default:
                    System.out.println("Try again");
                    break;
                    
            }
        }
            HashSet<Case> grilleCopie=new HashSet<>();
            grilleCopie.addAll(maGrille.getGrille());
            
            
            
            maGrille.seDeplacer(i);
            if(!grilleCopie.equals(maGrille.getGrille())){
                maGrille.nouvelleCase();
            }
            
        }
        System.out.println("bite");
        if(maGrille.partieFinie()&&maGrille.getValeurMax()<objectif){
            maGrille.gameOver();
        }else{
            maGrille.victory();
        }
        
    }
    
}
