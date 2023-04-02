package dev.felnull.itts.savedata;

import dev.felnull.itts.savedata.db.SaveDataDAO;
import dev.felnull.itts.savedata.db.entry.BotInfoEntry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class BotInfoSaveService extends InfoSaveService<BotInfoEntry> {
    public BotInfoSaveService(SaveDataDAO dao) {
        super(dao);
    }

    @Override
    protected Optional<BotInfoEntry> selectInfo(Connection connection, long id) throws SQLException {
        return dao.selectBotInfo(connection, id);
    }

    @Override
    protected void insertInfo(Connection connection, BotInfoEntry info) throws SQLException {
        dao.insertBotInfo(connection, info);
    }
}
