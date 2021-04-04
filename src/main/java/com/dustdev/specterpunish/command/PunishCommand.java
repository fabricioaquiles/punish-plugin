package com.dustdev.specterpunish.command;

import com.dustdev.specterpunish.Main;
import com.dustdev.specterpunish.configuration.values.GeneralValue;
import com.dustdev.specterpunish.configuration.values.MensagensValue;
import com.dustdev.specterpunish.enums.PunishType;
import com.dustdev.specterpunish.event.PunishPlayerEvent;
import com.dustdev.specterpunish.inventory.PunishInventory;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PunishCommand {

    @Command(
            name = "punir",
            usage = "/punir <jogador>",
            permission = "punir.usar",
            async = true
    ) public void punish(Context<CommandSender> sender, String targetn, @Optional String motivo, @Optional String prova) {

        OfflinePlayer target = Bukkit.getOfflinePlayer(targetn);

        if(target == null) {
            sender.sendMessage(MensagensValue.get(MensagensValue::notarget));
            return;
        }

        if(motivo == null) {

            sendMotivos(sender.getSender(), target);

            return;
        } else {

            GeneralValue.get(GeneralValue::punicoes).stream().filter(key -> key.split(":")[0].equalsIgnoreCase(motivo)).forEach(key -> {

                String[] args = key.split(":");
                PunishType punishType = PunishType.valueOf(args[3]);

                if (!sender.getSender().hasPermission(args[1])) {
                    sender.sendMessage(MensagensValue.get(MensagensValue::semperm));
                    return;
                }

                sender.sendMessage(MensagensValue.get(MensagensValue::punido).replace("{player}", target.getName()));
                PunishPlayerEvent punishPlayerEvent = new PunishPlayerEvent(target.getName(), motivo, sender.getSender().getName(), prova == null ? "Nenhuma" : prova, args[4], punishType, System.currentTimeMillis());
                Bukkit.getPluginManager().callEvent(punishPlayerEvent);

            });
        }
    }

    public void sendMotivos(CommandSender sender, OfflinePlayer target) {
        sender.sendMessage("");
        sender.sendMessage("§ePunições disponíveis:");
        sender.sendMessage("");
        GeneralValue.get(GeneralValue::punicoes).forEach(key -> {
            String[] args = key.split(":");

            TextComponent text = new TextComponent("  §f" + args[0].replace("_", " "));

            text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Clique para punir o jogador por este motivo.").create()));
            text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/punir " + target.getName() + " " + args[0] + " https://prova.com"));

            if(sender instanceof Player) {
                ((Player) sender).spigot().sendMessage(text);
            } else {
                sender.sendMessage(text.getText());
            }
        });
    }

    @Command(
            name = "unpunir",
            usage = "unpunir <jogador>",
            permission = "unpunir.usar",
            async = true
    ) public void unpunir(Context<CommandSender> sender, String targetn) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(targetn);
        if (player == null) {
            sender.sendMessage(MensagensValue.get(MensagensValue::notarget));
            return;
        }
        if (!Main.instance.punishStorage.isPunished(player.getName())) {
            sender.sendMessage(MensagensValue.get(MensagensValue::naopunido));
            return;
        }
        Main.instance.punishStorage.removePunished(player.getName());
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendMessage(MensagensValue.get(MensagensValue::despunido).replace("{player}", player.getName()));
        });
    }

    @Command(
            name = "punicoes",
            permission = "punicoes.usar",
            async = true
    ) public void punicoes(Context<Player> sender) {
        Player p = sender.getSender();
        new PunishInventory().open(p);
    }


}
