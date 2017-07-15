package Controllers;

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
 * Created by chris on 14/07/2017.
 */
public class ProduitController {

    private CodingTest main;
    /**
     * Ce boolean permettra de savoir s'il s'agit d'un ajout de Produit (false) ou d'une modification (true)
     */
    public static boolean edit = false;

    /**
     * L'ensemble des produits est stocké dans une liste d'Observable
     */
    private ObservableList<Produit> listeProduit = FXCollections.observableArrayList();

    /**
     * On va incrémenter les ID à partir du nombre de produits déjà présent
     */
    public static int nbrProduit;

    /**
     * TableView contiendra l'ensemble des colonnes (idColonne, nomColonne, quantiteColonne)
     */
    @FXML
    private TableView<Produit> tableProduits;

    @FXML
    private TableColumn<Produit, Number> idColonne;
    @FXML
    private TableColumn<Produit, String> nomColonne;
    @FXML
    private TableColumn<Produit, Number> quantiteColonne;

    public ProduitController(){    }

    /**
     * Affichage dans les colonnes correspondantes les différentes valeurs
     */
    public void initialisation(){
        idColonne.setCellValueFactory(CellData -> CellData.getValue().idProperty());
        nomColonne.setCellValueFactory(CellData -> CellData.getValue().nomProperty());
        quantiteColonne.setCellValueFactory(CellData -> CellData.getValue().quantiteProperty());
    }

    /**
     * Initialisation des produits
     */
    public void initProduit(){
        listeProduit.add(new Produit(1, "Lait", 4));
        listeProduit.add(new Produit(2, "Jus", 2));
        listeProduit.add(new Produit(3, "Champagne", 6));
        nbrProduit = listeProduit.size();
    }

    public ObservableList<Produit> getProduits() {
        return listeProduit;
    }

    //Les méthodes suivantes permettront d'agir sur la liste des produits
    /**
     * Permet de retourner au Menu quand on est sur la view produit
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
     * Suppresion d'un produit dans la liste.
     * Si l'utilisateur n'a pas sélectionné de produit, un message d'erreur apparait
     */
    @FXML
    public  void deleteProduit(){
        int indexProduit = tableProduits.getSelectionModel().getSelectedIndex();
        if (indexProduit >= 0) {
            tableProduits.getItems().remove(indexProduit);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun Produit Selectionné");
            alert.setContentText("Selectionnez un Produit !");

            alert.showAndWait();
        }
    }

    /**
     * Permet l'ajout d'un nouveau produit
     * Si ajouter == TRUE, c'est-à-dire que si l'utilisateur appuie sur le bouton "Valider" le produit sera ajouté à la liste
     */
    @FXML
    protected void nouveauProduit() {
        edit = false;
        Produit prod = new Produit();
        boolean ajouter = main.afficheAjouterProduit(prod);
        if (ajouter) {
            listeProduit.add(prod);
        }
    }

    /**
     * Modification d'un produit
     * Si l'utilisateur n'a pas sélectionné de produit, un message d'erreur apparait
     */
    @FXML
    protected void modifierProduit() {
        edit = true;
        Produit prodSelectionner = tableProduits.getSelectionModel().getSelectedItem();
        if (prodSelectionner != null) {
            main.afficheAjouterProduit(prodSelectionner);
        } else {
            // Aucun produit selectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun produit selectionné");
            alert.setContentText("Veuillez selectionner un Produit");

            alert.showAndWait();
        }
    }


    /**
     * Permet de passer la reference du main
     * @param main
     */
    public void setMain(CodingTest main) {
        this.main = main;
        initialisation();

        //Affiche la liste de produit dans la view
        tableProduits.setItems(getProduits());
    }
}
