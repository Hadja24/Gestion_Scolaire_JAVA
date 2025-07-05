package model;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private String nom;
    private String description;
    private List<Professeur> professeurs;
    private List<Classe> classes;

    public Module(String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.professeurs = new ArrayList<>();
        this.classes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public List<Professeur> getProfesseurs() {
        return new ArrayList<>(professeurs);
    }

    public void ajouterProfesseur(Professeur professeur) {
        if (!professeurs.contains(professeur)) {
            professeurs.add(professeur);
        }
    }

    public List<Classe> getClasses() {
        return new ArrayList<>(classes);
    }

    public void ajouterClasse(Classe classe) {
        if (!classes.contains(classe)) {
            classes.add(classe);
        }
    }

    @Override
    public String toString() {
        return "Module [id=" + id + ", libelle=" + libelle + "]";
    }
}
