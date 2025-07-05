<?php
require_once MODELS_PATH . '/User.php';

class AuthController {
    private $user;

    public function __construct() {
        $this->user = new User();
    }

    public function login() {
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $identifiant = $_POST['identifiant'] ?? '';
            $password = $_POST['password'] ?? '';

            if ($this->user->authenticate($identifiant, $password)) {
                $_SESSION['user'] = [
                    'id' => $this->user->getId(),
                    'nom_complet' => $this->user->getNomComplet(),
                    'role' => $this->user->getRole()
                ];
                setFlashMessage('success', 'Connexion réussie !');
                redirect('dashboard');
            } else {
                setFlashMessage('error', 'Identifiant ou mot de passe incorrect.');
            }
        }
        include VIEWS_PATH . '/auth/login.php';
    }

    public function logout() {
        session_destroy();
        setFlashMessage('success', 'Vous avez été déconnecté avec succès.');
        redirect('login');
    }
}
?> 