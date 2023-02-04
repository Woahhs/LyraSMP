package org.woahhs.essentials;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.woahhs.utils.Chat;

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
            sender.sendMessage(chat.translateChat("&b&lLyra >> &cUsage: /gamemode <gamemode> | /gamemode <gamemode> <player>"));
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
                sender.sendMessage(chat.translateChat("&b&lLyra >> &fThat player isn't online."));
                return true;
            }
            return setPlayerGamemode(args, player, target);
        }
        return false;
    }

    private boolean setPlayerGamemode(String[] args, Player player, Player target) {
        switch (args[0].toLowerCase()) {
            case "survival", "0", "s" -> {
                if(player != target){
                    target.setGameMode(GameMode.SURVIVAL);
                    target.sendMessage(chat.translateChat("&b&lLyra >> &fYour gamemode is now " + player.getGameMode()));
                    player.sendMessage(chat.translateChat("&b&lLyra >> &fYou set " + target.getName() +"'s to"));
                    break;
                }
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(chat.translateChat("&b&lLyra >> &fYour gamemode is now " + player.getGameMode()));
            }
            case "creative", "1", "c" -> {
                if(player != target){
                    target.setGameMode(GameMode.CREATIVE);
                    target.sendMessage(chat.translateChat("&b&lLyra >> &fYour gamemode is now " + player.getGameMode()));
                    player.sendMessage(chat.translateChat("&b&lLyra >> &fYou set " + target.getName() +"'s to"));
                    break;
                }
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(chat.translateChat("&b&lLyra >> &fYour gamemode is now " + player.getGameMode()));
            }
            case "adventure", "2", "a" -> {
                if(player != target){
                    target.setGameMode(GameMode.ADVENTURE);
                    target.sendMessage(chat.translateChat("&b&lLyra >> &fYour gamemode is now " + player.getGameMode()));
                    player.sendMessage(chat.translateChat("&b&lLyra >> &fYou set " + target.getName() +"'s to"));
                    break;
                }
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(chat.translateChat("&b&lLyra >> &fYour gamemode is now " + player.getGameMode()));
            }
            case "spectator", "3", "spec" -> {
                if(player != target){
                    target.setGameMode(GameMode.SPECTATOR);
                    target.sendMessage(chat.translateChat("&b&lLyra >> &fYour gamemode is now " + player.getGameMode()));
                    player.sendMessage(chat.translateChat("&b&lLyra >> &fYou set " + target.getName() +"'s to"));
                    break;
                }
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(chat.translateChat("&b&lLyra >> &fYour gamemode is now " + player.getGameMode()));
            }
        }
        return true;
    }
}
