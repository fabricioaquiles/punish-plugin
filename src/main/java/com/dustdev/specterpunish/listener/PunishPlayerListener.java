package com.dustdev.specterpunish.listener;

import com.dustdev.specterpunish.Main;
import com.dustdev.specterpunish.event.PunishPlayerEvent;
import com.dustdev.specterpunish.model.PunishModel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Date;

public class PunishPlayerListener implements Listener {

    @EventHandler
    public void punish(PunishPlayerEvent e) {
        Date atual = new Date(System.currentTimeMillis());

        if(Main.instance.punishStorage.isPunished(e.getPlayer())) {
            Main.instance.punishStorage.removePunished(e.getPlayer());
        }

        if(!e.getTempo().equalsIgnoreCase("*")) {
            atual.setMinutes(atual.getMinutes() + Integer.parseInt(e.getTempo()));
        }

        PunishModel punishBuilder = PunishModel.builder()
                .player(e.getPlayer())
                .motivo(e.getMotivo())
                .autor(e.getAutor())
                .prova(e.getProva())
                .data(e.getData())
                .tempo(e.getTempo().equalsIgnoreCase("*") ? "*" : atual.getTime()+"")
                .punishType(e.getPunishType())
                .build();

        Main.instance.punishStorage.punishPlayer(punishBuilder, e);
    }
}
