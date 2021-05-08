package com.dustdev.specterpunish.listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import com.dustdev.specterpunish.Main;
import com.dustdev.specterpunish.configuration.values.MensagensValue;
import com.dustdev.specterpunish.enums.PunishType;
import com.dustdev.specterpunish.model.PunishModel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LegendchatListener implements Listener {

    @EventHandler
    public void lc(ChatMessageEvent e) {
        if (Main.instance.punishStorage.isPunished(e.getSender().getName())) {
            PunishModel punishModel = Main.instance.punishStorage.getPunished(e.getSender().getName());
            if (punishModel.getPunishType() == PunishType.MUTE) {
                if (!punishModel.getTempo().equalsIgnoreCase("*") && passou(Long.parseLong(punishModel.getTempo()))) {
                    Main.instance.punishStorage.removePunished(e.getSender().getName());
                } else {
                    e.setCancelled(true);
                    MensagensValue.get(MensagensValue::mutado).forEach(msg -> {
                        e.getSender().sendMessage(msg
                                .replace("{id}", "" + punishModel.getId())
                                .replace("{data}", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(punishModel.getData()))
                                .replace("{autor}", punishModel.getAutor())
                                .replace("{motivo}", punishModel.getMotivo().replace("_", " "))
                                .replace("{tempo}", punishModel.getTempo().equalsIgnoreCase("*") ? "§cPermanente" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Long.parseLong(punishModel.getTempo())))
                        );
                    });
                }
            }
        }
    }

    public static boolean passou(long tempo) {
        Date vencimento = new Date(tempo);
        Date atual = new Date(System.currentTimeMillis());
        return atual.after(vencimento);
    }
}
