package com.dustdev.bungee.specterpunish;

import com.dustdev.bungee.specterpunish.configuration.registry.ConfigurationRegistry;
import com.dustdev.bungee.specterpunish.dao.PunishDAO;
import com.dustdev.bungee.specterpunish.listener.PreLoginListener;
import com.dustdev.bungee.specterpunish.sql.SQLProvider;
import com.dustdev.bungee.specterpunish.storage.PunishStorage;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import me.bristermitten.pdm.DependencyManager;
import me.bristermitten.pdm.PluginDependencyManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

public final class Main extends Plugin {

    public static Main instance;
    public PunishStorage punishStorage;
    public PunishDAO punishDAO;
    public SQLConnector sqlConnector;
    public SQLExecutor sqlExecutor;

    @Override
    public void onEnable() {
        instance = this;

        ConfigurationRegistry.of(this).register();

        sqlConnector = SQLProvider.of(instance).setup();
        sqlExecutor = new SQLExecutor(sqlConnector);

        punishDAO = new PunishDAO(sqlExecutor);
        punishStorage = new PunishStorage(punishDAO);
        punishStorage.init();

        getProxy().getPluginManager().registerListener(this, new PreLoginListener());

        BungeeCord.getInstance().getConsole().sendMessage("§b[SpecterPunish]§f plugin iniciado com sucesso!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
