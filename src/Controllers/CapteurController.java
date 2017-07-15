package Controllers;

import Models.Capteur;
import Models.Objet;
import Models.Produit;
import Views.CodingTest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by chris on 15/07/2017.
 */
public class CapteurController {

    private CodingTest main;
    /**
     * Ce boolean permettra de savoir s'il s'agit d'un ajout de Capteur (false) ou d'une modification (true)
     */
    public static boolean edit = false;

    /**
     * Les données sont stockées dans une liste d'Observable
     */
    private ObservableList<Capteur> listeCapteur = FXCollections.observableArrayList();

    /**
     * On va incrémenter les ID à partir du nombre de capteurs déjà présent
     */
    public static int nbrCapteur;

    /**
     * TableView contiendra l'ensemble des colonnes (idColonne, nomColonne)
     */
    @FXML
    private TableView<Capteur> tableCapteurs;
    @FXML
    private TableColumn<Capteur, Number> idColonne;
    @FXML
    private TableColumn<Capteur, String> nomColonne;

    public CapteurController(){}

    /**
     * Affichage dans les colonnes correspondantes les différentes valeurs
     */
    public void initialisation(){
        idColonne.setCellValueFactory(CellData -> CellData.getValue().idProperty());
        nomColonne.setCellValueFactory(CellData -> CellData.getValue().nomProperty());
    }

    /**
     * Initialisation des capteurs
     */
    public void initCapteurs(){
        listeCapteur.add(new Capteur(1, "Capteur de Température"));
        listeCapteur.add(new Capteur(2, "Capteur d'humidité"));
        nbrCapteur = listeCapteur.size();
    }

    public ObservableList<Capteur> getCapteurs() {
        return listeCapteur;
    }

    /**
     * Permet de retourner au Menu quand on est sur la view capteur
     */
    @FXML
    public void lancerMenu(){
        main.afficheIndex();
    }

    /**
     * Ferme l'application
     */
    @FXML
    public void fermer() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Suppresion d'un capteur dans la liste.
     * Si l'utilisateur n'a pas sélectionné de capteur, un message d'erreur apparait
     */
    @FXML
    private  void deleteCapteur(){
        int indexCapteur = tableCapteurs.getSelectionModel().getSelectedIndex();
        if (indexCapteur >= 0) {
            tableCapteurs.getItems().remove(indexCapteur);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun Capteur Selectionné");
            alert.setContentText("Selectionnez un Capteur !");

            alert.showAndWait();
        }
    }

    /**
     * Permet l'ajout d'un nouveau capteur
     * Si ajouter == TRUE, c'est-à-dire que si l'utilisateur appuie sur le bouton "Valider" le capteur sera ajouté à la liste
     */
    @FXML
    private void nouveauCapteur() {
        edit = false;
        Capteur capt = new Capteur();
        boolean validerButton = main.afficheAjouterCapteur(capt);
        if (validerButton) {
            listeCapteur.add(capt);
        }
    }

    /**
     * Modification d'un capteur
     * Si l'utilisateur n'a pas sélectionné de capteur, un message d'erreur apparait
     */
    @FXML
    private void modifierCapteur() {
        edit = true;
        Capteur captSelectionner = tableCapteurs.getSelectionModel().getSelectedItem();
        if (captSelectionner != null) {
            main.afficheAjouterCapteur(captSelectionner);
        } else {
            // Aucun capteur selectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun capteur selectionné");
            alert.setContentText("Veuillez selectionner un capteur");

            alert.showAndWait();
        }
    }


    /**
     * Permet de preciser au main prinicipal que cette classe sera le nouveau main
     * @param main
     */
    public void setMain(CodingTest main) {
        this.main = main;
        initialisation();
        //Affiche la liste de capteurs dans la view
        tableCapteurs.setItems(getCapteurs());
    }

}
