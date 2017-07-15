package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by chris on 15/07/2017.
 */

/**
 * Cette classe sera hérité par Produit et Capteur car la constitution de ces deux classes se ressemblent
 */
public class Objet {
    // Les Property permettent d'être automatiquement averti lorsqu'une variable a été modifiée.
    // Ceci nous aide à maintenir la view synchronisée avec les données
    private final StringProperty nom;
    private final IntegerProperty quantite;
    private final IntegerProperty id;

    public Objet(int id, String nom, int quantite){
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.quantite = new SimpleIntegerProperty(quantite);
    }

    public Objet(int id, String nom){
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.quantite = null;
    }

    /**
     * Constructeur par defaut
     */
    public Objet() {
        this(0, null, 0);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public int getQuantite() {
        return quantite.get();
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }
}
