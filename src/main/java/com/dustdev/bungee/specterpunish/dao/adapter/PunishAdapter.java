package com.dustdev.bungee.specterpunish.dao.adapter;

import com.dustdev.bungee.specterpunish.enums.PunishType;
import com.dustdev.bungee.specterpunish.model.PunishModel;
import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapter;
import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;

public class PunishAdapter implements SQLResultAdapter<PunishModel> {

    @Override
    public PunishModel adaptResult(SimpleResultSet resultSet) {

        int id = resultSet.get("id");
        String player = resultSet.get("player");
        String motivo = resultSet.get("motivo");
        String autor = resultSet.get("autor");
        String prova = resultSet.get("prova");
        long data = Long.parseLong(resultSet.get("data"));
        String tempo = resultSet.get("tempo");
        String type = resultSet.get("type");

        return PunishModel
                .builder()
                .id(id)
                .player(player)
                .motivo(motivo)
                .autor(autor)
                .prova(prova)
                .data(data)
                .tempo(tempo)
                .punishType(PunishType.valueOf(type.toUpperCase()))
                .build();
    }
}
