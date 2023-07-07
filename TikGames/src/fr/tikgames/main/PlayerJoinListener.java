package fr.tikgames.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import fr.tikgames.jeux.Game;

public class PlayerJoinListener implements Listener {
    private Game game;

    public PlayerJoinListener(Game game) {
        this.game = game;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setScoreboard(game.getScoreboard().getScoreboard());  // Assigner le scoreboard au joueur qui rejoint
        
        if (!game.isRunning() && Bukkit.getOnlinePlayers().size() >= game.getMinPlayers()) {
            game.startGame();
        }
    }
}