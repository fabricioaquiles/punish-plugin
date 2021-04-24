package com.dustdev.specterpunish.configuration.registry;

import com.dustdev.specterpunish.SpecterPunish;
import com.dustdev.specterpunish.configuration.values.GeneralValue;
import com.dustdev.specterpunish.configuration.values.MensagensValue;
import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import lombok.Data;

@Data(staticConstructor = "of")
public class ConfigurationRegistry {

    private final SpecterPunish plugin;

    public void register() {
        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(plugin,
                "mensagens.yml"
        );
        configurationInjector.injectConfiguration(
                GeneralValue.instance(),
                MensagensValue.instance()
        );

    }

}
