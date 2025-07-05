package service;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DemandeService {

    private List<Demande> demandes;

    public DemandeService() {
        this.demandes = new ArrayList<>();
    }

    // Crée une nouvelle demande pour un étudiant
    public Demande creerDemande(Etudiant etudiant, String motif) {
        Demande d = new Demande(etudiant, motif);
        demandes.add(d);
        return d;
    }

    // Liste toutes les demandes d'un étudiant
    public List<Demande> listerDemandesParEtudiant(Etudiant etudiant) {
        return demandes.stream()
                .filter(d -> d.getEtudiant().equals(etudiant))
                .collect(Collectors.toList());
    }

    // Liste les demandes d'un étudiant selon un état spécifique
    public List<Demande> listerDemandesParEtudiantEtEtat(Etudiant etudiant, EtatDemande etat) {
        return demandes.stream()
                .filter(d -> d.getEtudiant().equals(etudiant) && d.getEtat() == etat)
                .collect(Collectors.toList());
    }

    // Liste toutes les demandes
    public List<Demande> listerToutesLesDemandes() {
        return new ArrayList<>(demandes);
    }

    // Liste les demandes par état
    public List<Demande> listerDemandesParEtat(EtatDemande etat) {
        return demandes.stream()
                .filter(d -> d.getEtat() == etat)
                .collect(Collectors.toList());
    }

    // Recherche les demandes par matricule d'étudiant
    public List<Demande> rechercherDemandeParMatricule(String matricule) {
        return demandes.stream()
                .filter(d -> d.getEtudiant().getMatricule().equals(matricule))
                .collect(Collectors.toList());
    }

    // Traiter une demande (acceptée ou refusée)
    public void traiterDemande(Demande demande, boolean accepter) {
        if (accepter) {
            demande.setEtat(EtatDemande.ACCEPTEE);
        } else {
            demande.setEtat(EtatDemande.REFUSEE);
        }
    }

    public void ajouterDemande(Demande demande) {
        if (!demandes.contains(demande)) {
            demandes.add(demande);
        }
    }

    public List<Demande> listerDemandes() {
        return new ArrayList<>(demandes);
    }

    public Demande chercherDemandeParId(int id) {
        for (Demande d : demandes) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public void traiterDemande(Demande demande, EtatDemande etat) {
        demande.setEtat(etat);
    }

    // Autres méthodes utiles possibles selon besoin
}
