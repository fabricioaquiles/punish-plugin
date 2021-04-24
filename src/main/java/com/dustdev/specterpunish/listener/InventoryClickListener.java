package com.dustdev.specterpunish.listener;

import com.dustdev.specterpunish.event.UnPunishPlayerEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void aoClicar(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getName().startsWith("Punição : ")) {
            e.setCancelled(true);
            if(e.getRawSlot() == 13) {
                String punido = e.getInventory().getName().split(":")[1].replaceAll(" ", "");
                UnPunishPlayerEvent unPunishPlayerEvent = new UnPunishPlayerEvent(punido, p.getName());
                Bukkit.getPluginManager().callEvent(unPunishPlayerEvent);
                p.closeInventory();
            }
        }
    }
}
