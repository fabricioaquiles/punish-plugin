package com.dustdev.specterpunish.configuration.values;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("mensagens.yml")
@ConfigSection("Mensagens")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MensagensValue implements ConfigurationInjectable {

    @Getter
    private static final MensagensValue instance = new MensagensValue();

    @ConfigField("semperm") private String semperm;
    @ConfigField("jogadorinexistente") private String notarget;
    @ConfigField("jogadorpunido") private String punido;
    @ConfigField("jogadornaopunido") private String naopunido;
    @ConfigField("jogadordespunido") private String despunido;
    @ConfigField("banido") private List<String> banido;
    @ConfigField("baniu") private List<String> baniu;
    @ConfigField("mutado") private List<String> mutado;
    @ConfigField("mutou") private List<String> mutou;
    @ConfigField("kickado") private List<String> kickado;
    @ConfigField("kickou") private List<String> kickou;

    public static <T> T get(Function<MensagensValue, T> function) {
        return function.apply(instance);
    }
}
