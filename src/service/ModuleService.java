package service;

import model.Module;
import model.Professeur;
import model.Classe;

import java.util.ArrayList;
import java.util.List;

public class ModuleService {
    private List<Module> modules;

    public ModuleService() {
        this.modules = new ArrayList<>();
    }

    // ✅ Créer un module
    public Module creerModule(String libelle) {
        Module module = new Module(libelle);
        modules.add(module);
        return module;
    }

    // ✅ Lister les modules
    public List<Module> listerModules() {
        return new ArrayList<>(modules);
    }

    // ✅ Rechercher un module par son libellé
    public Module chercherParLibelle(String libelle) {
        for (Module m : modules) {
            if (m.getLibelle().equalsIgnoreCase(libelle)) {
                return m;
            }
        }
        return null;
    }

    // ✅ Supprimer un module (optionnel)
    public boolean supprimerModule(String libelle) {
        Module m = chercherParLibelle(libelle);
        if (m != null) {
            return modules.remove(m);
        }
        return false;
    }

    public void ajouterModule(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    public Module chercherModuleParNom(String nom) {
        for (Module m : modules) {
            if (m.getLibelle().equalsIgnoreCase(nom)) {
                return m;
            }
        }
        return null;
    }

    public void affecterProfesseur(Module module, Professeur professeur) {
        module.ajouterProfesseur(professeur);
    }

    public void affecterClasse(Module module, Classe classe) {
        module.ajouterClasse(classe);
    }
}
