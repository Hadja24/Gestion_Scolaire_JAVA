package service;

import model.RP;
import model.Classe;
import model.Professeur;
import model.Module;
import model.Demande;
import model.EtatDemande;

import java.util.ArrayList;
import java.util.List;

public class RPService {
    private RP rp;
    private List<Classe> classes;
    private List<Professeur> professeurs;
    private List<Module> modules;
    private List<Demande> demandes;

    public RPService(RP rp) {
        this.rp = rp;
        this.classes = new ArrayList<>();
        this.professeurs = new ArrayList<>();
        this.modules = new ArrayList<>();
        this.demandes = new ArrayList<>();
    }

    public RP getRP() {
        return rp;
    }

    public void ajouterClasse(Classe classe) {
        if (!classes.contains(classe)) {
            classes.add(classe);
            System.out.println("Classe ajoutée par " + rp.getNomComplet());
        }
    }

    public void ajouterProfesseur(Professeur professeur) {
        if (!professeurs.contains(professeur)) {
            professeurs.add(professeur);
            System.out.println("Professeur ajouté par " + rp.getNomComplet());
        }
    }

    public void ajouterModule(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
            System.out.println("Module ajouté par " + rp.getNomComplet());
        }
    }

    public void ajouterDemande(Demande demande) {
        if (!demandes.contains(demande)) {
            demandes.add(demande);
            System.out.println("Demande ajoutée par " + rp.getNomComplet());
        }
    }

    public List<Classe> listerClasses() {
        return new ArrayList<>(classes);
    }

    public List<Professeur> listerProfesseurs() {
        return new ArrayList<>(professeurs);
    }

    public List<Module> listerModules() {
        return new ArrayList<>(modules);
    }

    public List<Demande> listerDemandes() {
        return new ArrayList<>(demandes);
    }

    public void traiterDemande(Demande demande, EtatDemande etat) {
        demande.setEtat(etat);
    }

    public void affecterProfesseurClasse(Professeur professeur, Classe classe) {
        classe.ajouterProfesseur(professeur);
    }

    public void affecterModuleClasse(Module module, Classe classe) {
        classe.ajouterModule(module);
    }
}
