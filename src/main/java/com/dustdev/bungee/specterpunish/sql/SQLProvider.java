package com.dustdev.bungee.specterpunish.sql;

import com.dustdev.bungee.specterpunish.configuration.values.GeneralValue;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.connector.type.SQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType;
import lombok.Data;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;

@Data(staticConstructor = "of")
public class SQLProvider {

    private final Plugin plugin;

    public SQLConnector setup() {

        SQLConnector sqlConnector;

        sqlConnector = mysqlDatabaseType(GeneralValue.get(GeneralValue::host), GeneralValue.get(GeneralValue::user), GeneralValue.get(GeneralValue::password), GeneralValue.get(GeneralValue::database)).connect();
        BungeeCord.getInstance().getConsole().sendMessage("§b[SpecterPunish] [MySQL]§f conexão com o MySQL efetuada com sucesso, criando tabelas...");

        return sqlConnector;
    }

    private SQLDatabaseType mysqlDatabaseType(String host, String user, String password, String database) {
        return MySQLDatabaseType.builder()
                .address(host)
                .username(user)
                .password(password)
                .database(database)
                .build();
    }
}
