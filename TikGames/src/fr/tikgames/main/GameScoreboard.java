package fr.tikgames.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class GameScoreboard {

    private Scoreboard scoreboard;
    private Objective objective;

    public GameScoreboard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        this.scoreboard = manager.getNewScoreboard();
        this.objective = this.scoreboard.registerNewObjective("game", "dummy");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName(ChatColor.GOLD + "TikGames"); 
    }

    public void updateScoreboard(int playerCount, int minPlayers, String status, int countdown) {
        for (String entry : scoreboard.getEntries()) {
            scoreboard.resetScores(entry);
        }

        Score score1 = objective.getScore(ChatColor.BLUE + "Joueurs : " + playerCount + "/" + minPlayers);
        score1.setScore(3);  // Changer le score à 3

        if (status.equals("En attente...")) {
            Score score2 = objective.getScore(ChatColor.RED + "Play.serveur.net");
            score2.setScore(2);
        } else {
            Score score2 = objective.getScore(ChatColor.YELLOW + "Commence dans : " + ChatColor.RED + countdown);
            score2.setScore(2); // Changer le score à 2
            Score score3 = objective.getScore(ChatColor.RED + "Play.serveur.net");
            score3.setScore(1);
        }
    }


    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }
}
