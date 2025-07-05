import model.*;
import service.*;
import view.*;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Initialisation des services
        AuthentificationService authService = new AuthentificationService();
        ClasseService classeService = new ClasseService();
        ModuleService moduleService = new ModuleService();
        ProfesseurService professeurService = new ProfesseurService();
        InscriptionService inscriptionService = new InscriptionService();
        DemandeService demandeService = new DemandeService();

        // Création des utilisateurs de test
        RP rp = new RP("Admin", "Admin", "admin@admin.com", "admin123");
        Attache attache = new Attache("Attache", "Attache", "attache@attache.com", "attache123", "Informatique");
        Professeur professeur = new Professeur("Professeur", "Professeur", "prof@prof.com", "prof123", "Informatique");
        Etudiant etudiant = new Etudiant("Etudiant", "Etudiant", "etudiant@etudiant.com", "etudiant123", "ETU001");

        // Ajout des utilisateurs au service d'authentification
        authService.ajouterUtilisateur(rp);
        authService.ajouterUtilisateur(attache);
        authService.ajouterUtilisateur(professeur);
        authService.ajouterUtilisateur(etudiant);

        // Initialisation des services spécifiques
        RPService rpService = new RPService(rp);
        AttacheService attacheService = new AttacheService(attache);

        // Création des vues
        RPView rpView = new RPView(rp, rpService, classeService, moduleService, professeurService, inscriptionService, demandeService);
        AttacheView attacheView = new AttacheView(attache, attacheService, classeService, inscriptionService, demandeService);
        ProfesseurView professeurView = new ProfesseurView(professeur, professeurService, moduleService, classeService);
        EtudiantView etudiantView = new EtudiantView(etudiant, inscriptionService, demandeService);

        // Démarrage de l'application
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Connexion RP");
            System.out.println("2. Connexion Attaché");
            System.out.println("3. Connexion Professeur");
            System.out.println("4. Connexion Étudiant");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    System.out.print("Email : ");
                    String emailRP = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String mdpRP = scanner.nextLine();
                    Utilisateur userRP = authService.authentifier(emailRP, mdpRP);
                    if (userRP instanceof RP) {
                        rpView.menu();
                    } else {
                        System.out.println("Identifiants invalides ou utilisateur non autorisé.");
                    }
                }
                case 2 -> {
                    System.out.print("Email : ");
                    String emailAttache = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String mdpAttache = scanner.nextLine();
                    Utilisateur userAttache = authService.authentifier(emailAttache, mdpAttache);
                    if (userAttache instanceof Attache) {
                        attacheView.menu();
                    } else {
                        System.out.println("Identifiants invalides ou utilisateur non autorisé.");
                    }
                }
                case 3 -> {
                    System.out.print("Email : ");
                    String emailProf = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String mdpProf = scanner.nextLine();
                    Utilisateur userProf = authService.authentifier(emailProf, mdpProf);
                    if (userProf instanceof Professeur) {
                        professeurView.menu();
                    } else {
                        System.out.println("Identifiants invalides ou utilisateur non autorisé.");
                    }
                }
                case 4 -> {
                    System.out.print("Email : ");
                    String emailEtudiant = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String mdpEtudiant = scanner.nextLine();
                    Utilisateur userEtudiant = authService.authentifier(emailEtudiant, mdpEtudiant);
                    if (userEtudiant instanceof Etudiant) {
                        etudiantView.menu();
                    } else {
                        System.out.println("Identifiants invalides ou utilisateur non autorisé.");
                    }
                }
                case 0 -> continuer = false;
                default -> System.out.println("Choix invalide.");
            }
        }
        scanner.close();
    }
}

