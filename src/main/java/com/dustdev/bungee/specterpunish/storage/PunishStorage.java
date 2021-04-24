package com.dustdev.bungee.specterpunish.storage;

import com.dustdev.bungee.specterpunish.dao.PunishDAO;
import com.dustdev.bungee.specterpunish.model.PunishModel;
import lombok.RequiredArgsConstructor;

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
        punishs.clear();
        PunishModel punishModel =  punishs.getOrDefault(name, null);
        if (punishModel == null) {

            punishModel = punishDAO.selectOne(name);
            if (punishModel != null) {
                punishs.put(name, punishModel);
            }

        }
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

    public void removePunished(String name) {
        punishDAO.deleteOne(name);
        punishs.remove(name);
    }
}
