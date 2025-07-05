package view;

import model.*;
import model.Attache;
import model.Sexe;
import service.*;

import java.util.List;
import java.util.Scanner;

public class AttacheView {
    private Attache attache;
    private AttacheService attacheService;
    private ClasseService classeService;
    private InscriptionService inscriptionService;
    private DemandeService demandeService;
    private Scanner scanner;

    public AttacheView(Attache attache, AttacheService attacheService, ClasseService classeService, InscriptionService inscriptionService, DemandeService demandeService) {
        this.attache = attache;
        this.attacheService = attacheService;
        this.classeService = classeService;
        this.inscriptionService = inscriptionService;
        this.demandeService = demandeService;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        int choix;
        do {
            System.out.println("\n===== MENU ATTACHÉ DE CLASSE =====");
            System.out.println("Connecté en tant que : " + attache.getNomComplet());
            System.out.println("1. Inscrire un nouvel étudiant");
            System.out.println("2. Réinscrire un étudiant existant");
            System.out.println("3. Lister les étudiants d'une classe par année");
            System.out.println("4. Traiter les demandes");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // vider le buffer

            switch (choix) {
                case 1 -> inscrireNouvelEtudiant();
                case 2 -> reinscrireEtudiant();
                case 3 -> listerEtudiantsParClasseEtAnnee();
                case 4 -> traiterDemandes();
            }
        } while (choix != 0);
    }

    private void inscrireNouvelEtudiant() {
        System.out.print("Nom complet : ");
        String nom = scanner.nextLine();

        System.out.print("Login : ");
        String login = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        String genre;
        do {
            System.out.print("Genre (M/F) : ");
            genre = scanner.nextLine().toUpperCase();
        } while (!genre.equals("M") && !genre.equals("F"));

        System.out.print("Matricule : ");
        String matricule = scanner.nextLine();

        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();

        Etudiant etu = new Etudiant(nom, login, adresse, motDePasse, matricule);
        etu.setSexe(genre.equals("M") ? Sexe.MASCULIN : Sexe.FEMININ);

        Classe classe = choisirClasse();
        if (classe == null) return;

        System.out.print("Année : ");
        String annee = scanner.nextLine();

        boolean success = inscriptionService.inscrire(etu, classe, annee);
        if (success) {
            System.out.println("✅ Étudiant inscrit avec succès !");
        }
    }

    private void reinscrireEtudiant() {
        System.out.print("Matricule de l'étudiant : ");
        String matricule = scanner.nextLine();
        Etudiant etu = attacheService.listerEtudiants().stream()
                .filter(e -> e.getMatricule().equals(matricule))
                .findFirst()
                .orElse(null);

        if (etu == null) {
            System.out.println("⚠️ Étudiant introuvable.");
            return;
        }

        Classe classe = choisirClasse();
        if (classe == null) return;

        System.out.print("Nouvelle année : ");
        String annee = scanner.nextLine();

        boolean success = inscriptionService.inscrire(etu, classe, annee);
        if (success) {
            System.out.println("✅ Réinscription réussie !");
        }
    }

    private void listerEtudiantsParClasseEtAnnee() {
        Classe classe = choisirClasse();
        if (classe == null) return;

        System.out.print("Année : ");
        String annee = scanner.nextLine();

        List<Etudiant> etudiants = inscriptionService.listerEtudiantsParClasseEtAnnee(classe, annee);
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant inscrit dans cette classe pour l'année " + annee);
        } else {
            System.out.println("Étudiants inscrits :");
            for (Etudiant e : etudiants) {
                System.out.println("- " + e);
            }
        }
    }

    private void traiterDemandes() {
        List<Demande> demandes = demandeService.listerDemandesParEtat(EtatDemande.EN_ATTENTE);
        if (demandes.isEmpty()) {
            System.out.println("Aucune demande en attente.");
            return;
        }

        System.out.println("Demandes en attente :");
        for (Demande d : demandes) {
            System.out.println("- " + d);
            System.out.print("Accepter ? (O/N) : ");
            String reponse = scanner.nextLine().toUpperCase();
            demandeService.traiterDemande(d, reponse.equals("O"));
        }
    }

    private Classe choisirClasse() {
        List<Classe> classes = classeService.listerClasses();
        if (classes.isEmpty()) {
            System.out.println("⚠️ Aucune classe disponible !");
            return null;
        }

        System.out.println("Classes disponibles :");
        for (Classe c : classes) {
            System.out.println("- " + c.getLibelle());
        }

        System.out.print("Choisissez une classe (libellé exact) : ");
        String lib = scanner.nextLine();

        for (Classe c : classes) {
            if (c.getLibelle().equalsIgnoreCase(lib)) {
                return c;
            }
        }

        System.out.println("⚠️ Classe introuvable !");
        return null;
    }
}
