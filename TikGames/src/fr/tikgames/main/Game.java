package fr.tikgames.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Game {
    private Main plugin;
    private int minPlayers;
    private int maxPlayers;
    private boolean isRunning = false;
    private int countdown = 15;
    private GameScoreboard scoreboard;

    public Game(Main plugin, int minPlayers, int maxPlayers) {
        this.plugin = plugin;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.scoreboard = new GameScoreboard();
    }
    
    public int getMinPlayers() {
        return this.minPlayers;
    }
    
    public GameScoreboard getScoreboard() {
        return this.scoreboard;
    }

    public void startGame() {
        if (isRunning || Bukkit.getOnlinePlayers().size() < minPlayers || Bukkit.getOnlinePlayers().size() > maxPlayers) {
            return;
        }
        
        new BukkitRunnable() {
            @Override
            public void run() {
                if (countdown > 0) {
                    if (countdown == 15 || countdown == 10 || countdown <= 5) {
                        Bukkit.broadcastMessage("Le jeu commence dans " + countdown + " secondes!");
                    }
                    scoreboard.updateScoreboard(Bukkit.getOnlinePlayers().size(), minPlayers, "Préparation...", countdown);
                    countdown--;
                } else {
                  
                    isRunning = true;
                    Bukkit.broadcastMessage("Le jeu a commencé!");
                    scoreboard.updateScoreboard(Bukkit.getOnlinePlayers().size(), minPlayers, "Jeu en cours...", countdown);
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public boolean isRunning() {
        return isRunning;
    }
}