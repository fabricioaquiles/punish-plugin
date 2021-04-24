package com.dustdev.specterpunish.listener;

import com.dustdev.specterpunish.SpecterPunish;
import com.dustdev.specterpunish.configuration.values.MensagensValue;
import com.dustdev.specterpunish.event.UnPunishPlayerEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UnPunishPlayerListener implements Listener {

    @EventHandler
    public void unpunir(UnPunishPlayerEvent e) {
        SpecterPunish.instance.punishStorage.removePunished(e.getPlayer());
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendMessage(MensagensValue.get(MensagensValue::despunido).replace("{player}", e.getPlayer()).replace("{autor}", e.getAutor()));
        });
    }
}
