package com.dustdev.specterpunish;

import com.dustdev.specterpunish.command.registry.CommandRegistry;
import com.dustdev.specterpunish.configuration.registry.ConfigurationRegistry;
import com.dustdev.specterpunish.dao.PunishDAO;
import com.dustdev.specterpunish.listener.registry.ListenerRegistry;
import com.dustdev.specterpunish.sql.SQLProvider;
import com.dustdev.specterpunish.storage.PunishStorage;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpecterPunish extends JavaPlugin {

    public static SpecterPunish instance;
    public PunishStorage punishStorage;
    public PunishDAO punishDAO;
    public SQLConnector sqlConnector;
    public SQLExecutor sqlExecutor;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            try {
                instance = this;

                ConfigurationRegistry.of(this).register();
                ListenerRegistry.of(this).register();
                CommandRegistry.of(this).register();

                sqlConnector = SQLProvider.of(instance).setup();
                sqlExecutor = new SQLExecutor(sqlConnector);

                punishDAO = new PunishDAO(sqlExecutor);
                punishStorage = new PunishStorage(punishDAO);
                punishStorage.init();

                Bukkit.getConsoleSender().sendMessage("§b[SpecterPunish]§f plugin iniciado com sucesso!");

            } catch (Throwable t) {
                t.printStackTrace();
                Bukkit.getConsoleSender().sendMessage("§b[SpecterPunish]§f não foi possível iniciar o plugin!");
                Bukkit.getPluginManager().disablePlugin(this);
            }

        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
