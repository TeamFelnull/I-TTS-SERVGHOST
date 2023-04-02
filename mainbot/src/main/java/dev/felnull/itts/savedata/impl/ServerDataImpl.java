package dev.felnull.itts.savedata.impl;

import dev.felnull.itts.core.savedata.ServerData;
import dev.felnull.itts.savedata.DBSaveDataBase;
import dev.felnull.itts.savedata.db.entry.ServerDataEntry;
import dev.felnull.itts.savedata.db.SaveDataDAO;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ServerDataImpl extends DBSaveDataBase<ServerDataEntry> implements ServerData {
    private final long serverId;

    public ServerDataImpl(long serverId, SaveDataDAO dao) {
        super(dao, new ServerDataEntry(serverId, INIT_DEFAULT_VOICE_TYPE, INIT_IGNORE_REGEX, INIT_NEED_JOIN, INIT_OVERWRITE_ALOUD, INIT_NOTIFY_MOVE, INIT_READ_LIMIT, INIT_NAME_READ_LIMIT));
        this.serverId = serverId;
    }

    @Override
    protected Optional<ServerDataEntry> selectData(Connection connection) throws SQLException {
        return dao.selectServerData(connection, serverId);
    }

    @Override
    protected void insertData(Connection connection, ServerDataEntry data) throws SQLException {
        dao.insertServerData(connection, data);
    }

    @Override
    protected void updateData(Connection connection, ServerDataEntry data) throws SQLException {
        dao.updateServerData(connection, data);
    }

    @Override
    public @Nullable String getDefaultVoiceType() {
        return currentData().getDefaultVoiceType();
    }

    @Override
    public void setDefaultVoiceType(@Nullable String s) {
        insertOrUpdateData(data -> {
            data.setDefaultVoiceType(s);
            return data;
        });
    }

    @Override
    public @Nullable String getIgnoreRegex() {
        return currentData().getIgnoreRegex();
    }

    @Override
    public void setIgnoreRegex(@Nullable String s) {
        insertOrUpdateData(data -> {
            data.setIgnoreRegex(s);
            return data;
        });
    }

    @Override
    public boolean isNeedJoin() {
        return currentData().isNeedJoin();
    }

    @Override
    public void setNeedJoin(boolean b) {
        insertOrUpdateData(data -> {
            data.setNeedJoin(b);
            return data;
        });
    }

    @Override
    public boolean isOverwriteAloud() {
        return currentData().isOverwriteAloud();
    }

    @Override
    public void setOverwriteAloud(boolean b) {
        insertOrUpdateData(data -> {
            data.setOverwriteAloud(b);
            return data;
        });
    }

    @Override
    public boolean isNotifyMove() {
        return currentData().isNotifyMove();
    }

    @Override
    public void setNotifyMove(boolean b) {
        insertOrUpdateData(data -> {
            data.setNotifyMove(b);
            return data;
        });
    }

    @Override
    public int getReadLimit() {
        return currentData().getReadLimit();
    }

    @Override
    public void setReadLimit(int i) {
        insertOrUpdateData(data -> {
            data.setReadLimit(i);
            return data;
        });
    }

    @Override
    public int getNameReadLimit() {
        return currentData().getNameReadLimit();
    }

    @Override
    public void setNameReadLimit(int i) {
        insertOrUpdateData(data -> {
            data.setNameReadLimit(i);
            return data;
        });
    }
}
