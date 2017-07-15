package Controllers;

import Views.CodingTest;
import javafx.fxml.FXML;

/**
 * Created by chris on 15/07/2017.
 */
public class IndexController {

    private CodingTest main;

    /**
     * Modifie la référence du Main
     * @param main
     */
    public void setMain(CodingTest main) {
        this.main = main;
    }

    @FXML
    public void lancerProduits() {
        main.afficheProduits();
    }

    @FXML
    public void lancerCapteurs() {
        main.afficheCapteurs();
    }

}
