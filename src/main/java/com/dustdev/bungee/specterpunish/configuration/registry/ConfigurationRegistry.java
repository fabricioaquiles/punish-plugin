package com.dustdev.bungee.specterpunish.configuration.registry;

import com.dustdev.bungee.specterpunish.Main;
import com.dustdev.bungee.specterpunish.configuration.values.GeneralValue;
import com.dustdev.bungee.specterpunish.configuration.values.MensagensValue;
import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import com.henryfabio.minecraft.configinjector.bungee.configuration.BungeeConfiguration;
import com.henryfabio.minecraft.configinjector.bungee.injector.BungeeConfigurationInjector;
import lombok.Data;

@Data(staticConstructor = "of")
public class ConfigurationRegistry {

    private final Main plugin;

    public void register() {
        BungeeConfigurationInjector configurationInjector = new BungeeConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(plugin,
                "mensagens.yml", "config.yml"
        );
        configurationInjector.injectConfiguration(
                MensagensValue.instance(),
                GeneralValue.instance()
        );

    }

}
