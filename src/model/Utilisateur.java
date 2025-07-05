package model;

public abstract class Utilisateur {
    private static int compteur = 1;

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;

    public Utilisateur(String nom, String prenom, String email, String motDePasse) {
        this.id = compteur++;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", nomComplet=" + getNomComplet() + ", email=" + email + "]";
    }
} 