package com.dustdev.specterpunish.storage;

import com.dustdev.specterpunish.configuration.values.MensagensValue;
import com.dustdev.specterpunish.dao.PunishDAO;
import com.dustdev.specterpunish.event.PunishPlayerEvent;
import com.dustdev.specterpunish.model.PunishModel;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
public class PunishStorage {

    private final Map<String, PunishModel> punishs = new LinkedHashMap<>();

    private final PunishDAO punishDAO;

    public void init() {
        punishDAO.createTable();
        punishDAO.selectAll().forEach(key -> punishs.put(key.getPlayer(), key));
    }

    public boolean isPunished(String name) {
        return punishs.containsKey(name);
    }

    public PunishModel getPunished(String name) {

        PunishModel punishModel =  punishs.getOrDefault(name, null);

        if (punishModel == null) {

            punishModel = punishDAO.selectOne(name);

            punishs.put(name, punishModel);

        }

        return punishModel;

    }

    public void punishPlayer(PunishModel punishBuilder, PunishPlayerEvent e) {
        punishDAO.insertOne(punishBuilder);

        PunishModel punishModel = getPunished(e.getPlayer());
        Player p = Bukkit.getPlayerExact(e.getPlayer());

        switch (e.getPunishType()) {
            case MUTE:

                if (p != null) {

                    MensagensValue.get(MensagensValue::mutado).forEach(msg -> {
                        p.sendMessage(msg
                                .replace("{id}", "" + punishModel.getId())
                                .replace("{data}", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(punishModel.getData()))
                                .replace("{autor}", punishModel.getAutor())
                                .replace("{motivo}", punishModel.getMotivo().replace("_", " "))
                                .replace("{tempo}", punishModel.getTempo().equalsIgnoreCase("*") ? "§cPermanente" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Long.parseLong(punishModel.getTempo())))
                        );
                    });

                }

                Bukkit.getOnlinePlayers().forEach(players -> {
                    MensagensValue.get(MensagensValue::mutou).forEach(msg -> {
                        players.sendMessage(msg
                                .replace("{player}", punishModel.getPlayer())
                                .replace("{autor}", punishModel.getAutor())
                                .replace("{motivo}", punishModel.getMotivo().replace("_", " "))
                                .replace("{tempo}", punishModel.getTempo().equalsIgnoreCase("*") ? "§cPermanente" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Long.parseLong(punishModel.getTempo())))
                        );
                    });
                });
                break;
            case BAN:

                if (p != null) {
                    String msg = String.join("\n", MensagensValue.get(MensagensValue::banido));
                    p.kickPlayer(msg
                            .replace("{id}", "" + punishModel.getId())
                            .replace("{data}", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(punishModel.getData()))
                            .replace("{autor}", punishModel.getAutor())
                            .replace("{motivo}", punishModel.getMotivo().replace("_", " "))
                            .replace("{tempo}", punishModel.getTempo().equalsIgnoreCase("*") ? "§cPermanente" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Long.parseLong(punishModel.getTempo())))
                    );

                }

                Bukkit.getOnlinePlayers().forEach(players -> {
                    MensagensValue.get(MensagensValue::baniu).forEach(msg -> {
                        players.sendMessage(msg
                                .replace("{player}", punishModel.getPlayer())
                                .replace("{autor}", punishModel.getAutor())
                                .replace("{motivo}", punishModel.getMotivo().replace("_", " "))
                                .replace("{tempo}", punishModel.getTempo().equalsIgnoreCase("*") ? "§cPermanente" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Long.parseLong(punishModel.getTempo())))
                        );
                    });
                });
                break;
        }
    }

    public void removePunished(String name) {
        punishDAO.deleteOne(name);
        punishs.remove(name);
    }
}
