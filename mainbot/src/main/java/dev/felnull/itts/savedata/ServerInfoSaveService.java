package dev.felnull.itts.savedata;

import dev.felnull.itts.savedata.db.SaveDataDAO;
import dev.felnull.itts.savedata.db.entry.ServerInfoEntry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ServerInfoSaveService extends InfoSaveService<ServerInfoEntry> {
    public ServerInfoSaveService(SaveDataDAO dao) {
        super(dao, initedInfo);
    }

    @Override
    protected Optional<ServerInfoEntry> selectInfo(Connection connection, long id) throws SQLException {
        return dao.selectServerInfo(connection, id);
    }

    @Override
    protected void insertInfo(Connection connection, ServerInfoEntry info) throws SQLException {
        dao.insertServerInfo(connection, info);
    }
}
