package view;

import model.*;
import service.*;

import java.util.Scanner;

public class MenuPrincipal {
    private AuthentificationService serviceAuth;
    private LogService logService;
    private Scanner scanner;

    public MenuPrincipal(AuthentificationService serviceAuth) {
        this.serviceAuth = serviceAuth;
        this.logService = LogService.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        System.out.println("===== BIENVENUE DANS L'APPLICATION DE GESTION SCOLAIRE =====");
        
        while (true) {
            System.out.print("Entrez votre identifiant : ");
            String identifiant = scanner.nextLine();
            System.out.print("Entrez votre mot de passe : ");
            String mdp = scanner.nextLine();

            Utilisateur user = serviceAuth.authentifier(identifiant, mdp);
            if (user == null) {
                System.out.println("Identifiant ou mot de passe incorrect. Veuillez réessayer.");
                logService.logAction(null, "Tentative de connexion échouée avec l'identifiant: " + identifiant);
                continue;
            }

            System.out.println("Connexion réussie. Bienvenue, " + user.getNomComplet() + " !");
            logService.logAction(user, "Connexion réussie");
            
            // Redirection selon rôle
            if (user instanceof RP rp) {
                RPView viewRP = new RPView(rp, new RPService(rp), new ClasseService(), new ModuleService(), 
                    new ProfesseurService(), new InscriptionService(), new DemandeService());
                viewRP.menu();
            } else if (user instanceof Professeur prof) {
                ProfesseurView viewProf = new ProfesseurView(prof, new ProfesseurService(), new ModuleService(), new ClasseService());
                viewProf.menu();
            } else if (user instanceof Attache attache) {
                AttacheView viewAttache = new AttacheView(attache, new AttacheService(attache), new ClasseService(), new InscriptionService(), new DemandeService());
                viewAttache.menu();
            } else if (user instanceof Etudiant etu) {
                EtudiantView viewEtu = new EtudiantView(etu, new InscriptionService(), new DemandeService());
                viewEtu.menu();
            } else {
                System.out.println("Rôle utilisateur inconnu.");
                logService.logAction(user, "Tentative d'accès avec un rôle inconnu");
            }

            logService.logAction(user, "Déconnexion");
            System.out.println("Déconnexion...\n");
        }
    }
}
