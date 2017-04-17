/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048;

/**
 *
 * @author louis
 */
public abstract class AI {
    private static boolean interuption= true;
    
    public static void resolveTheGame(Fenetre2048 mainApp, Thread t) throws InterruptedException{
        //tant que la partie n'est pas finie ou que l'IA n'est pas mise sur pause
        while(!AI.interuption||!mainApp.getMaGrille().partieFinie()){
            System.out.println("Test du while");
            t.sleep(1000);
        }
        
    }
    
    public static String resolveOneTurn(Fenetre2048 mainApp){
        String direction=null;
        
        return direction;
    }
    
    public static void setInteruption(boolean interuption){
        AI.interuption=interuption;
    }
    
    public static boolean getInteruption(){
        return AI.interuption;
    }
}

