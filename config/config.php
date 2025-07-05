<?php
// Configuration de l'application
define('APP_NAME', 'Gestion Scolaire');
define('APP_URL', 'http://localhost/gestion-scolaire');

// Configuration de la base de données
define('DB_HOST', 'localhost');
define('DB_NAME', 'gestion_scolaire');
define('DB_USER', 'root');
define('DB_PASS', '');

// Configuration des chemins
define('ROOT_PATH', dirname(__DIR__));
define('VIEWS_PATH', ROOT_PATH . '/views');
define('CONTROLLERS_PATH', ROOT_PATH . '/controllers');
define('MODELS_PATH', ROOT_PATH . '/models');

// Configuration des rôles
define('ROLE_RP', 'RP');
define('ROLE_PROFESSEUR', 'PROFESSEUR');
define('ROLE_ATTACHE', 'ATTACHE');
define('ROLE_ETUDIANT', 'ETUDIANT');

// Fonction pour rediriger
function redirect($page) {
    header('Location: ' . APP_URL . '/index.php?page=' . $page);
    exit();
}

// Fonction pour afficher les messages flash
function setFlashMessage($type, $message) {
    $_SESSION['flash'] = [
        'type' => $type,
        'message' => $message
    ];
}

function getFlashMessage() {
    if (isset($_SESSION['flash'])) {
        $flash = $_SESSION['flash'];
        unset($_SESSION['flash']);
        return $flash;
    }
    return null;
}
?> 