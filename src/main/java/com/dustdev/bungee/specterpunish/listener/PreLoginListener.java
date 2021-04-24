package com.dustdev.bungee.specterpunish.listener;

import com.dustdev.bungee.specterpunish.Main;
import com.dustdev.bungee.specterpunish.configuration.values.MensagensValue;
import com.dustdev.bungee.specterpunish.enums.PunishType;
import com.dustdev.bungee.specterpunish.model.PunishModel;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PreLoginListener implements Listener {

    @EventHandler
    public void prelogin(PreLoginEvent e) {
        if (Main.instance.punishStorage.isPunished(e.getConnection().getName())) {
            PunishModel punishModel = Main.instance.punishStorage.getPunished(e.getConnection().getName());
            if (punishModel.getPunishType() == PunishType.BAN) {
                if (!punishModel.getTempo().equalsIgnoreCase("*") && passou(Long.parseLong(punishModel.getTempo()))) {
                    Main.instance.punishStorage.removePunished(e.getConnection().getName());
                } else {
                    String msg = String.join("\n", MensagensValue.get(MensagensValue::banido));
                    e.setCancelled(true);
                    e.setCancelReason(msg
                            .replace("{id}", "" + punishModel.getId())
                            .replace("{data}", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(punishModel.getData()))
                            .replace("{autor}", punishModel.getAutor())
                            .replace("{motivo}", punishModel.getMotivo().replace("_", " "))
                            .replace("{tempo}", punishModel.getTempo().equalsIgnoreCase("*") ? "§cPermanente" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Long.parseLong(punishModel.getTempo())))
                    );
                }
            }
        }
    }

    public static boolean passou(long tempo) {
        Date vencimento = new Date(tempo);
        Date atual = new Date();
        return atual.after(vencimento);
    }
}
