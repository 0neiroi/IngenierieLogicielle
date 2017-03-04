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
public interface Parametres {
        //Gestion des directions grâce à des int
    final int  haut=1;
    final int  bas=-1;
    final int gauche=-2;
    final int droite=2;
    
        //Gestion des touches possiblement utilisées pour les mouvements (interface)
    final String UP="w";
    final String DOWN="s";
    final String LEFT="a";
    final String RIGHT="d";
    
    final int taille=4; //Taille du tableau (4x4)
    final int objectif=2048;    //Objectif de victoire du jeu (2048, 4096, ...)
}
