package org.woahhs.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.woahhs.utils.Chat;

import static org.woahhs.Plugin.prefix;

public class FlyCommand implements CommandExecutor {

    private Chat chat = new Chat();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!cmd.getName().equalsIgnoreCase("fly")){
            return true;
        }
        if(!(sender instanceof Player)){
            sender.sendMessage("You cannot execute this command.");
            return true;
        }
        if(!sender.hasPermission("smp.fly") || !sender.isOp()){
            sender.sendMessage(chat.translateChat("&cYou cannot execute this command."));
            return true;
        }
        switch (args.length) {
            case 0 -> {
                Player player = (Player) sender;
                player.setAllowFlight(!player.getAllowFlight());
                player.sendMessage(chat.translateChat(prefix +" &fYour flight is now " + player.getAllowFlight()));
            }
            case 1 -> {
                Player target = Bukkit.getPlayerExact(args[0]);
                Player player = (Player) sender;
                target.setAllowFlight(!target.getAllowFlight());
                target.sendMessage(chat.translateChat(prefix + " &fYour flight is now " + target.getAllowFlight()));
                player.sendMessage(chat.translateChat(prefix + " &f" + target.getName() +"'s flight is now " + target.getAllowFlight()));
            }
            default -> {
                sender.sendMessage(chat.translateChat(prefix + " &fUsage /fly | /fly <target>"));
            }
        }
        return false;
    }
}
