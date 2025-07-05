package view;

import model.*;
import model.Module;
import service.*;
import service.ProfesseurService;
import service.ModuleService;

import java.util.List;
import java.util.Scanner;

public class ProfesseurView {
    private Professeur professeur;
    private ProfesseurService professeurService;
    private ModuleService moduleService;
    private ClasseService classeService;
    private Scanner scanner;

    public ProfesseurView(Professeur professeur, ProfesseurService professeurService, ModuleService moduleService, ClasseService classeService) {
        this.professeur = professeur;
        this.professeurService = professeurService;
        this.moduleService = moduleService;
        this.classeService = classeService;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        int choix;
        do {
            System.out.println("\n===== MENU PROFESSEUR =====");
            System.out.println("1. Voir mes classes");
            System.out.println("2. Voir les étudiants d'une classe");
            System.out.println("3. Voir mes modules");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> afficherClasses();
                case 2 -> afficherEtudiantsParClasse();
                case 3 -> afficherModules();
            }
        } while (choix != 0);
    }

    private void afficherClasses() {
        List<Classe> classes = professeurService.listerClassesDunProf(professeur.getEmail());
        if (classes.isEmpty()) {
            System.out.println("Vous n'avez aucune classe affectée.");
            return;
        }
        System.out.println("Vos classes :");
        for (Classe c : classes) {
            System.out.println("- " + c);
        }
    }

    private void afficherEtudiantsParClasse() {
        System.out.print("Libellé de la classe : ");
        String libelle = scanner.nextLine();
        Classe classe = null;
        for (Classe c : professeurService.listerClassesDunProf(professeur.getEmail())) {
            if (c.getLibelle().equalsIgnoreCase(libelle)) {
                classe = c;
                break;
            }
        }
        if (classe == null) {
            System.out.println("Classe introuvable ou non assignée à ce professeur.");
            return;
        }

        List<Etudiant> etudiants = classeService.listerEtudiantsParClasse(classe);
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant dans cette classe.");
            return;
        }

        System.out.println("Étudiants de la classe " + classe.getLibelle() + " :");
        for (Etudiant e : etudiants) {
            System.out.println("- " + e.getNomComplet());
        }
    }

    private void afficherModules() {
        List<Module> modules = moduleService.listerModules();
        if (modules.isEmpty()) {
            System.out.println("Vous n'avez aucun module assigné.");
            return;
        }
        System.out.println("Vos modules :");
        for (Module m : modules) {
            System.out.println("- " + m);
        }
    }
}
