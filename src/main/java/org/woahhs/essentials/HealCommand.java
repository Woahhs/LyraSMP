package org.woahhs.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.woahhs.utils.Chat;

import static org.woahhs.Plugin.prefix;

public class HealCommand implements CommandExecutor {

    private final Chat chat = new Chat();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!cmd.getName().equalsIgnoreCase("heal")){
            return true;
        }
        if(!(sender instanceof Player)){
            sender.sendMessage("You cannot execute this command.");
            return true;
        }
        if(!sender.hasPermission("smp.heal") || !sender.isOp()){
            sender.sendMessage(chat.translateChat("&cYou cannot execute this command."));
            return true;
        }
        if(args.length > 1){
            sender.sendMessage(chat.translateChat(prefix + " &cUsage: /heal | /heal <player>"));
            return true;
        }
        if (args.length == 0) {
            Player player = (Player) sender;
            return healPlayer(player, player);
        }
        Player player = (Player) sender;
        Player target = Bukkit.getPlayerExact(args[0]);
        return healPlayer(player, target);
    }

    private boolean healPlayer(Player player, Player target){
        if(player != target){
            target.setHealth(20.0);
            target.setSaturation(20);
            target.sendMessage(chat.translateChat(prefix + " &fYou have been healed!"));
            player.sendMessage(chat.translateChat(prefix + " &fYou healed " + target.getName() + "!"));
            return true;
        }
        player.setHealth(20.0);
        player.setSaturation(20);
        player.sendMessage(chat.translateChat(prefix + " &fYou have been healed!"));
        return true;
    }



}
