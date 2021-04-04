package com.dustdev.specterpunish.model;

import com.dustdev.specterpunish.enums.PunishType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PunishModel {

    private int id;
    private String player,motivo,autor,prova,tempo;
    private long data;
    private PunishType punishType;

}
