package Models;

/**
 * Created by chris on 14/07/2017.
 */
public class Capteur extends Objet {

    public Capteur(int id, String nom){
        super(id, nom);
    }

    /**
     * Constructeur par defaut
     */
    public Capteur() {
        super(0,null,0);
    }
}
