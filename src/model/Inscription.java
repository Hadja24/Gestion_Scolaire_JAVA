package model;

import java.time.LocalDate;

public class Inscription {
    private static int compteur = 1;

    private int id;
    private Etudiant etudiant;
    private Classe classe;
    private String annee;
    private LocalDate dateInscription;
    private EtatInscription etat;

    public Inscription(Etudiant etudiant, Classe classe, String annee) {
        this.id = compteur++;
        this.etudiant = etudiant;
        this.classe = classe;
        this.annee = annee;
        this.dateInscription = LocalDate.now();
        this.etat = EtatInscription.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Classe getClasse() {
        return classe;
    }

    public String getAnnee() {
        return annee;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public EtatInscription getEtat() {
        return etat;
    }

    public void setEtat(EtatInscription etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Inscription [id=" + id + ", etudiant=" + etudiant + ", classe=" + classe + ", annee=" + annee + "]";
    }
}
