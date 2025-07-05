package service;

import model.Utilisateur;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogService {
    private static final String LOG_FILE = "logs/actions.log";
    private static LogService instance;
    private PrintWriter writer;

    private LogService() {
        try {
            // Créer le dossier logs s'il n'existe pas
            new java.io.File("logs").mkdirs();
            writer = new PrintWriter(new FileWriter(LOG_FILE, true));
        } catch (IOException e) {
            System.err.println("Erreur lors de l'initialisation du fichier de log: " + e.getMessage());
        }
    }

    public static LogService getInstance() {
        if (instance == null) {
            instance = new LogService();
        }
        return instance;
    }

    public void logAction(Utilisateur utilisateur, String action) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logEntry = String.format("[%s] %s (%s) : %s", 
            timestamp, 
            utilisateur.getNomComplet(),
            utilisateur.getClass().getSimpleName(),
            action);
        
        try {
            writer.println(logEntry);
            writer.flush();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'écriture dans le fichier de log: " + e.getMessage());
        }
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
} 