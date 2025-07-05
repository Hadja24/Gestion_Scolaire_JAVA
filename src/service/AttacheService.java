package service;

import model.Attache;
import model.Classe;
import model.Etudiant;
import model.Demande;
import model.EtatDemande;
import model.Inscription;

import java.util.ArrayList;
import java.util.List;

public class AttacheService {
    private Attache attache;
    private List<Classe> classes;
    private List<Etudiant> etudiants;
    private List<Demande> demandes;
    private List<Inscription> inscriptions;

    public AttacheService(Attache attache) {
        this.attache = attache;
        this.classes = new ArrayList<>();
        this.etudiants = new ArrayList<>();
        this.demandes = new ArrayList<>();
        this.inscriptions = new ArrayList<>();
    }

    public Attache getAttache() {
        return attache;
    }

    public void ajouterClasse(Classe classe) {
        if (!classes.contains(classe)) {
            classes.add(classe);
            System.out.println("Classe ajoutée par " + attache.getNomComplet());
        }
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        if (!etudiants.contains(etudiant)) {
            etudiants.add(etudiant);
            System.out.println("Étudiant ajouté par " + attache.getNomComplet());
        }
    }

    public void ajouterDemande(Demande demande) {
        if (!demandes.contains(demande)) {
            demandes.add(demande);
            System.out.println("Demande ajoutée par " + attache.getNomComplet());
        }
    }

    public List<Classe> listerClasses() {
        return new ArrayList<>(classes);
    }

    public List<Etudiant> listerEtudiants() {
        return new ArrayList<>(etudiants);
    }

    public List<Demande> listerDemandes() {
        return new ArrayList<>(demandes);
    }

    public List<Demande> listerDemandesParEtat(EtatDemande etat) {
        List<Demande> resultat = new ArrayList<>();
        for (Demande d : demandes) {
            if (d.getEtat() == etat) {
                resultat.add(d);
            }
        }
        return resultat;
    }

    public void traiterDemande(Demande demande, boolean accepter) {
        demande.setEtat(accepter ? EtatDemande.ACCEPTEE : EtatDemande.REFUSEE);
        System.out.println("Demande traitée par " + attache.getNomComplet());
    }

    public void affecterEtudiantClasse(Etudiant etudiant, Classe classe) {
        classe.ajouterEtudiant(etudiant);
        System.out.println("Étudiant affecté par " + attache.getNomComplet());
    }

    public boolean inscrireEtudiant(Etudiant etudiant, Classe classe, String annee) {
        if (estInscritCetteAnnee(etudiant, annee)) {
            System.out.println("❌ Cet étudiant est déjà inscrit pour l'année " + annee);
            return false;
        }

        Inscription inscription = new Inscription(etudiant, classe, annee);
        ajouterInscription(inscription);
        classe.ajouterInscription(inscription);
        System.out.println("Inscription effectuée par " + attache.getNomComplet());
        return true;
    }

    private boolean estInscritCetteAnnee(Etudiant etudiant, String annee) {
        for (Inscription i : inscriptions) {
            if (i.getEtudiant().equals(etudiant) && i.getAnnee().equals(annee)) {
                return true;
            }
        }
        return false;
    }

    private void ajouterInscription(Inscription inscription) {
        if (!inscriptions.contains(inscription)) {
            inscriptions.add(inscription);
        }
    }

    public List<Etudiant> listerEtudiantsParClasseEtAnnee(Classe classe, String annee) {
        List<Etudiant> resultat = new ArrayList<>();
        for (Inscription i : inscriptions) {
            if (i.getClasse().equals(classe) && i.getAnnee().equals(annee)) {
                resultat.add(i.getEtudiant());
            }
        }
        return resultat;
    }
} 
