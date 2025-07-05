package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class AuthentificationService {
    private List<Utilisateur> utilisateurs;

    public AuthentificationService() {
        this.utilisateurs = new ArrayList<>();

        // Création d'exemples d'utilisateurs
        utilisateurs.add(new RP("Admin", "Admin", "admin@admin.com", "passrp"));
        utilisateurs.add(new Professeur("Prof1", "Prof1", "prof1@prof.com", "passprof", "Maître de Conférences"));
        utilisateurs.add(new Attache("Att1", "Att1", "att1@att.com", "passatt", "Informatique"));
        utilisateurs.add(new Etudiant("Etu1", "Etu1", "etu1@etu.com", "passetu", "ETU001"));
    }

    public Utilisateur authentifier(String email, String motDePasse) {
        for (Utilisateur u : utilisateurs) {
            if (u.getEmail().equals(email) && u.getMotDePasse().equals(motDePasse)) {
                return u;
            }
        }
        return null;
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        if (!utilisateurs.contains(utilisateur)) {
            utilisateurs.add(utilisateur);
        }
    }
}