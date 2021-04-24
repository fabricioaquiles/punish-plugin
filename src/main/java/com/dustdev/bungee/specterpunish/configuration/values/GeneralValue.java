package com.dustdev.bungee.specterpunish.configuration.values;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.md_5.bungee.config.Configuration;

import java.util.List;
import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("config.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneralValue implements ConfigurationInjectable {

    @Getter private static final GeneralValue instance = new GeneralValue();

    @ConfigField("MySQL.host") private String host;
    @ConfigField("MySQL.user") private String user;
    @ConfigField("MySQL.password") private String password;
    @ConfigField("MySQL.database") private String database;

    public static <T> T get(Function<GeneralValue, T> function) {
        return function.apply(instance);
    }

}
