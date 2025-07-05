package service;

import model.Professeur;
import model.Module;
import model.Classe;

import java.util.ArrayList;
import java.util.List;

public class ProfesseurService {
    private List<Professeur> professeurs;

    public ProfesseurService() {
        this.professeurs = new ArrayList<>();
    }

    public void ajouterProfesseur(Professeur professeur) {
        if (!professeurs.contains(professeur)) {
            professeurs.add(professeur);
        }
    }

    public List<Professeur> listerProfesseurs() {
        return new ArrayList<>(professeurs);
    }

    public Professeur chercherProfesseurParNom(String nomComplet) {
        for (Professeur p : professeurs) {
            if (p.getNomComplet().equalsIgnoreCase(nomComplet)) {
                return p;
            }
        }
        return null;
    }

    public Professeur chercherProfesseurParEmail(String email) {
        for (Professeur p : professeurs) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                return p;
            }
        }
        return null;
    }

    public void affecterModule(Professeur professeur, Module module) {
        professeur.ajouterModule(module);
    }

    public void affecterClasse(Professeur professeur, Classe classe) {
        professeur.ajouterClasse(classe);
    }

    public void ajouterModuleAProfesseur(String email, Module mod) {
        for (Professeur p : professeurs) {
            if (p.getEmail().equals(email)) {
                p.ajouterModule(mod);
                return;
            }
        }
    }

    public List<Module> getModulesParProfesseur(String email) {
        for (Professeur p : professeurs) {
            if (p.getEmail().equals(email)) {
                return p.getModules();
            }
        }
        return new ArrayList<>();
    }

    public List<Professeur> getProfesseursParModule(String libelleModule) {
        List<Professeur> resultat = new ArrayList<>();
        for (Professeur p : professeurs) {
            for (Module m : p.getModules()) {
                if (m.getLibelle().equals(libelleModule)) {
                    resultat.add(p);
                    break;
                }
            }
        }
        return resultat;
    }

    public void affecterClasseAProfesseur(String email, Classe classe) {
        for (Professeur p : professeurs) {
            if (p.getEmail().equals(email)) {
                p.ajouterClasse(classe);
                return;
            }
        }
    }

    public List<Classe> listerClassesDunProf(String email) {
        for (Professeur p : professeurs) {
            if (p.getEmail().equals(email)) {
                return p.getClasses();
            }
        }
        return new ArrayList<>();
    }
} 
