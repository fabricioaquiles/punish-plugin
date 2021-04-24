package com.dustdev.bot.specterpunish.listener;

import com.dustdev.bot.specterpunish.Main;
import com.dustdev.specterpunish.event.PunishPlayerEvent;
import com.dustdev.specterpunish.event.UnPunishPlayerEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PunishPlayerListenerBot implements Listener {

    @EventHandler
    public void punish(PunishPlayerEvent e) {
        MessageEmbed messageEmbed = new EmbedBuilder()
                .setTitle(":green_circle: Jogador punido.")
                .addField("Jogador", e.getPlayer(), false)
                .addField("Autor", e.getAutor(), false)
                .addField("Motivo", e.getMotivo(), false)
                .addField("Tipo de punição", e.getPunishType().toString(),false)
                .addField("Data da punição", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(e.getData()), false)
                .addField("Evidência", e.getProva(), false)
                .setColor(Color.green)
                .setTimestamp(new Date(System.currentTimeMillis()).toInstant())
                .build();

        Main.jda.getTextChannelById(Main.getPlugin(Main.class).getConfig().getString("Config.channels-id.punish")).sendMessage(messageEmbed).queue();

    }

    @EventHandler
    public void unpunish(UnPunishPlayerEvent e) {
        MessageEmbed messageEmbed = new EmbedBuilder()
                .setTitle(":no_entry: Jogador despunido.")
                .addField("Jogador", e.getPlayer(), false)
                .addField("Autor", e.getAutor(), false)
                .addField("Data", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()), false)
                .setColor(Color.red)
                .setTimestamp(new Date(System.currentTimeMillis()).toInstant())
                .build();

        Main.jda.getTextChannelById(Main.getPlugin(Main.class).getConfig().getString("Config.channels-id.unpunish")).sendMessage(messageEmbed).queue();

    }
}
