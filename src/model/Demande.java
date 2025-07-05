package model;

import java.time.LocalDate;

public class Demande {
    private static int compteur = 1;

    private int id;
    private Etudiant etudiant;
    private String motif;
    private LocalDate dateDemande;
    private EtatDemande etat;

    public Demande(Etudiant etudiant, String motif) {
        this.id = compteur++;
        this.etudiant = etudiant;
        this.motif = motif;
        this.dateDemande = LocalDate.now();
        this.etat = EtatDemande.EN_ATTENTE;
    }

    public int getId() {
        return id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public String getMotif() {
        return motif;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public EtatDemande getEtat() {
        return etat;
    }

    public void setEtat(EtatDemande etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Demande [id=" + id + ", etudiant=" + etudiant + ", motif=" + motif + ", date=" + dateDemande + ", etat=" + etat + "]";
    }
}
