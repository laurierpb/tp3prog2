package tp3;

import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.border.Border;

public class TP3 extends WindowAdapter implements ActionListener {

    /**
     * **********************
     * CONSTANTES **********************
     */
    //chemin du fichier d'enregistrement de la configuration de l'editeur
    public final static String CONFIG_CHEMIN_FIC = "./config.txt";

    //dimensions pour la fenetre principale
    public final static int LARGEUR_FENETRE = 600;
    public final static int HAUTEUR_FENETRE = 540;
    public final static int LARG_ECRAN = Toolkit.getDefaultToolkit().getScreenSize().width;
    public final static int HAUT_ECRAN = Toolkit.getDefaultToolkit().getScreenSize().height;

    //couleurs
    public final static Color NOIR = Color.BLACK;
    public final static Color BLANC = Color.WHITE;
    public final static Color JAUNE = new Color(253, 246, 63);
    public final static Color ROUGE = new Color(201, 0, 0);
    public final static Color BLEU = new Color(40, 79, 206);
    public final static Color BLEU_PALE = new Color(131, 197, 253);
    public final static Color VERT = new Color(44, 155, 1);
    public final static Color VERT_PALE = new Color(175, 248, 1);
    public final static Color ORANGE = new Color(246, 122, 0);
    public final static Color ROSE = new Color(255, 180, 208);
    public final static Color GRIS = Color.LIGHT_GRAY;

    //Titre de la fenetre de l'editeur
    public final static String TITRE_FENETRE = "MINI ÉDITEUR";
    public final static String TITRE_FENETRE_CONFIG = "CONFIGURATION";

    public String[] listeCaract = {"Arial", "Courier", "Lucida Grande", "Time"};
    public String[] listeCouleur = {"Noir", "Blanc", "Jaune", "Rouge", "Rose",
        "Bleu", "Bleu pâle", "Vert", "Vert pâle", "Orange", "Gris"};
    /**
     * **********************
     * ATTRIBUTS D'INSTANCE **********************
     */
    private JFrame fenetre;
    private JFrame fenetreConfig;
    private JPanel menu;
    private JPanel text;
    private JPanel fichierPan;
    private JButton nouveau;
    private JButton charger;
    private JButton sauvgarder;
    private JButton configuration;

    /**
     * Constructeur sans argument qui initialise tous les composants graphiques.
     */
    public TP3() {
        init();
    }

    /**
     * @Initialisation de l'interface graphique.
     */
    private void init() {

        /*
        * FENETRE
        */
        fenetre = new JFrame(TITRE_FENETRE);
        fenetre.setBounds(LARG_ECRAN / 2 - LARGEUR_FENETRE / 2, HAUT_ECRAN / 2 - HAUTEUR_FENETRE / 2, LARGEUR_FENETRE, HAUTEUR_FENETRE);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(null);
        fenetre.setResizable(false);

        nouveau = new JButton("Nouveau");
        charger = new JButton("Charger");
        sauvgarder = new JButton("Sauvgarder");
        configuration = new JButton("Configuration");

        menu = new JPanel();
        menu.setBounds(10, 5, LARGEUR_FENETRE - 25, 40);
        menu.add(nouveau);
        menu.add(charger);
        menu.add(sauvgarder);
        menu.add(configuration);
        menu.doLayout();

        text = new JPanel();
        text.setBounds(10, menu.getHeight(), LARGEUR_FENETRE - 25, HAUTEUR_FENETRE - (menu.getHeight() + 65));
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(text.getSize()));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        textArea.setLineWrap(true);
        JLabel fichier = new JLabel("Fichier : ");
        JPanel fichierPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fichierPan.setBounds(10,477,LARGEUR_FENETRE - 25, 25);
        fichierPan.add(fichier);
        
        text.add(scrollPane);
        
        fenetre.add(menu);
        fenetre.add(text);
        fenetre.add(fichierPan);
        
        fenetre.setVisible(true);
        
        
        
        /*
        * CONFIGURATION
        */
        JPanel top = new JPanel();
        JPanel bot = new JPanel();
        
        //TOP
        fenetreConfig = new JFrame(TITRE_FENETRE_CONFIG);
        fenetreConfig.setLayout(null);
        fenetreConfig.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fenetreConfig.setBounds(fenetre.getX() + fenetre.getWidth(), fenetre.getY(), 300, fenetre.getHeight());
        fenetreConfig.setResizable(false);
        
        top.setBounds(10, 10, 275, 190);
        top.setBorder(BorderFactory.createLineBorder(NOIR));
        JLabel policeCaract = new JLabel("POLICE DE CARACTÈRES                                        ");
        
        JPanel policeContent = new JPanel();
        policeContent.setSize(top.getWidth(), top.getHeight() - 30);
        
