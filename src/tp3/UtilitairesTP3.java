package tp3;


import javax.swing.JFileChooser;
import java.io.*;

/**
 * Methodes de classe a utiliser pour le TP3.
 * @author Melanie Lord
 * @version Hiver 2014
 */
public class UtilitairesTP3 {
   
   //Constantes pour le titre du selecteur de fichier, selon qu'on ouvre ou 
   //qu'on enregistre un fichier.
   public final static String OUVRIR = "Ouvrir";
   public final static String SAUVEGARDER = "Sauvegarder";
   
  
   /**
    * Ouvre une fenetre d'enregistrement d'un fichier. Si le cheminFic donne
    * est null, la fentre d'enregistrement s'ouvre dans le dossier personnel
    * (le "home") de l'utilisateur sinon, elle s'ouvre dans le dossier parent
    * du cheminFic donne et le nom du fic donne est deja ecrit dans la fenetre.
    * 
    * Si l'operation n'est pas annulee (l'utilisateur choisit d'enregistrer), 
    * le texte donne est ecrit dans le fichier selectionne dans la fenetre 
    * d'enregistrement. Si le fichier selectionne existait, son contenu est ecrase 
    * par texte. Si l'operation est annulee, le texte n'est pas ecrit et l'operation 
    * se termine. 
    * 
    * @param texte le texte a ecrire dans le fichier selectionne dans la fenetre
    *        d'enregistrement.
    * @param cheminFic le chemin du fichier selectionne a l'ouverture de la fenetre
    *        d'enregistrement (Antecedent : doit etre un fichier valide et non 
    *        un dossier).
    * @return le chemin du fichier selectionne dans la fenetre d'enregistrement 
    *         ou null si l'operation a ete annulee.
    * @throws IOException s'il se produit une erreur d'entrÃ©e/sortie inattendue.
    */
   public static String sauvegarder (String texte, String cheminFic) 
           throws IOException {
      int reponse;
      String nouveauCheminFic = null;
      File f;
      PrintWriter out;
      JFileChooser chooser;
      
      //creer le selecteur de fichier
      chooser = new JFileChooser();
      chooser.setDialogTitle(SAUVEGARDER);
      
      //s'ouvre dans le dossier parent du cheminFic donne
      //sinon, dans le home de l'utilisateur
      if (cheminFic != null) {
         f = new File(cheminFic);
         chooser.setSelectedFile(f);
      }
      reponse = chooser.showSaveDialog(null);
      f = chooser.getSelectedFile();
      
      //L'operation n'a pas ete annulee
      if (reponse == JFileChooser.APPROVE_OPTION) {
         nouveauCheminFic = f.getAbsolutePath();
         
         //ecriture de texte dans le fichier selectionne
         out = new PrintWriter(new FileWriter(f));
         out.println(texte);
         out.close();
      } 
      //le chemin du fichier sauvegarde (ou null si op. annulee)
      return nouveauCheminFic;
   }
   

   /**
    * Permet de selectionner un fichier dans un selecteur de fichier. 
    * @param cheminDossierCourant le chemin du dossier dans lequel s'ouvrira le 
    *        selecteur de fichier (JFileChooser). Si null ou non valide, le 
    *        le selecteur s'ouvrira dans le "home" de l'utilisateur. 
    * @return le chemin du fichier selectionne ou null si l'operation est 
    *         annulee.
    */
   public static String selectionnerFichier (String cheminDossierCourant) {
      int reponse;
      String cheminFic = null;
      File f;
      JFileChooser chooser;
      
      //creation du selecteur de fichier
      chooser = new JFileChooser();
      
      //ouvrir le selecteur dans le dossier courant donne (cheminDossierCourant) 
      if (cheminDossierCourant != null) {
         chooser.setCurrentDirectory(new File(cheminDossierCourant));
      }
      chooser.setDialogTitle(OUVRIR);
      reponse = chooser.showOpenDialog(null);
      
      //l'operation n'a pas ete annulee
      if (reponse == JFileChooser.APPROVE_OPTION) {   
         f = chooser.getSelectedFile();
         cheminFic = f.getAbsolutePath();
      } 
      //chemin du fichier selectionne ou null si op. annulee.
      return cheminFic; 
   }
      

   /**
    * Retourne le texte contenu dans le fichier cheminFic donne.
    * @param cheminFichier le chemin du fichier dont on veut lire le contenu.
    * @return le texte contenu dans le fichier cheminFic donne.
    * @throws IOException si le fichier cheminFic donne est non valide ou ne
    *         peut pas etre lu.
    */
   public static String lireFichier (String cheminFic) throws IOException {
      String texte = "";
      BufferedReader in = new BufferedReader(new FileReader(cheminFic));
      while (in.ready()) {
         texte = texte + in.readLine() + "\n";
      }
      in.close();
      return texte;
   }

   
   //Pour tests seulement. Exemples :
   public static void main (String [] args) throws IOException {
      
      //Notes : 
      //   - modifier les chemins de fichiers ou dossiers selon l'arborescence
      //     des fichiers sur votre ordinateur.
      //   - Tester un exemple Ã  la fois (laisser les autres en commentaires)
      
      sauvegarder("je suis un texte Ã  sauvegarder.", "/Users/mel/Desktop/test.txt");
      //sauvegarder("je suis un texte Ã  sauvegarder.", null);
      
      //System.out.println(selectionnerFichier(null));
      //System.out.println(selectionnerFichier("/Users/mel/Desktop"));
      
      //System.out.println(lireFichier("/Users/mel/Desktop/test.txt"));
      
   }
   
}