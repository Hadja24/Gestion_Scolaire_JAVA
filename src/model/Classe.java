package model;

import java.util.ArrayList;
import java.util.List;

public class Classe {
    private static int compteur = 1;

    private int id;
    private String libelle;
    private String filiere;
    private String niveau;

    private List<Professeur> professeurs;
    private List<Inscription> inscriptions;
    private List<Etudiant> etudiants;
    private List<Module> modules;

    public Classe(String libelle, String filiere, String niveau) {
        this.id = compteur++;
        this.libelle = libelle;
        this.filiere = filiere;
        this.niveau = niveau;
        this.professeurs = new ArrayList<>();
        this.inscriptions = new ArrayList<>();
        this.etudiants = new ArrayList<>();
        this.modules = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<Professeur> getProfesseurs() {
        return new ArrayList<>(professeurs);
    }

    public void ajouterProfesseur(Professeur professeur) {
        if (!professeurs.contains(professeur)) {
            professeurs.add(professeur);
            professeur.ajouterClasse(this);
        }
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void ajouterInscription(Inscription inscription) {
        if (!inscriptions.contains(inscription)) {
            inscriptions.add(inscription);
        }
    }

    public List<Etudiant> getEtudiants() {
        return new ArrayList<>(etudiants);
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        if (!etudiants.contains(etudiant)) {
            etudiants.add(etudiant);
        }
    }

    public List<Module> getModules() {
        return new ArrayList<>(modules);
    }

    public void ajouterModule(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    @Override
    public String toString() {
        return "Classe [id=" + id + ", libelle=" + libelle + ", filiere=" + filiere + ", niveau=" + niveau + "]";
    }
}
