package com.dustdev.bot.specterpunish;

import com.dustdev.bot.specterpunish.listener.PunishPlayerListenerBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class Main extends JavaPlugin {

    public static JDA jda;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new PunishPlayerListenerBot(), this);
        try {
            jda = new JDABuilder().createDefault(this.getConfig().getString("Config.bot-token")).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
