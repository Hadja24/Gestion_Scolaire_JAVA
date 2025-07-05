package view;

import model.*;
import model.Module;
import service.*;

import java.util.List;
import java.util.Scanner;

public class RPView {
    private RP rp;
    private RPService rpService;
    private ClasseService classeService;
    private ModuleService moduleService;
    private ProfesseurService professeurService;
    private InscriptionService inscriptionService;
    private DemandeService demandeService;
    private Scanner scanner;

    public RPView(RP rp, RPService rpService, ClasseService classeService, ModuleService moduleService,
                 ProfesseurService professeurService, InscriptionService inscriptionService, DemandeService demandeService) {
        this.rp = rp;
        this.rpService = rpService;
        this.classeService = classeService;
        this.moduleService = moduleService;
        this.professeurService = professeurService;
        this.inscriptionService = inscriptionService;
        this.demandeService = demandeService;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        int choix;
        do {
            System.out.println("\n===== MENU RESPONSABLE PÉDAGOGIQUE =====");
            System.out.println("Connecté en tant que : " + rp.getNomComplet());
            System.out.println("1. Gérer les classes");
            System.out.println("2. Gérer les professeurs");
            System.out.println("3. Gérer les modules");
            System.out.println("4. Gérer les inscriptions");
            System.out.println("5. Gérer les demandes");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // vider buffer

            switch (choix) {
                case 1 -> gererClasses();
                case 2 -> gererProfesseurs();
                case 3 -> gererModules();
                case 4 -> gererInscriptions();
                case 5 -> gererDemandes();
            }
        } while (choix != 0);
    }

    private void gererClasses() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES CLASSES =====");
            System.out.println("1. Créer une classe");
            System.out.println("2. Lister les classes");
            System.out.println("3. Affecter un professeur");
            System.out.println("4. Affecter un module");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    System.out.print("Libellé : ");
                    String libelle = scanner.nextLine();
                    System.out.print("Filière : ");
                    String filiere = scanner.nextLine();
                    System.out.print("Niveau : ");
                    String niveau = scanner.nextLine();
                    Classe classe = new Classe(libelle, filiere, niveau);
                    rpService.ajouterClasse(classe);
                    System.out.println("Classe créée avec succès.");
                }
                case 2 -> {
                    List<Classe> classes = rpService.listerClasses();
                    if (classes.isEmpty()) {
                        System.out.println("Aucune classe.");
                    } else {
                        System.out.println("Liste des classes :");
                        for (Classe c : classes) {
                            System.out.println("- " + c);
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Libellé de la classe : ");
                    String libelle = scanner.nextLine();
                    Classe classe = classeService.chercherClasseParLibelle(libelle);
                    if (classe == null) {
                        System.out.println("Classe introuvable.");
                        return;
                    }

                    System.out.print("Email du professeur : ");
                    String email = scanner.nextLine();
                    Professeur professeur = professeurService.chercherProfesseurParEmail(email);
                    if (professeur == null) {
                        System.out.println("Professeur introuvable.");
                        return;
                    }

                    rpService.affecterProfesseurClasse(professeur, classe);
                    System.out.println("Professeur affecté avec succès.");
                }
                case 4 -> {
                    System.out.print("Libellé de la classe : ");
                    String libelle = scanner.nextLine();
                    Classe classe = classeService.chercherClasseParLibelle(libelle);
                    if (classe == null) {
                        System.out.println("Classe introuvable.");
                        return;
                    }

                    System.out.print("Nom du module : ");
                    String nom = scanner.nextLine();
                    Module module = moduleService.chercherModuleParNom(nom);
                    if (module == null) {
                        System.out.println("Module introuvable.");
                        return;
                    }

                    rpService.affecterModuleClasse(module, classe);
                    System.out.println("Module affecté avec succès.");
                }
            }
        } while (choix != 0);
    }

    private void gererProfesseurs() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES PROFESSEURS =====");
            System.out.println("1. Créer un professeur");
            System.out.println("2. Lister les professeurs");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String motDePasse = scanner.nextLine();
                    System.out.print("Spécialité : ");
                    String specialite = scanner.nextLine();
                    Professeur professeur = new Professeur(nom, prenom, email, motDePasse, specialite);
                    rpService.ajouterProfesseur(professeur);
                    System.out.println("Professeur créé avec succès.");
                }
                case 2 -> {
                    List<Professeur> professeurs = rpService.listerProfesseurs();
                    if (professeurs.isEmpty()) {
                        System.out.println("Aucun professeur.");
                    } else {
                        System.out.println("Liste des professeurs :");
                        for (Professeur p : professeurs) {
                            System.out.println("- " + p);
                        }
                    }
                }
            }
        } while (choix != 0);
    }

    private void gererModules() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES MODULES =====");
            System.out.println("1. Créer un module");
            System.out.println("2. Lister les modules");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    Module module = new Module(nom);
                    rpService.ajouterModule(module);
                    System.out.println("Module créé avec succès.");
                }
                case 2 -> {
                    List<Module> modules = rpService.listerModules();
                    if (modules.isEmpty()) {
                        System.out.println("Aucun module.");
                    } else {
                        System.out.println("Liste des modules :");
                        for (Module m : modules) {
                            System.out.println("- " + m);
                        }
                    }
                }
            }
        } while (choix != 0);
    }

    private void gererInscriptions() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES INSCRIPTIONS =====");
            System.out.println("1. Lister les inscriptions");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    List<Inscription> inscriptions = inscriptionService.listerInscriptions();
                    if (inscriptions.isEmpty()) {
                        System.out.println("Aucune inscription.");
                    } else {
                        System.out.println("Liste des inscriptions :");
                        for (Inscription i : inscriptions) {
                            System.out.println("- " + i);
                        }
                    }
                }
            }
        } while (choix != 0);
    }

    private void gererDemandes() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES DEMANDES =====");
            System.out.println("1. Lister les demandes");
            System.out.println("2. Traiter une demande");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    List<Demande> demandes = rpService.listerDemandes();
                    if (demandes.isEmpty()) {
                        System.out.println("Aucune demande.");
                    } else {
                        System.out.println("Liste des demandes :");
                        for (Demande d : demandes) {
                            System.out.println("- " + d);
                        }
                    }
                }
                case 2 -> {
                    System.out.print("ID de la demande : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Demande demande = demandeService.chercherDemandeParId(id);
                    if (demande == null) {
                        System.out.println("Demande introuvable.");
                        return;
                    }

                    System.out.println("1. Accepter");
                    System.out.println("2. Refuser");
                    System.out.print("Votre choix : ");
                    int choixEtat = scanner.nextInt();
                    scanner.nextLine();

                    EtatDemande etat = choixEtat == 1 ? EtatDemande.ACCEPTEE : EtatDemande.REFUSEE;
                    rpService.traiterDemande(demande, etat);
                    System.out.println("Demande traitée avec succès.");
                }
            }
        } while (choix != 0);
    }
}
