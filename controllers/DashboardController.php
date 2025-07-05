<?php
class DashboardController {
    private $db;

    public function __construct() {
        $this->db = Database::getInstance()->getConnection();
    }

    public function index() {
        $user = $_SESSION['user'];
        $data = [];

        switch ($user['role']) {
            case ROLE_RP:
                $data = $this->getRPData();
                break;
            case ROLE_PROFESSEUR:
                $data = $this->getProfesseurData();
                break;
            case ROLE_ATTACHE:
                $data = $this->getAttacheData();
                break;
            case ROLE_ETUDIANT:
                $data = $this->getEtudiantData();
                break;
        }

        include VIEWS_PATH . '/dashboard/index.php';
    }

    private function getRPData() {
        // Statistiques pour le RP
        $stats = [
            'total_classes' => $this->getCount('classes'),
            'total_professeurs' => $this->getCount('professeurs'),
            'total_etudiants' => $this->getCount('etudiants'),
            'total_modules' => $this->getCount('modules')
        ];

        // Dernières inscriptions
        $query = "SELECT e.nom, e.prenom, c.nom as classe, i.date_inscription 
                 FROM inscriptions i 
                 JOIN etudiants e ON i.etudiant_id = e.id 
                 JOIN classes c ON i.classe_id = c.id 
                 ORDER BY i.date_inscription DESC LIMIT 5";
        $stmt = $this->db->query($query);
        $dernieres_inscriptions = $stmt->fetchAll(PDO::FETCH_ASSOC);

        return [
            'stats' => $stats,
            'dernieres_inscriptions' => $dernieres_inscriptions
        ];
    }

    private function getProfesseurData() {
        // Cours du professeur
        $query = "SELECT c.nom as classe, m.nom as module, h.jour, h.heure_debut, h.heure_fin 
                 FROM horaires h 
                 JOIN classes c ON h.classe_id = c.id 
                 JOIN modules m ON h.module_id = m.id 
                 WHERE h.professeur_id = :prof_id 
                 ORDER BY h.jour, h.heure_debut";
        $stmt = $this->db->prepare($query);
        $stmt->execute(['prof_id' => $_SESSION['user']['id']]);
        $cours = $stmt->fetchAll(PDO::FETCH_ASSOC);

        return [
            'cours' => $cours
        ];
    }

    private function getAttacheData() {
        // Demandes en attente
        $query = "SELECT d.*, e.nom, e.prenom 
                 FROM demandes d 
                 JOIN etudiants e ON d.etudiant_id = e.id 
                 WHERE d.statut = 'en_attente' 
                 ORDER BY d.date_demande DESC";
        $stmt = $this->db->query($query);
        $demandes = $stmt->fetchAll(PDO::FETCH_ASSOC);

        return [
            'demandes' => $demandes
        ];
    }

    private function getEtudiantData() {
        // Notes de l'étudiant
        $query = "SELECT n.*, m.nom as module 
                 FROM notes n 
                 JOIN modules m ON n.module_id = m.id 
                 WHERE n.etudiant_id = :etudiant_id 
                 ORDER BY m.nom";
        $stmt = $this->db->prepare($query);
        $stmt->execute(['etudiant_id' => $_SESSION['user']['id']]);
        $notes = $stmt->fetchAll(PDO::FETCH_ASSOC);

        // Emploi du temps
        $query = "SELECT h.*, m.nom as module, c.nom as classe 
                 FROM horaires h 
                 JOIN modules m ON h.module_id = m.id 
                 JOIN classes c ON h.classe_id = c.id 
                 JOIN inscriptions i ON i.classe_id = c.id 
                 WHERE i.etudiant_id = :etudiant_id 
                 ORDER BY h.jour, h.heure_debut";
        $stmt = $this->db->prepare($query);
        $stmt->execute(['etudiant_id' => $_SESSION['user']['id']]);
        $emploi_du_temps = $stmt->fetchAll(PDO::FETCH_ASSOC);

        return [
            'notes' => $notes,
            'emploi_du_temps' => $emploi_du_temps
        ];
    }

    private function getCount($table) {
        $query = "SELECT COUNT(*) as count FROM $table";
        $stmt = $this->db->query($query);
        return $stmt->fetch(PDO::FETCH_ASSOC)['count'];
    }
}
?> 