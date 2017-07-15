package Views;/**
 * Created by chris on 14/07/2017.
 */

import Controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Models.*;

import java.io.IOException;

public class CodingTest extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public CodingTest(){    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Faites votre choix");

        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CodingTest.class.getResource("rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficheIndex();
    }

    public void afficheIndex(){
        try {
            // Charge la librairie
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CodingTest.class.getResource("Index.fxml"));
            AnchorPane menu = (AnchorPane) loader.load();

            // Récupère le contrôleur et lui passe la référence du Main
            IndexController controller = loader.getController();
            controller.setMain(this);

            // Affiche la librairie
            rootLayout.setCenter(menu);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afficheProduits() {
        try {
            //Modification du titre de la fenêtre
            primaryStage.setTitle("Liste des Produits");

            // Chargement de la page produit.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CodingTest.class.getResource("produit.fxml"));
            AnchorPane produit = (AnchorPane) loader.load();

            // Positionnement de produit.fxml au centre de rootLayout
            rootLayout.setCenter(produit);

            // Permet au controller d'acceder et modifier le main
            ProduitController controller = loader.getController();
            controller.setMain(this);

            controller.initProduit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afficheCapteurs() {
        try {
            //Modification du titre de la fenêtre
            primaryStage.setTitle("Liste des Capteurs");

            // Chargement de la page produit.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CodingTest.class.getResource("capteurs.fxml"));
            AnchorPane capteur = (AnchorPane) loader.load();

            // Positionnement de capteur.fxml au centre de rootLayout
            rootLayout.setCenter(capteur);

            // Permet au controller d'acceder et modifier le main
            CapteurController controller = loader.getController();
            controller.setMain(this);

            controller.initCapteurs();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'ouvrir la boite de dialogue d'ajout de produit
     * @param produit
     * @return : Si la méthode retourne TRUE, le produit sera ajouté à la liste des produits
     */
    public boolean afficheAjouterProduit(Produit produit){
        try{
            // Chargement du fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CodingTest.class.getResource("AjouterProduit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Création de la fenêtre de dialogue
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter Produit");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Modification du controller du produit
            AjouterProduitController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduit(produit);

            // Affiche la boite de dialogue et attend que l'utilisateur ferme la fenêtre
            dialogStage.showAndWait();

            return controller.boutonValider();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Permet d'ouvrir la boite de dialogue d'ajout de capteur
     * @param capteur
     * @return : Si la méthode retourne TRUE, le produit sera ajouté à la liste des capteurs
     */
    public boolean afficheAjouterCapteur(Capteur capteur){
        try{
            //Chargement du fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CodingTest.class.getResource("AjouterCapteur.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter Produit");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //Modifie le controller du produit
            AjouterCapteurController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCapteur(capteur);

            // Affiche la boite de dialogue et attend que l'utilisateur ferme la fenêtre
            dialogStage.showAndWait();

            return controller.boutonValider();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);

    }

}
