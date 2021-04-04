package com.dustdev.specterpunish.inventory;

import com.dustdev.specterpunish.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DetalhesInventory {

    public void open(Player p, String punido) {
        Inventory inv = Bukkit.createInventory(null, 3*9, "Punição : "+punido);

        inv.setItem(13, new ItemBuilder(Material.BARRIER)
                .setName("§cDespunir")
                .setLore(
                        "§7Clique retirar a punição",
                        "§7do jogador "+punido
                ).toItemStack());

        p.openInventory(inv);
    }

}
