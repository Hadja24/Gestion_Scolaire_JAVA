package view;

import model.*;
import service.*;
import service.DemandeService;

import java.util.List;
import java.util.Scanner;

public class EtudiantView {
    private InscriptionService inscriptionService;
    private DemandeService demandeService;
    private Etudiant etudiant;
    private Scanner scanner;

    public EtudiantView(Etudiant etudiant, InscriptionService inscriptionService, DemandeService demandeService) {
        this.etudiant = etudiant;
        this.inscriptionService = inscriptionService;
        this.demandeService = demandeService;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        int choix;
        do {
            System.out.println("\n===== MENU ÉTUDIANT =====");
            System.out.println("1. Voir mes inscriptions");
            System.out.println("2. Faire une demande d'annulation/suspension");
            System.out.println("3. Lister mes demandes");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // vider buffer

            switch (choix) {
                case 1 -> afficherInscriptions();
                case 2 -> faireDemande();
                case 3 -> listerDemandes();
            }
        } while (choix != 0);
    }

    private void afficherInscriptions() {
        List<Inscription> inscriptions = inscriptionService.listerInscriptionsParEtudiant(etudiant);
        if (inscriptions.isEmpty()) {
            System.out.println("Vous n'avez aucune inscription.");
            return;
        }
        System.out.println("Vos inscriptions :");
        for (Inscription i : inscriptions) {
            System.out.println("- Classe : " + i.getClasse().getLibelle() + ", Année : " + i.getAnnee() +
                    ", Statut : " + i.getEtat());
        }
    }

    private void faireDemande() {
        System.out.println("Faire une demande d'annulation ou de suspension");
        System.out.print("Motif : ");
        String motif = scanner.nextLine();

        demandeService.creerDemande(etudiant, motif);
        System.out.println("Demande créée avec succès, en attente de traitement.");
    }

    private void listerDemandes() {
        System.out.print("Filtrer par état (EN_ATTENTE, ACCEPTEE, REFUSEE) ou 'TOUTES' : ");
        String filtre = scanner.nextLine().toUpperCase();

        List<Demande> demandes;
        if (filtre.equals("TOUTES")) {
            demandes = demandeService.listerDemandesParEtudiant(etudiant);
        } else {
            try {
                EtatDemande etat = EtatDemande.valueOf(filtre);
                demandes = demandeService.listerDemandesParEtudiantEtEtat(etudiant, etat);
            } catch (IllegalArgumentException e) {
                System.out.println("État invalide, affichage de toutes les demandes.");
                demandes = demandeService.listerDemandesParEtudiant(etudiant);
            }
        }

        if (demandes.isEmpty()) {
            System.out.println("Aucune demande correspondante.");
        } else {
            System.out.println("Vos demandes :");
            for (Demande d : demandes) {
                System.out.println("- Date : " + d.getDateDemande() + ", Motif : " + d.getMotif() + ", État : " + d.getEtat());
            }
        }
    }
}
