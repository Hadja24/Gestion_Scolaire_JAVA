package model;

public class Attache extends Utilisateur {
    private String filiere;

    public Attache(String nom, String prenom, String email, String motDePasse, String filiere) {
        super(nom, prenom, email, motDePasse);
        this.filiere = filiere;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    @Override
    public String toString() {
        return getNomComplet() + " (" + filiere + ")";
    }
}
