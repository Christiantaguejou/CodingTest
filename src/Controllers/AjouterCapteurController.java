package Controllers;

import Models.Capteur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by chris on 15/07/2017.
 */
public class AjouterCapteurController {

    @FXML
    private TextField nomAjouter;

    private Stage dialogStage;
    private Capteur capteur;
    private boolean valider = false;

    /**
     * Permet de modifier le Stage, c'est-à-dire la fenetre principale de l'application
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Modification d'un Capteur
     * @param
     */
    public void setCapteur(Capteur capteur) {
        this.capteur = capteur;
        nomAjouter.setText(capteur.getNom());
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
    private void validerCapteur() {
        if (valeursValide()) {
            //S'il s'agit de l'ajout d'un capteur, l'id du capteur est incrementé
            if(!CapteurController.edit){
                CapteurController.nbrCapteur += 1;
                capteur.setId(CapteurController.nbrCapteur);
            }
            //S'il s'agit d'une modification l'id n'est pas modifié
            else {
                capteur.setId(capteur.getId());
            }

            capteur.setNom(nomAjouter.getText());

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
            errorMessage += "Nom de capteur invalide\n";
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
