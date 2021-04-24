package com.dustdev.bungee.specterpunish.dao;

import com.dustdev.bungee.specterpunish.dao.adapter.PunishAdapter;
import com.dustdev.bungee.specterpunish.model.PunishModel;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class PunishDAO {

    private final String table = "SpecterPunish";

    private final SQLExecutor sqlExecutor;

    public void createTable() {
        sqlExecutor.updateQuery("CREATE TABLE IF NOT EXISTS " + table + "(" +
                "id INT AUTO_INCREMENT PRIMARY key," +
                "player VARCHAR(16)," +
                "motivo TEXT," +
                "autor VARCHAR(16)," +
                "prova TEXT," +
                "data LONG," +
                "tempo TEXT," +
                "type TEXT" +
                ");"
        );
    }

    public PunishModel selectOne(String player) {
        return sqlExecutor.resultOneQuery(
                "SELECT * FROM " + table + " WHERE player = ?",
                statement -> statement.set(1, player),
                PunishAdapter.class
        );
    }

    public Set<PunishModel> selectAll() {
        return sqlExecutor.resultManyQuery(
                "SELECT * FROM " + table + "",
                statement -> {},
                PunishAdapter.class
        );
    }

    public void insertOne(PunishModel punishModel) {
        sqlExecutor.updateQuery(
                "INSERT INTO " + table + " VALUES(?,?,?,?,?,?,?,?);",
                statement -> {
                    statement.set(1, null);
                    statement.set(2, punishModel.getPlayer());
                    statement.set(3, punishModel.getMotivo());
                    statement.set(4, punishModel.getAutor());
                    statement.set(5, punishModel.getProva());
                    statement.set(6, punishModel.getData());
                    statement.set(7, punishModel.getTempo());
                    statement.set(8, punishModel.getPunishType()+"");
                }
        );
    }

    public void deleteOne(String name) {
        sqlExecutor.updateQuery(
                "DELETE FROM `SpecterPunish` WHERE `SpecterPunish`.`player` = ?",
                statement -> {
                    statement.set(1, name);
                }
        );
    }

}

