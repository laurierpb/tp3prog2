package tp3;


import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;

/**
 * Calcul de la TPS et TVQ.
 *
 * @author Louise Laforest
 * @version fevrier 2006, modifiee par Melanie Lord novembre 2013
 */
public class CalculTpsTvqAvecBoutonsRadio {

    /**
     * *************************
     * ATTRIBUTS D'INSTANCE
   **************************
     */
    //composants graphiques
    private JFrame uneFenetre;
    private JPanel panneauBoutons;
    private JLabel labelMontant;
    private JTextField champTexteMontant;
    private JLabel labelTps;
    private JTextField champTexteTps;
    private JLabel labelTvq;
    private JTextField champTexteTvq;
    private JButton boutonCalculer;
    private JRadioButton boutonRadioSans;
    private JRadioButton boutonRadioTps;
    private JRadioButton boutonRadioTvq;
    private JRadioButton boutonRadioTpsTvq;

    /**
     * Constructeur d'initialisation des composants graphiques.
     */
    public CalculTpsTvqAvecBoutonsRadio() {
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
        uneFenetre = new JFrame("Calcul de taxes");
        uneFenetre.setBounds(500, 200, 200, 335);
        uneFenetre.setBackground(Color.GREEN);
        uneFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Pour positionner correctement les composants
        uneFenetre.setLayout(null);

        /**
         * *************************************
         * INIT ETIQUETTES
   	 **************************************
         */
        //Montant
        labelMontant = new JLabel("Montant");
        labelMontant.setBounds(10, 20, 80, 20);
        labelMontant.setHorizontalAlignment(JLabel.RIGHT);

        //TPS
        labelTps = new JLabel("TPS");
        labelTps.setBounds(labelMontant.getX(),
                labelMontant.getY() + labelMontant.getHeight() + 10,
                labelMontant.getWidth(), 20);
        labelTps.setHorizontalAlignment(JLabel.RIGHT);

        //TVQ
        labelTvq = new JLabel("TVQ");
        labelTvq.setBounds(labelTps.getX(),
                labelTps.getY() + labelTps.getHeight() + 10,
                labelTps.getWidth(), 20);
        labelTvq.setHorizontalAlignment(JLabel.RIGHT);

        /**
         * *************************************
         * INIT CHAMPS TEXTE
   	 **************************************
         */
        //montant
        champTexteMontant = new JTextField("0");
        champTexteMontant.setBounds(
                labelMontant.getX() + labelMontant.getWidth() + 10,
                labelMontant.getY(),
                50, labelMontant.getHeight());

        //TPS
        champTexteTps = new JTextField("");
        champTexteTps.setBounds(
                labelTps.getX() + labelTps.getWidth() + 10,
                labelTps.getY(),
                champTexteMontant.getWidth(),
                champTexteMontant.getHeight());
        champTexteTps.setEditable(false);//champ non editable

        //TVQ
        champTexteTvq = new JTextField();
        champTexteTvq.setBounds(
                labelTvq.getX() + labelTvq.getWidth() + 10,
                labelTvq.getY(),
                champTexteTps.getWidth(), champTexteTps.getHeight());
        //champ non editable
        champTexteTvq.setEditable(false);

        /**
         * *************************************
         * INIT BOUTONS RADIO
   	 **************************************
         */
        boutonRadioSans = new JRadioButton("Aucune taxes");
        boutonRadioTps = new JRadioButton("TPS seulement");
        boutonRadioTvq = new JRadioButton("TVQ seulement");
        boutonRadioTpsTvq = new JRadioButton("TPS et TVQ");
        boutonRadioSans.setSelected(true);

		//Ajout des boutons radion a un ButtonGroup 
        //pour empecher la selection de plus d'un bouton radio
        ButtonGroup groupeTaxes = new ButtonGroup();
        groupeTaxes.add(boutonRadioSans);
        groupeTaxes.add(boutonRadioTps);
        groupeTaxes.add(boutonRadioTvq);
        groupeTaxes.add(boutonRadioTpsTvq);

        /**
         * *************************************
         * INIT PANNEAU BOUTONS RADIO
   	 **************************************
         */
        panneauBoutons = new JPanel(new FlowLayout());
        ((FlowLayout) panneauBoutons.getLayout()).setAlignment(FlowLayout.LEFT);
        panneauBoutons.setBounds(20,
                labelTvq.getY() + labelTvq.getHeight() + 20,
                160, 130);
        panneauBoutons.setBackground(Color.GREEN);

        //Ajout des boutons radio au panneau
        panneauBoutons.add(boutonRadioSans);
        panneauBoutons.add(boutonRadioTps);
        panneauBoutons.add(boutonRadioTvq);
        panneauBoutons.add(boutonRadioTpsTvq);

        /**
         * *************************************
         * INIT BOUTON
   	 **************************************
         */
        boutonCalculer = new JButton("calculer taxes");
        boutonCalculer.setBounds(panneauBoutons.getX(),
                panneauBoutons.getY() + panneauBoutons.getHeight() + 20,
                panneauBoutons.getWidth(), 20);

        /**
         * *************************************
         * AJOUT DES COMPOSANTS A LA FENETRE
   	 **************************************
         */
        uneFenetre.getContentPane().add(panneauBoutons);
        uneFenetre.getContentPane().add(labelMontant);
        uneFenetre.getContentPane().add(champTexteMontant);
        uneFenetre.getContentPane().add(labelTps);
        uneFenetre.getContentPane().add(champTexteTps);
        uneFenetre.getContentPane().add(labelTvq);
        uneFenetre.getContentPane().add(champTexteTvq);
        uneFenetre.getContentPane().add(boutonCalculer);

        //Rendre la fenetre visible
        uneFenetre.setVisible(true);
    }

    public static void main(String[] args) {
        new CalculTpsTvqAvecBoutonsRadio();
    }

}
