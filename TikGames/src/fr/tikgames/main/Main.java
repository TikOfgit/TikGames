package fr.tikgames.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import fr.tikgames.jeux.Game;

public class Main extends JavaPlugin {
    private Game game;
    private GameScoreboard scoreboard;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        int minPlayers = config.getInt("minPlayers");
        int maxPlayers = config.getInt("maxPlayers");
        game = new Game(this, minPlayers, maxPlayers);
        scoreboard = new GameScoreboard();
        game.setScoreboard(scoreboard);
        scoreboard.updateScoreboard(Bukkit.getOnlinePlayers().size(), minPlayers, "En attente...", 0);
        this.getCommand("start").setExecutor(new CommandStart(game));
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(game), this);

        int countdownSeconds = config.getInt("countdownSeconds");

        Bukkit.getScheduler().runTaskLater(this, () -> {
            game.startGame();
        }, countdownSeconds * 20L);
    }
    
 
}