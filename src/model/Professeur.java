package model;

import java.util.ArrayList;
import java.util.List;

public class Professeur extends Utilisateur {
    private String specialite;
    private List<Classe> classes;
    private List<Module> modules;

    public Professeur(String nom, String prenom, String email, String motDePasse, String specialite) {
        super(nom, prenom, email, motDePasse);
        this.specialite = specialite;
        this.classes = new ArrayList<>();
        this.modules = new ArrayList<>();
    }

    public String getSpecialite() {
        return specialite;
    }

    public List<Classe> getClasses() {
        return new ArrayList<>(classes);
    }

    public List<Module> getModules() {
        return new ArrayList<>(modules);
    }

    public void ajouterClasse(Classe classe) {
        if (!classes.contains(classe)) {
            classes.add(classe);
        }
    }

    public void ajouterModule(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    @Override
    public String toString() {
        return getNomComplet() + " (" + specialite + ")";
    }
}
