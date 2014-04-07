package tp3;

import java.awt.*;
import javax.swing.*;

/**
 * Clavier de telephone
 *
 * @author Louise Laforest, modifiee par Melanie Lord novembre 2013
 * @version novembre 2013
 */
public class Telephone {

    /**
     * *************************
     * ATTRIBUTS D'INSTANCE
   **************************
     */
    private JFrame fenetre = new JFrame("Telephone");
    private JPanel panneauPrincipal = new JPanel(new GridLayout(4, 3, 10, 10));
    private JButton[] clavier = new JButton[12];
    private JTextField ecran = new JTextField();

    /**
     * Constructeur d'initialisation des composants graphiques.
     */
    public Telephone() {
        init();
    }

    /**
     * Initialisation des composants graphiques.
     */
    private void init() {

        /**
         * *************************************
         * INIT FENETRE
     **************************************
         */
        fenetre.setBounds(600, 200, 200, 400);
        fenetre.setResizable(false);
        fenetre.setLayout(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * *************************************
         * INIT BOUTONS CLAVIER
     **************************************
         */
        for (int i = 0; i < 10; i++) {
            clavier[i] = new JButton(i + "");
        }
        clavier[10] = new JButton("*");
        clavier[11] = new JButton("#");

        /**
         * *************************************
         * INIT PANNEAU BOUTONS
     **************************************
         */
        //Ajout des boutons au panneau
        for (int i = 1; i < 10; i++) {
            panneauPrincipal.add(clavier[i]);
        }
        panneauPrincipal.add(clavier[10]);
        panneauPrincipal.add(clavier[0]);
        panneauPrincipal.add(clavier[11]);
        panneauPrincipal.setBounds(10, 60, 180, 280);
        panneauPrincipal.doLayout(); //rafraichir le panneau

        /**
         * *************************************
         * INIT ECRAN
     **************************************
         */
        ecran.setBounds(10, 10, 180, 30);
        ecran.setEditable(false);

        /**
         * *************************************
         * AJOUT DES COMPOSANTS A LA FENETRE
     **************************************
         */
        fenetre.getContentPane().add(panneauPrincipal);
        fenetre.getContentPane().add(ecran);

        //rendre la fenetre visible
        fenetre.setVisible(true);
    }

    public static void main(String[] args) {
        new Telephone();
    }
}
