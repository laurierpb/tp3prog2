
import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import javax.swing.*;
import java.io.*;

/**
 * Création une application graphique d’édition de texte avec l'ajout de
 * certaines fonctionalités de modification de police et autre. L'ouverture et
 * la sauvegarde de fichiers est comprise
 *
 * @author Laurier Paquette Berube
 * @author Francis Fleurissaint FLEF19099105
 * @version 21/01/2014
 */
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

    private String lienConfig;
    private JTextArea textArea;
    private JLabel fichier;
    private String nomFichier;
    private JComboBox courier;
    private JTextField textTaille;
    private JComboBox couleurBox;
    private JComboBox fondBox;
    private JComboBox texteSelectionneBox;
    private JComboBox selectionTexteBox;
    private JComboBox curseurBox;
    private JTextField valeurTabulation;
    private String tempTaille;
    private String tempFocus;
    private JRadioButton normal;
    private JRadioButton gras;
    private JRadioButton italique;
    private JCheckBox retourALaLigne;

    /**
     * Constructeur sans argument qui initialise tous les composants graphiques.
     */
    public TP3() {
        init();
        chargerConfiguration();
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
        nouveau.addActionListener(this);
        charger = new JButton("Ouvrir");
        charger.addActionListener(this);
        sauvgarder = new JButton("Sauvgarder");
        sauvgarder.addActionListener(this);
        configuration = new JButton("Configuration");
        configuration.addActionListener(this);

        menu = new JPanel();
        menu.setBounds(10, 5, LARGEUR_FENETRE - 25, 40);
        menu.add(nouveau);
        menu.add(charger);
        menu.add(sauvgarder);
        menu.add(configuration);
        menu.doLayout();

        text = new JPanel();
        text.setBounds(10, menu.getHeight(), LARGEUR_FENETRE - 25, HAUTEUR_FENETRE - (menu.getHeight() + 65));
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(text.getSize()));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        textArea.setLineWrap(true);
        nomFichier = "Nouveau";
        lienConfig = "";
        fichier = new JLabel("Fichier : " + nomFichier);
        JPanel fichierPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fichierPan.setBounds(10, 477, LARGEUR_FENETRE - 25, 25);
        fichierPan.add(fichier);

        text.add(scrollPane);

        fenetre.add(menu);
        fenetre.add(text);
        fenetre.add(fichierPan);
        fenetre.addWindowListener(this);
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
        textTaille = new JTextField("12");
        tempTaille = textTaille.getText();
        courier = new JComboBox(listeCaract);
        courier.addActionListener(this);

        textTaille.addActionListener(this);
        couleurBox = new JComboBox(listeCouleur);
        couleurBox.addActionListener(this);

        policeContent.add(nom);
        policeContent.add(courier);
        policeContent.add(taille);
        policeContent.add(textTaille);
        policeContent.add(couleur);
        policeContent.add(couleurBox);

        normal = new JRadioButton("Normal     ");
        normal.addActionListener(this);
        gras = new JRadioButton("Gras     ");
        gras.addActionListener(this);
        italique = new JRadioButton("Italique     ");
        italique.addActionListener(this);
        normal.setSelected(true);

        courier.setSelectedIndex(1);

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

        fondBox = new JComboBox(listeCouleur);
        fondBox.addActionListener(this);
        texteSelectionneBox = new JComboBox(listeCouleur);
        texteSelectionneBox.addActionListener(this);
        selectionTexteBox = new JComboBox(listeCouleur);
        selectionTexteBox.addActionListener(this);
        curseurBox = new JComboBox(listeCouleur);
        curseurBox.addActionListener(this);

        couleursContent.add(fond);
        couleursContent.add(fondBox);
        couleursContent.add(textSelectionne);
        couleursContent.add(texteSelectionneBox);
        couleursContent.add(selectionTexte);
        couleursContent.add(selectionTexteBox);
        couleursContent.add(curseur);
        couleursContent.add(curseurBox);

        JTextField autre = new JTextField("Autres                                                                     ");
        autre.setBackground(BLANC);
        autre.setEditable(false);

        JPanel autresContent = new JPanel();
        autresContent.setSize(top.getWidth(), 200);
        autresContent.setLayout(new GridLayout(2, 2, 10, 5));

        retourALaLigne = new JCheckBox("Retour à la ligne");
        retourALaLigne.setSelected(true);
        textArea.setWrapStyleWord(true);

        retourALaLigne.addActionListener(this);
        JLabel tabulation = new JLabel(" Long. tabulation");
        valeurTabulation = new JTextField("3");
        tempFocus = valeurTabulation.getText();
        valeurTabulation.addActionListener(this);

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
        //fenetreConfig.setVisible(true);

        textArea.requestFocus();
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

        if (e.getSource() == nouveau) {
            creerNouveau();
        } else if (e.getSource() == charger) {
            ouvrirFichier();
        } else if (e.getSource() == sauvgarder) {
            sauvegarderFichier();
        } else if (e.getSource() == configuration) {
            fenetreConfig.setLocation(fenetre.getX() + fenetre.getWidth(), fenetre.getY());
            fenetreConfig.setVisible(true);
        } else if (e.getSource() == courier) {

            textArea.setFont(trouverFont());

        } else if (e.getSource() == textTaille) {

            if (textTaille.getText().matches("[0-9]+")) {
                if (Integer.parseInt(textTaille.getText()) >= 10 && Integer.parseInt(textTaille.getText()) <= 100) {

                    textArea.setFont(trouverFont());
                    tempTaille = textTaille.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur  inattendue.\n"
                            + " Le nombre doit être 10 et 100 inclusivement", "Oups", JOptionPane.ERROR_MESSAGE);
                }
                textTaille.setText(tempTaille);
                textTaille.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Erreur  inattendue.\n"
                        + "La taille ne peut pas contenir de caractères, elle doit contenir seulement des nombres", "Oups", JOptionPane.ERROR_MESSAGE);
                textTaille.setText(tempTaille);
                textTaille.requestFocus();
            }
        } else if (e.getSource() == couleurBox) {
            textArea.setForeground(trouverCouleur(couleurBox));
        } else if (e.getSource() == fondBox) {
            textArea.setBackground(trouverCouleur(fondBox));
        } else if (e.getSource() == texteSelectionneBox) {
            textArea.setSelectedTextColor(trouverCouleur(texteSelectionneBox));
        } else if (e.getSource() == selectionTexteBox) {
            textArea.setSelectionColor(trouverCouleur(selectionTexteBox));
        } else if (e.getSource() == curseurBox) {
            textArea.setCaretColor(trouverCouleur(curseurBox));
        } else if (e.getSource() == valeurTabulation) {

            if (valeurTabulation.getText().matches("[0-9]+")) {
                if (Integer.parseInt(valeurTabulation.getText()) >= 2 && Integer.parseInt(valeurTabulation.getText()) <= 20) {
                    textArea.setTabSize(Integer.parseInt(valeurTabulation.getText()));
                    tempFocus = valeurTabulation.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur  inattendue.\n"
                            + " Le nombre doit être 2 et 20 inclusivement", "Oups", JOptionPane.ERROR_MESSAGE);
                    valeurTabulation.setText(tempFocus);
                    valeurTabulation.requestFocus();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Erreur  inattendue.\n"
                        + "La tabulation ne peut pas contenir de caractères, elle doit contenir seulement des nombres", "Oups", JOptionPane.ERROR_MESSAGE);
                valeurTabulation.setText(tempFocus);
                valeurTabulation.requestFocus();
            }
        } else if (e.getSource() == normal) {

            textArea.setFont(trouverFont());
        } else if (e.getSource() == gras) {

            textArea.setFont(trouverFont());
        } else if (e.getSource() == italique) {

            textArea.setFont(trouverFont());
        } else if (e.getSource() == retourALaLigne) {
            if (retourALaLigne.isSelected()) {
                textArea.setWrapStyleWord(true);
            } else {
                textArea.setWrapStyleWord(false);
            }
        }

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
        try {
            enregistrer(CONFIG_CHEMIN_FIC);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur  inattendue.\n"
                    + " Enregistrement impossible de la configuration de l'éditeur.", "Oups", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Enregistre les options de polices choisies dans un fichier texte nommé
     * config.txt, enregiste les options sous forme "Type":"selection".
     *
     * @param cheminFic le lien du fichier a enregistrer
     * @throws IOEException s'il se produit une erreur lors de l'enregistrement
     */
    private void enregistrer(String cheminFic)
            throws IOException {
        File f;
        PrintWriter out;

        f = new File(cheminFic);

        //ecriture de texte dans le fichier selectionne
        out = new PrintWriter(new FileWriter(f));
        out.println("Nom police :" + courier.getSelectedItem().toString());
        out.println("Taille police :" + textTaille.getText());
        out.println("Couleur police :" + couleurBox.getSelectedItem().toString());
        if (normal.isSelected()) {
            out.println("Type police :" + "normal");
        } else if (gras.isSelected()) {
            out.println("Type police :" + "gras");
        } else if (italique.isSelected()) {
            out.println("Type police :" + "italique");
        }
        out.println("Couleur fond :" + fondBox.getSelectedItem().toString());
        out.println("Couleur texte selec :" + texteSelectionneBox.getSelectedItem());
        out.println("Couleur selection :" + selectionTexteBox.getSelectedItem().toString());
        out.println("Couleur curseur :" + curseurBox.getSelectedItem().toString());

        if (retourALaLigne.isSelected()) {
            out.println("retour ligne :" + "true");
        } else {
            out.println("retour ligne :" + "false");
        }

        out.println("longueur tab :" + valeurTabulation.getText().toString());
        out.close();

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
        //verifierConfig();
    }

    /**
     * Reinitialise la zone de texte pour la création d'un nouveau fichier et
     * redonne le focus a la zone de texte
     *
     */
    private void creerNouveau() {
        textArea.setText(null);
        fichier.setText("Fichier : Nouveau");
        textArea.requestFocus();
    }

    /**
     * Demande a l'utilisateur un fichier et l'ouvre dans la zone de texte
     * affiche un message d'erreur si il y a une erreur lors de 
     * l'ouverture du fichier 
     */
    private void ouvrirFichier() {

        String reponse;
        int position = 0;

        if (fichier.getText().equals("Fichier : Nouveau")) {
            reponse = UtilitairesTP3.selectionnerFichier(null);
        } else {
            reponse = UtilitairesTP3.selectionnerFichier(lienConfig);
        }

        try {
            textArea.setText(UtilitairesTP3.lireFichier(reponse));
            position = reponse.lastIndexOf('\\');
            nomFichier = reponse.substring(position + 1);
            lienConfig = reponse;
            fichier.setText("Fichier : " + nomFichier);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Erreur  inattendue.\n"
                    + " Ouverture du fichier impossible.", "Oups", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {

        }
    }

    /**
     * Demand a l'utilisateur ou il voudrait sauvegarder le texte dans la zone 
     * de texte, si c'est un fichier qui a ete ouvert, on lui offre de choisir 
     * a partir de l'emplacement du fichier
     *
     * afficher un message d'erreur si il y a un probleme lors de 
     * l'enregistrement du fichier
     */
    private void sauvegarderFichier() {
        try {
            if (lienConfig.equals("")) {
                UtilitairesTP3.sauvegarder(textArea.getText(), null);
            } else {
                UtilitairesTP3.sauvegarder(textArea.getText(), lienConfig);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur  inattendue.\n"
                    + " Enregistrement du fichier impossible.", "Oups", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Renvoie le bon type couleur selon la couleur choisie
     *
     * @param Jcombo element modifie qui envoie une couleur
     */
    private Color trouverCouleur(JComboBox Jcombo) {
        Color couleur = null;

        if (Jcombo.getSelectedItem().toString().equals(listeCouleur[0])) {
            couleur = Color.BLACK;

        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[1])) {
            couleur = Color.WHITE;
        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[2])) {
            couleur = Color.YELLOW;
        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[3])) {
            couleur = Color.RED;
        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[4])) {
            couleur = Color.PINK;
        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[5])) {
            couleur = Color.BLUE;
        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[6])) {
            couleur = new Color(31, 190, 214);
        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[7])) {
            couleur = Color.GREEN;
        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[8])) {
            couleur = new Color(144, 238, 144);
        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[9])) {
            couleur = Color.ORANGE;
        } else if (Jcombo.getSelectedItem().toString().equals(listeCouleur[10])) {
            couleur = Color.GRAY;
        }
        return couleur;
    }

    
    /**
     * Renvoie le bon type font selon la police choisie de l'element modifie
     *
     */
    private Font trouverFont() {
        Font font = null;
        String police = null;

        if (courier.getSelectedItem().equals("Arial")) {
            police = "Arial Unicode MS";
        } else if (courier.getSelectedItem().equals("Courier")) {
            police = "Courier New";
        } else if (courier.getSelectedItem().equals("Lucida Grande")) {
            police = "Lucida Bright";
        } else if (courier.getSelectedItem().equals("Time")) {
            police = "Times New Roman";
        }
        
        if (normal.isSelected()) {
            font = new Font(police, Font.PLAIN, Integer.parseInt(textTaille.getText()));
        } else if (gras.isSelected()) {
            font = new Font(police, Font.BOLD, Integer.parseInt(textTaille.getText()));
        } else if (italique.isSelected()) {
            font = new Font(police, Font.ITALIC, Integer.parseInt(textTaille.getText()));
        }

        return font;

    }

       /**
     * Charge les configuration par defaut si il n'y a pas de fichier de 
     * configuration sinon applique les choix du fichier de configuration au 
     * options
     *
     */
    private void chargerConfiguration() {
        String config = null;
       
        try {
            config = UtilitairesTP3.lireFichier(CONFIG_CHEMIN_FIC);
        } catch (FileNotFoundException e) {
            chargerDefaut();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur  inattendue.\n"
                    + " Chargement de la configuration de l'éditeur impossible.", "Oups", JOptionPane.ERROR_MESSAGE);
        }
        if (config != null) {
            chargerCustom(config);

        }

    }

    /**
     * Applique les options par defaut au panneau de configuration 
     * 
     */
    private void chargerDefaut() {

        courier.setSelectedItem("Courier");
        textTaille.setText("12");
        couleurBox.setSelectedItem("Noir");
        normal.setSelected(true);
        fondBox.setSelectedItem("Blanc");
        texteSelectionneBox.setSelectedItem("Noir");
        selectionTexteBox.setSelectedItem("Jaune");
        curseurBox.setSelectedItem("Noir");
        retourALaLigne.setSelected(true);
        valeurTabulation.setText("3");
        textArea.setFont(trouverFont());
    }

       /**
     * Charge les options lu dans le fichier texte dans le panneau de 
     * configuration
     *
     * @param config texte du fichier contenant les options
     */
    private void chargerCustom(String config) {

        String temp = null;

        courier.setSelectedItem(config.substring(config.indexOf(":") + 1, config.indexOf("\n")));
        config = config.substring(config.indexOf("\n") + 1);
        textTaille.setText(config.substring(config.indexOf(":") + 1, config.indexOf("\n")));
        config = config.substring(config.indexOf("\n") + 1);

        couleurBox.setSelectedItem(config.substring(config.indexOf(":") + 1, config.indexOf("\n")));
        config = config.substring(config.indexOf("\n") + 1);

        temp = config.substring(config.indexOf(":") + 1, config.indexOf("\n"));
        config = config.substring(config.indexOf("\n") + 1);
        if (temp.equals("normal")) {
            normal.setSelected(true);
        } else if (temp.equals("gras")) {
            gras.setSelected(true);
        } else if (temp.equals("italique")) {
            italique.setSelected(true);
        }

        fondBox.setSelectedItem(config.substring(config.indexOf(":") + 1, config.indexOf("\n")));
        config = config.substring(config.indexOf("\n") + 1);

        texteSelectionneBox.setSelectedItem(config.substring(config.indexOf(":") + 1, config.indexOf("\n")));
        config = config.substring(config.indexOf("\n") + 1);

        selectionTexteBox.setSelectedItem(config.substring(config.indexOf(":") + 1, config.indexOf("\n")));
        config = config.substring(config.indexOf("\n") + 1);

        curseurBox.setSelectedItem(config.substring(config.indexOf(":") + 1, config.indexOf("\n")));
        config = config.substring(config.indexOf("\n") + 1);

        temp = config.substring(config.indexOf(":") + 1, config.indexOf("\n"));
        config = config.substring(config.indexOf("\n") + 1);
        if (temp.equals("true")) {
            retourALaLigne.setSelected(true);
        } else {
            retourALaLigne.setSelected(false);
        }

        valeurTabulation.setText(config.substring(config.indexOf(":") + 1, config.indexOf("\n")));

        textArea.setFont(trouverFont());

    }
}
