package model;

import java.util.ArrayList;
import java.util.List;

public class Etudiant extends Utilisateur {
    private String matricule;
    private Sexe sexe;
    private List<Inscription> inscriptions;
    private List<Demande> demandes;

    public Etudiant(String nom, String prenom, String email, String motDePasse, String matricule) {
        super(nom, prenom, email, motDePasse);
        this.matricule = matricule;
        this.sexe = Sexe.MASCULIN; // Valeur par défaut
        this.inscriptions = new ArrayList<>();
        this.demandes = new ArrayList<>();
    }

    public String getMatricule() {
        return matricule;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public List<Inscription> getInscriptions() {
        return new ArrayList<>(inscriptions);
    }

    public void ajouterInscription(Inscription inscription) {
        if (!inscriptions.contains(inscription)) {
            inscriptions.add(inscription);
        }
    }

    public List<Demande> getDemandes() {
        return new ArrayList<>(demandes);
    }

    public void ajouterDemande(Demande demande) {
        if (!demandes.contains(demande)) {
            demandes.add(demande);
        }
    }

    @Override
    public String toString() {
        return "Etudiant [matricule=" + matricule + ", " + super.toString() + "]";
    }
}
