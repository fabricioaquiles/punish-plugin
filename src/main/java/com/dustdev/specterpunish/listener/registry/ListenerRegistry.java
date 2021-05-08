package com.dustdev.specterpunish.listener.registry;

import com.dustdev.specterpunish.Main;
import com.dustdev.specterpunish.listener.*;
import lombok.Data;
import org.bukkit.Bukkit;

@Data(staticConstructor = "of")
public class ListenerRegistry {

    private final Main instance;

    public void register() {
        try {

            Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), instance);
            Bukkit.getPluginManager().registerEvents(new LegendchatListener(), instance);
            Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), instance);
            Bukkit.getPluginManager().registerEvents(new PunishPlayerListener(), instance);
            Bukkit.getPluginManager().registerEvents(new UnPunishPlayerListener(), instance);

            Bukkit.getConsoleSender().sendMessage("§b[SpecterPunish] [Eventos]§f eventos carregados com sucesso.");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§b[SpecterPunish] [Eventos]§f não foi possível fazer o carregamento dos eventos.");
        }
    }

}
