package com.dustdev.specterpunish.event;

import com.dustdev.specterpunish.enums.PunishType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Data
@EqualsAndHashCode(callSuper = true)
public class PunishPlayerEvent extends Event {

    @Getter
    private static final HandlerList handlerList = new HandlerList();

    private final String player, motivo, autor, prova, tempo;
    private final PunishType punishType;
    private final long data;

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}
