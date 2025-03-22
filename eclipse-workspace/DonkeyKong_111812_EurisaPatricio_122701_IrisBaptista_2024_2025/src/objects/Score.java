package objects;

import java.io.*;
import java.util.*;

import pt.iscte.poo.gui.ImageGUI;

public class Score {
    private String name;
    private int score;
    private static final String SCORE_FILE = "highscores.txt";

    public Score(String name, int score) {
        this.name = name;
        this.score = Math.max(score, 0);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

 
    // Salva os scores
    public static void saveScore(String name, int score) {
        List<Score> scores = loadScores();
        scores.add(new Score(name, score));
        scores.sort(new ScoreComparator());

        // Mantém apenas os top 10
        if (scores.size() > 10) {
            scores = scores.subList(0, 10);
        }

        try (BufferedWriter w = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (Score s : scores) {
                w.write(s.getName() + "," + s.getScore());
                w.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }

    // guarda score 
    public static List<Score> loadScores() {
    	//listas c as scores 
        List<Score> scores = new ArrayList<>();
        
        try (BufferedReader b = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = b.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                	//adiciona
                	   scores.add(new Score(parts[0], Integer.parseInt(parts[1])));
                }
            }
            } catch (FileNotFoundException e) {
            	System.err.println("Error finding the file ");
            } catch (IOException e) {
                System.err.println("Error reading ");
            }
             
        
        return scores;
    }

    // Exibe os high scores
    public static void HighScores() {
        List<Score> scores = loadScores();
        StringBuilder message = new StringBuilder("High Scores:\n");
        for (Score s : scores) {
            message.append(s).append("\n");
        }
        ImageGUI.getInstance().showMessage("High Scores", message.toString());
    }
    
    @Override
    public String toString() {
        return String.format("%s : %5d", name, score);
    }

}
