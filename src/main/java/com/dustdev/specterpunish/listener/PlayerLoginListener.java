package com.dustdev.specterpunish.listener;

import com.dustdev.specterpunish.Main;
import com.dustdev.specterpunish.configuration.values.MensagensValue;
import com.dustdev.specterpunish.enums.PunishType;
import com.dustdev.specterpunish.model.PunishModel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void login(PlayerLoginEvent e) {
        if (Main.instance.punishStorage.isPunished(e.getPlayer().getName())) {
            PunishModel punishModel = Main.instance.punishStorage.getPunished(e.getPlayer().getName());
            if (punishModel.getPunishType() == PunishType.BAN) {
                if (!punishModel.getTempo().equalsIgnoreCase("*") && passou(Long.parseLong(punishModel.getTempo()))) {
                    Main.instance.punishStorage.removePunished(e.getPlayer().getName());
                } else {
                    String msg = String.join("\n", MensagensValue.get(MensagensValue::banido));
                    e.setKickMessage(msg
                            .replace("{id}", "" + punishModel.getId())
                            .replace("{data}", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(punishModel.getData()))
                            .replace("{autor}", punishModel.getAutor())
                            .replace("{motivo}", punishModel.getMotivo().replace("_", " "))
                            .replace("{tempo}", punishModel.getTempo().equalsIgnoreCase("*") ? "§cPermanente" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Long.parseLong(punishModel.getTempo())))
                    );
                    e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
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
