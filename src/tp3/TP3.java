package tp3;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class TP3 extends WindowAdapter implements ActionListener {
   
   /************************
    * CONSTANTES
    ************************/
   //chemin du fichier d'enregistrement de la configuration de l'editeur
   public final static String CONFIG_CHEMIN_FIC = "./config.txt";
   
   //dimensions pour la fenetre principale
   public final static int LARGEUR_FENETRE = 600;
   public final static int HAUTEUR_FENETRE = 540;
   
   //largeur de l'ecran de l'ordinateur pour centrer l'application
   public final static int LARG_ECRAN = 
           Toolkit.getDefaultToolkit().getScreenSize().width;
   //hauteur de l'ecran de l'ordinateur pour centrer l'application
   public final static int HAUT_ECRAN = 
           Toolkit.getDefaultToolkit().getScreenSize().height;
   
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
   public final static String TITRE_FENETRE = "MINI Ã‰DITEUR";
   public final static String TITRE_FENETRE_CONFIG = "CONFIGURATION";

   
   /************************
    * ATTRIBUTS D'INSTANCE
    ************************/
   private JFrame fenetre;
   private JFrame fenetreConfig;
   
   //A COMPLETER
   
   /**
    * Constructeur sans argument qui initialise tous les composants graphiques.
    */
   public TP3() {
      //initialisation de l'interface graphique
      init();
   }
   
   /**
    * Initialisation de l'interface graphique.
    */
   private void init() {
      
      //FRAME FENETRE ET FENETRE CONFIG
      fenetre = new JFrame(TITRE_FENETRE);
      //centrer la fenetre principale de l'editeur
      fenetre.setBounds(LARG_ECRAN / 2 - LARGEUR_FENETRE / 2, 
              HAUT_ECRAN / 2 - HAUTEUR_FENETRE / 2, 
              LARGEUR_FENETRE, HAUTEUR_FENETRE);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      fenetre.setLayout(null);
      fenetre.setResizable(false);
      
      fenetreConfig = new JFrame("Configuration");
      fenetreConfig.setLayout(null);
      fenetreConfig.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      fenetreConfig.setBounds(fenetre.getX() + fenetre.getWidth(), 
              fenetre.getY(), 300, fenetre.getHeight());
      fenetreConfig.setResizable(false);
      
      //A COMPLETER...
      
      
      /***********************
       * AJOUT DES ECOUTEURS
       ***********************/
      
      //ajout d'un ecouteur a la fenetre 
      //(voir la redefinition de la methode windowClosing)
      fenetre.addWindowListener(this); 
      
      //A COMPLETER...
      
      /************************************************
       * RENDRE LA FENETRE PRINCIPALE VISIBLE
       ************************************************/
      //Cette instruction devra eventuellement etre enlevee car cette fenetre
      //ne doit pas apparaitre a l'initialisation de l'application, mais pendant
      //le developpement, il peut etre utile de la voir.
      fenetreConfig.setVisible(true); 
      
      //important : mettre cette instruction apres l'initialisation de tous 
      //les composants graphiques.
      fenetre.setVisible(true);
   }
   
   
   /************************************************
    * IMPLEMENTATION DE L'INTERFACE ActionListener
    ************************************************/
   /**
    * Gestion des evenements ActionEvent.
    * @param e l'evenement intercepte.
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      //A COMPLETER
   }
   
   
   /****************************
    * METHODES PRIVEES 
    ****************************/
   /**
    * Sauvegarde la configuration de l'editeur dans le fichier CONFIG_CHEMIN_FIC.
    */
   private void sauvegarderConfig() {
      //A COMPLETER
   }
   
   
   /**********************************************************************
    * REDEFINITION DE LA METHODE windowClosing DE LA CLASSE WindowAdapter
    **********************************************************************/
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
   public static void main (String [] args) {
      new TP3();
   }
   
}
