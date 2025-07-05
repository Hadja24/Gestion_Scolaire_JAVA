package service;

import model.Classe;
import model.Etudiant;
import model.Inscription;
import model.Sexe;

import java.util.ArrayList;
import java.util.List;

public class InscriptionService {
    private List<Inscription> inscriptions = new ArrayList<>();

    // Inscrire un étudiant
    public boolean inscrire(Etudiant etudiant, Classe classe, String annee) {
        if (estInscritCetteAnnee(etudiant, annee)) {
            System.out.println("❌ Cet étudiant est déjà inscrit pour l'année " + annee);
            return false;
        }

        Inscription inscription = new Inscription(etudiant, classe, annee);
        inscriptions.add(inscription);
        classe.ajouterInscription(inscription);
        return true;
    }

    // Vérifie si un étudiant est déjà inscrit dans l'année
    public boolean estInscritCetteAnnee(Etudiant etudiant, String annee) {
        for (Inscription i : inscriptions) {
            if (i.getEtudiant().equals(etudiant) && i.getAnnee().equals(annee)) {
                return true;
            }
        }
        return false;
    }

    // Lister les étudiants d'une classe dans une année
    public List<Etudiant> listerEtudiantsParClasseEtAnnee(Classe classe, String annee) {
        List<Etudiant> resultat = new ArrayList<>();
        for (Inscription i : inscriptions) {
            if (i.getClasse().equals(classe) && i.getAnnee().equals(annee)) {
                resultat.add(i.getEtudiant());
            }
        }
        return resultat;
    }

    // Renvoyer toutes les inscriptions
    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    // Statistiques
    public int getEffectifParAnnee(String annee) {
        int total = 0;
        for (Inscription i : inscriptions) {
            if (i.getAnnee().equals(annee)) {
                total++;
            }
        }
        return total;
    }

    public int getNombreParGenreParAnnee(String genre, String annee) {
        int total = 0;
        Sexe sexe = Sexe.valueOf(genre.toUpperCase());
        for (Inscription i : inscriptions) {
            if (i.getAnnee().equals(annee) && i.getEtudiant().getSexe() == sexe) {
                total++;
            }
        }
        return total;
    }

    public int getEffectifParClasse(Classe classe, String annee) {
        int total = 0;
        for (Inscription i : inscriptions) {
            if (i.getClasse().equals(classe) && i.getAnnee().equals(annee)) {
                total++;
            }
        }
        return total;
    }

    public int getNombreGenreParClasse(Classe classe, String genre, String annee) {
        int total = 0;
        Sexe sexe = Sexe.valueOf(genre.toUpperCase());
        for (Inscription i : inscriptions) {
            if (i.getClasse().equals(classe) && i.getAnnee().equals(annee) && i.getEtudiant().getSexe() == sexe) {
                total++;
            }
        }
        return total;
    }

    public void ajouterInscription(Inscription inscription) {
        if (!inscriptions.contains(inscription)) {
            inscriptions.add(inscription);
        }
    }

    public List<Inscription> listerInscriptions() {
        return new ArrayList<>(inscriptions);
    }

    public List<Inscription> listerInscriptionsParEtudiant(Etudiant etudiant) {
        List<Inscription> resultat = new ArrayList<>();
        for (Inscription i : inscriptions) {
            if (i.getEtudiant().equals(etudiant)) {
                resultat.add(i);
            }
        }
        return resultat;
    }

    public List<Inscription> listerInscriptionsParClasse(Classe classe) {
        List<Inscription> resultat = new ArrayList<>();
        for (Inscription i : inscriptions) {
            if (i.getClasse().equals(classe)) {
                resultat.add(i);
            }
        }
        return resultat;
    }

    public List<Inscription> listerInscriptionsParAnnee(String annee) {
        List<Inscription> resultat = new ArrayList<>();
        for (Inscription i : inscriptions) {
            if (i.getAnnee().equals(annee)) {
                resultat.add(i);
            }
        }
        return resultat;
    }
}
