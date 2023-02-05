package org.woahhs.essentials;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.woahhs.utils.Chat;

import static org.woahhs.Plugin.prefix;

public class GamemodeCommand implements CommandExecutor {

    private Chat chat = new Chat();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!cmd.getName().equalsIgnoreCase("gamemode")){
            return true;
        }
        if(!(sender instanceof Player)){
            sender.sendMessage("You cannot execute this command.");
            return true;
        }
        if(!sender.hasPermission("smp.gamemode") || !sender.isOp()){
            sender.sendMessage(chat.translateChat("&cYou cannot execute this command."));
            return true;
        }
        if(args.length > 2 || args.length < 1){
            sender.sendMessage(chat.translateChat(prefix + " &cUsage: /gamemode <gamemode> | /gamemode <gamemode> <player>"));
            return true;
        }

        if(args.length == 1){
            Player player = (Player) sender;
            return setPlayerGamemode(args, player, player);
        }
        if(args.length == 2){
            Player player = (Player) sender;
            Player target = Bukkit.getPlayerExact(args[1]);
            if(target == null || !target.isOnline()){
                sender.sendMessage(chat.translateChat(prefix +" &fThat player isn't online."));
                return true;
            }
            return setPlayerGamemode(args, player, target);
        }
        return false;
    }

    private boolean setPlayerGamemode(String[] args, Player player, Player target) {
        GameMode gameMode = null;
        switch (args[0].toLowerCase()) {
            case "survival", "0", "s" -> {
                gameMode = GameMode.SURVIVAL;
            }
            case "creative", "1", "c" -> {
                gameMode = GameMode.CREATIVE;
            }
            case "adventure", "2", "a" -> {
                gameMode = GameMode.ADVENTURE;
            }
            case "spectator", "3", "spec" -> {
                gameMode = GameMode.SPECTATOR;
            }
            default -> {
                player.sendMessage(chat.translateChat(prefix + " &cUsage: /gamemode <gamemode> | /gamemode <gamemode> <player>"));
            }
        }
        if(gameMode == null){
            player.sendMessage(chat.translateChat(prefix + " &cGamemode is null. Please use an existing Gamemode."));
            return true;
        }
        if(player != target){
            target.setGameMode(gameMode);
            target.sendMessage(chat.translateChat(prefix + " &fYour gamemode is now " + player.getGameMode()));
            player.sendMessage(chat.translateChat(prefix + " &fYou set " + target.getName() +"'s gamemode to " + target.getGameMode()));
            return true;
        }
        player.setGameMode(gameMode);
        player.sendMessage(chat.translateChat(prefix +" &fYour gamemode is now " + player.getGameMode()));
        return true;
    }
}
