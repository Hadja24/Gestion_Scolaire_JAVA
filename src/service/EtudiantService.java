package service;

import model.Demande;
import model.EtatDemande;
import model.Etudiant;
import java.util.ArrayList;
import java.util.List;

public class EtudiantService {
    private List<Etudiant> etudiants = new ArrayList<>();
    private List<Demande> demandes = new ArrayList<>();

    public Etudiant ajouterEtudiant(String nom, String prenom, String email, String motDePasse, String matricule) {
        Etudiant etu = new Etudiant(nom, prenom, email, motDePasse, matricule);
        etudiants.add(etu);
        return etu;
    }

    public List<Etudiant> listerEtudiants() {
        return new ArrayList<>(etudiants);
    }

    public Demande soumettreDemande(Etudiant etu, String motif) {
        Demande demande = new Demande(etu, motif);
        demandes.add(demande);
        etu.ajouterDemande(demande);
        return demande;
    }

    public List<Demande> listerDemandesParEtat(Etudiant etu, EtatDemande etat) {
        List<Demande> resultat = new ArrayList<>();
        for (Demande d : demandes) {
            if (d.getEtudiant().equals(etu) && d.getEtat() == etat) {
                resultat.add(d);
            }
        }
        return resultat;
    }

    public Etudiant rechercherParMatricule(String matricule) {
        for (Etudiant e : etudiants) {
            if (e.getMatricule().equals(matricule)) {
                return e;
            }
        }
        return null;
    }

    public Etudiant chercherEtudiantParNom(String nomComplet) {
        for (Etudiant e : etudiants) {
            if (e.getNomComplet().equalsIgnoreCase(nomComplet)) {
                return e;
            }
        }
        return null;
    }
}