        policeContent.setLayout(new GridLayout(3, 2, -45, 10));
        JLabel nom = new JLabel("Nom");
        JLabel taille = new JLabel("Taille");
        JLabel couleur = new JLabel("Couleur");
        JComboBox courier = new JComboBox(listeCaract);
        courier.setSelectedIndex(1);
        JTextField textTaille = new JTextField("12");
        JComboBox couleurBox = new JComboBox(listeCouleur);
        
        policeContent.add(nom);
        policeContent.add(courier);
        policeContent.add(taille);
        policeContent.add(textTaille);
        policeContent.add(couleur);
        policeContent.add(couleurBox);
        
        JRadioButton normal = new JRadioButton("Normal     ");
        JRadioButton gras = new JRadioButton("Gras     ");
        JRadioButton italique = new JRadioButton("Italique     ");
        normal.setSelected(true);
        
        ButtonGroup groupeRadio = new ButtonGroup();
        groupeRadio.add(normal);
        groupeRadio.add(gras);
        groupeRadio.add(italique);
        
        JPanel panelRadio = new JPanel(new FlowLayout());
        panelRadio.add(normal);
        panelRadio.add(gras);
        panelRadio.add(italique);
        
        top.add(policeCaract);
        top.add(policeContent);
        top.add(panelRadio);
        
        
        
        bot.setBounds(10, top.getHeight() + 20, 275, 290);
        bot.setBorder(BorderFactory.createLineBorder(NOIR));
        
        
        //BOT
        JLabel options = new JLabel("OPTIONS                                                                    ");
        JTextField couleurs = new JTextField("Couleurs                                                                     ");
        couleurs.setBackground(BLANC);
        couleurs.setEditable(false);
        JPanel couleursPan = new JPanel();
        couleursPan.add(couleurs);
        
        JPanel couleursContent = new JPanel();
        couleursContent.setSize(top.getWidth(), top.getHeight() - 30);
        
        couleursContent.setLayout(new GridLayout(4, 2, 30, 5));
        JLabel fond = new JLabel("Fond");
        JLabel textSelectionne = new JLabel("Texte selectionné");
        JLabel selectionTexte = new JLabel("Selection texte");
        JLabel curseur = new JLabel("Curseur");        
        
        JComboBox fondBox = new JComboBox(listeCouleur);
        JComboBox texteSelectionneBox = new JComboBox(listeCouleur);
        JComboBox selectionTexteBox = new JComboBox(listeCouleur);
        JComboBox curseurBox = new JComboBox(listeCouleur);
        
                   ");
        autre.setBackground(BLANC);
        autre.setEditable(false);
        
        JPanel autresContent = new JPanel();
        autresContent.setSize(top.getWidth(), 200);
        autresContent.setLayout(new GridLayout(2, 2, 10, 5));
        
        JCheckBox retourALaLigne = new JCheckBox("Retour à la ligne");
        retourALaLigne.setSelected(true);
        JLabel tabulation = new JLabel(" Long. tabulation");
        JTextField valeurTabulation = new JTextField("3");
        
        autresContent.add(retourALaLigne);
        autresContent.add(new JLabel());
        autresContent.add(tabulation);
        autresContent.add(valeurTabulation);
        
        bot.add(options);
        bot.add(couleursPan);
        bot.add(couleursContent);
        bot.add(autre);
        bot.add(autresContent);
        
        fenetreConfig.add(top);
        fenetreConfig.add(bot);
        fenetreConfig.setVisible(true);
    }

    /**
     * **********************************************
     * IMPLEMENTATION DE L'INTERFACE ActionListener
     * **********************************************
     */
    /**
     * Gestion des evenements ActionEvent.
     *
     * @param e l'evenement intercepte.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //A COMPLETER
    }

    /**
     * **************************
     * METHODES PRIVEES **************************
     */
    /**
     * Sauvegarde la configuration de l'editeur dans le fichier
     * CONFIG_CHEMIN_FIC.
     */
    private void sauvegarderConfig() {
        //A COMPLETER
    }

    /**
     * ********************************************************************
     * REDEFINITION DE LA METHODE windowClosing DE LA CLASSE WindowAdapter
     * ********************************************************************
     */
    /**
     * A la fermeture de la fenetre (avec le [x]) : enregistrement de la
     * configuration de l'editeur dans le fichier CONFIG_CHEMIN_FIC.
     *
     * @param e l'evenement intercepte (fermeture fenetre).
     */
    @Override
    public void windowClosing(WindowEvent e) {
        sauvegarderConfig();
    }

    /**
     * Initialisation de l'application.
     */
    public static void main(String[] args) {
        new TP3();
    }

}
