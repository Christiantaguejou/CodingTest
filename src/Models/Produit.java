package Models;

/**
 * Created by chris on 14/07/2017.
 */

public class Produit extends Objet {


    public Produit(int id, String nom, int quantite){
        super(id, nom, quantite);
    }

    /**
     * Constructeur par defaut
     */
    public Produit() {
        super(0,null,0);
    }

}
