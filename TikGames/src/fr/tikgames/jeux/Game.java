package fr.tikgames.jeux;

import fr.tikgames.main.GameScoreboard;
import fr.tikgames.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private Main plugin;
    private Map<Player, MobClass> playerClasses;
    private Map<Player, Integer> playerKillCounts;
    private boolean gameStarted;
    private int minPlayers;
    private int maxPlayers;
    private GameScoreboard scoreboard;

    public Game(Main plugin, int minPlayers, int maxPlayers) {
        this.plugin = plugin;
        this.playerClasses = new HashMap<>();
        this.playerKillCounts = new HashMap<>();
        this.gameStarted = false;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.scoreboard = new GameScoreboard();
    }
    
    public void setScoreboard(GameScoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void assignClass(Player player, MobClass mobClass) {
        playerClasses.put(player, mobClass);
    }
    
    public GameScoreboard getScoreboard() {
        return scoreboard;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void onPlayerShootSnowball(Player player) {
        MobClass mobClass = playerClasses.get(player);
        if (mobClass != null) {
            mobClass.shootSnowball();
        }
    }

    public void onPlayerUseSpecialAbility(Player player) {
        MobClass mobClass = playerClasses.get(player);
        if (mobClass != null) {
            mobClass.specialAbility();
        }
    }

    public void onPlayerKill(Player killer, Player victim) {
        int killCount = playerKillCounts.getOrDefault(killer, 0) + 1;
        playerKillCounts.put(killer, killCount);

        if (killCount % 3 == 0) {
            MobClass currentClass = playerClasses.get(killer);
            MobClass nextClass;
            if (currentClass instanceof CreeperClass) {
                nextClass = new CowClass(killer, plugin);
            } else if (currentClass instanceof CowClass) {
                nextClass = new BlazeClass(killer, plugin);
            } else if (currentClass instanceof BlazeClass) {
                nextClass = new IronGolemClass(killer, plugin);
            } else {
                nextClass = currentClass;
            }
            assignClass(killer, nextClass);
        }
    }

    public void startGame() {
        if (gameStarted || Bukkit.getOnlinePlayers().size() < minPlayers || Bukkit.getOnlinePlayers().size() > maxPlayers) {
            return;
        }

        
        gameStarted = true;
    }

    public boolean isRunning() {
        return gameStarted;
    }

}