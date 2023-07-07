package fr.tikgames.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import fr.tikgames.jeux.Game;

public class CommandStart implements CommandExecutor {
    private Game game;

    public CommandStart(Game game) {
        this.game = game;
    }
    

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seul un joueur peut exécuter cette commande.");
            return true;
        }

        Player player = (Player) sender;
        if (player.isOp()) {
            if (!game.isRunning()) {
                game.startGame();
            } else {
                player.sendMessage("Le jeu a déjà commencé.");
            }
        } else {
            player.sendMessage("Vous devez être opérateur pour démarrer le jeu.");
        }

        return true;
    }
}