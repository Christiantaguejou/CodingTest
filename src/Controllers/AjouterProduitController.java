package Controllers;

import Models.Produit;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by chris on 14/07/2017.
 */
public class AjouterProduitController {

    @FXML
    private TextField nomAjouter;
    @FXML
    private TextField quantiteAjouter;

    private Stage dialogStage;
    private Produit produit;
    private boolean valider = false;

    /**
     * Permet de modifier le Stage, c'est-à-dire la fenetre principale de l'application
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Modification ou Ajout d'un produit
     * @param produit
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
        nomAjouter.setText(produit.getNom());
        quantiteAjouter.setText(Integer.toString(produit.getQuantite()));
    }

    /**
     * Renvoie True si l'utilisateur clique sur Valider
     * @return
     */
    public boolean boutonValider() {
        return valider;
    }

    /**
     * Si l'utilisateur appuie sur le bouton "Annuler", la fenêtre se ferme
     */
    @FXML
    private void annuler() {
        dialogStage.close();
    }

    /**
     * Cette méthode est appellé lorsque l'utilisateur clique sur "Valider"
     */
    @FXML
    private void validerProduit() {
        if (valeursValide()) {
            //S'il s'agit de l'ajout d'un produit, l'id du produit est incrementé
            if(!ProduitController.edit){
                ProduitController.nbrProduit += 1;
                produit.setId(ProduitController.nbrProduit);
            }
            //S'il s'agit d'une modification l'id n'est pas modifié
            else {
                produit.setId(produit.getId());
            }

            produit.setNom(nomAjouter.getText());
            produit.setQuantite(Integer.parseInt(quantiteAjouter.getText()));

            valider = true;
            dialogStage.close();

        }
    }

    /**
     * Permet de vérifier si les valeurs entrée par l'utilisateur sont bonnes
     * @return
     */
    private boolean valeursValide(){
        String errorMessage = "";
        if (nomAjouter.getText() == null || nomAjouter.getText().length() == 0) {
            errorMessage += "Nom de produit invalide\n";
        }
        if (quantiteAjouter.getText() == null || Integer.parseInt(quantiteAjouter.getText()) < 0) {
            errorMessage += "Quantité du produit invalide\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la saisie");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
