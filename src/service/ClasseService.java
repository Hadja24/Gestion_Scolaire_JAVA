package service;

import model.Classe;
import model.Etudiant;
import model.Professeur;
import model.Module;

import java.util.ArrayList;
import java.util.List;

public class ClasseService {
    private List<Classe> classes;

    public ClasseService() {
        this.classes = new ArrayList<>();
    }

    // ✅ Créer une nouvelle classe
    public Classe creerClasse(String libelle, String filiere, String niveau) {
        Classe c = new Classe(libelle, filiere, niveau);
        classes.add(c);
        return c;
    }

    // ✅ Lister toutes les classes
    public List<Classe> listerClasses() {
        return new ArrayList<>(classes);
    }

    // ✅ Rechercher une classe par son libellé
    public Classe chercherClasseParLibelle(String libelle) {
        for (Classe c : classes) {
            if (c.getLibelle().equalsIgnoreCase(libelle)) {
                return c;
            }
        }
        return null;
    }

    // ✅ Supprimer une classe (facultatif)
    public boolean supprimerClasse(String libelle) {
        Classe c = chercherClasseParLibelle(libelle);
        if (c != null) {
            return classes.remove(c);
        }
        return false;
    }

    public void ajouterClasse(Classe classe) {
        classes.add(classe);
    }

    public List<Etudiant> listerEtudiantsParClasse(Classe classe) {
        return classe.getEtudiants();
    }

    public void affecterProfesseur(Classe classe, Professeur professeur) {
        classe.ajouterProfesseur(professeur);
    }

    public void affecterModule(Classe classe, Module module) {
        classe.ajouterModule(module);
    }
}
