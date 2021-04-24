package com.dustdev.specterpunish.command.registry;

import com.dustdev.specterpunish.SpecterPunish;
import com.dustdev.specterpunish.command.PunishCommand;
import com.dustdev.specterpunish.configuration.values.MensagensValue;
import lombok.Data;
import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.Bukkit;

@Getter
@Data(staticConstructor = "of")
public class CommandRegistry {

    private final SpecterPunish plugin;

    public void register() {
        try {
            BukkitFrame bukkitFrame = new BukkitFrame(plugin);
            bukkitFrame.registerCommands(new PunishCommand()
            );

            MessageHolder messageHolder = bukkitFrame.getMessageHolder();
            messageHolder.setMessage(MessageType.NO_PERMISSION, MensagensValue.get(MensagensValue::semperm).replace("%grupo%", "§2Moderador"));

            Bukkit.getConsoleSender().sendMessage("§b[SpecterPunish] [Comandos] §fcomandos carregados com sucesso.");
        }catch (Throwable t) {
            t.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§b[SpecterPunish] [Comandos] §fnão foi possível fazer o carregamento dos comandos.");
        }
    }
}
