# Application de Gestion Scolaire

Une application web moderne pour la gestion d'un établissement scolaire, développée en PHP avec une interface utilisateur dynamique.

## Fonctionnalités

- Authentification multi-rôles (RP, Professeur, Attaché, Étudiant)
- Tableau de bord personnalisé selon le rôle
- Gestion des classes et des modules
- Gestion des inscriptions
- Suivi des notes
- Gestion des emplois du temps
- Traitement des demandes

## Prérequis

- PHP 7.4 ou supérieur
- MySQL 5.7 ou supérieur
- Serveur web (Apache/Nginx)
- Composer (pour la gestion des dépendances)

## Installation

1. Clonez le dépôt :
```bash
git clone [URL_DU_REPO]
cd gestion-scolaire
```

2. Configurez votre serveur web pour pointer vers le dossier du projet

3. Créez la base de données :
```bash
mysql -u root -p < database.sql
```

4. Configurez les paramètres de connexion à la base de données dans `config/config.php`

5. Assurez-vous que les permissions des dossiers sont correctes :
```bash
chmod -R 755 .
chmod -R 777 storage/
```

## Utilisation

1. Accédez à l'application via votre navigateur
2. Connectez-vous avec l'un des comptes de test :
   - RP : identifiant `rp1`, mot de passe `password`
   - Professeur : identifiant `prof1`, mot de passe `password`
   - Attaché : identifiant `attache1`, mot de passe `password`
   - Étudiant : identifiant `etudiant1`, mot de passe `password`

## Structure du projet

```
├── config/             # Configuration
├── controllers/        # Contrôleurs
├── models/            # Modèles
├── views/             # Vues
│   ├── auth/         # Vues d'authentification
│   ├── dashboard/    # Vues du tableau de bord
│   └── layouts/      # Layouts communs
├── database.sql      # Script de création de la base de données
└── index.php         # Point d'entrée de l'application
```

## Sécurité

- Les mots de passe sont hashés avec bcrypt
- Protection contre les injections SQL
- Validation des entrées utilisateur
- Gestion des sessions sécurisée

## Contribution

Les contributions sont les bienvenues ! N'hésitez pas à :
1. Fork le projet
2. Créer une branche pour votre fonctionnalité
3. Commiter vos changements
4. Pousser vers la branche
5. Ouvrir une Pull Request

## Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.
