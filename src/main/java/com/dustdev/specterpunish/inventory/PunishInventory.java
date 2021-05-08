package com.dustdev.specterpunish.inventory;

import com.dustdev.specterpunish.Main;
import com.dustdev.specterpunish.utils.Scroller;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PunishInventory {

    public void open(Player p) {
        List<ItemStack> itens = new ArrayList<>();
        Main.instance.punishDAO.selectAll().forEach(key -> {

            ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(short)3);
            SkullMeta meta = (SkullMeta) item.getItemMeta();

            meta.setOwner(key.getPlayer());
            meta.setDisplayName("§7"+key.getPlayer());

            String tempo = key.getTempo().equalsIgnoreCase("*") ? "Permanente" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Long.parseLong(key.getTempo()));
            String tipo = key.getPunishType().toString().equalsIgnoreCase("BAN") ? "Banido" : "Mutado";

            List<String> lore = Arrays.asList(
                    "§7ID: §f#"+key.getId(),
                    "§7Data da punição: §f"+new SimpleDateFormat("dd-MM-yyyy HH:mm").format(key.getData()),
                    "§7Autor: §f"+key.getAutor(),
                    "§7Tempo: §f"+tempo,
                    "§7Motivo da Punição: §f"+key.getMotivo(),
                    "§7Evidência: §f"+key.getProva(),
                    "§7Tipo de punição: §f"+tipo
            );

            meta.setLore(lore);
            item.setItemMeta(meta);

            itens.add(item);

        });

        if(itens.size() == 0 ) {
            p.sendMessage("§cNo momento não há nenhuma punição ativa.");
            return;
        }

        Scroller scroller = new Scroller.ScrollerBuilder()
                .withSize(54)
                .withName("Punições ativas")
                .withItems(itens)
                .withItemsSlots(10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43)
                .withArrowsSlots(45, 53)
                .withOnClick(new Scroller.ChooseItemRunnable() {
                    @Override
                    public void run(Player player, ItemStack item) {
                        Main.instance.punishDAO.selectAll().forEach(key -> {
                            if (item.getItemMeta().getDisplayName().replace("§7", "").equalsIgnoreCase(key.getPlayer())) {
                                new DetalhesInventory().open(p, key.getPlayer());
                            }
                        });
                    }
                })
                .build();

        scroller.open(p);
    }
}
