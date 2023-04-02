package dev.felnull.itts.savedata.impl;

import dev.felnull.itts.core.savedata.ServerUserData;
import dev.felnull.itts.savedata.DBSaveDataBase;
import dev.felnull.itts.savedata.db.entry.ServerUserDataEntry;
import dev.felnull.itts.savedata.db.SaveDataDAO;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ServerUserDataImpl extends DBSaveDataBase<ServerUserDataEntry> implements ServerUserData {
    private final long serverId;
    private final long userId;

    public ServerUserDataImpl(long serverId, long userId, SaveDataDAO dao) {
        super(dao, new ServerUserDataEntry(serverId, userId, INIT_VOICE_TYPE, INIT_DENY, INIT_NICK_NAME));
        this.serverId = serverId;
        this.userId = userId;
    }

    @Override
    protected Optional<ServerUserDataEntry> selectData(Connection connection) throws SQLException {
        return dao.selectServerUserData(connection, serverId, userId);
    }

    @Override
    protected void insertData(Connection connection, ServerUserDataEntry data) throws SQLException {
        dao.insertServerUserData(connection, data);
    }

    @Override
    protected void updateData(Connection connection, ServerUserDataEntry data) throws SQLException {
        dao.updateServerUserData(connection, data);
    }

    @Override
    public @Nullable String getVoiceType() {
        return currentData().getVoiceType();
    }

    @Override
    public void setVoiceType(@Nullable String s) {
        insertOrUpdateData(data -> {
            data.setVoiceType(s);
            return data;
        });
    }

    @Override
    public boolean isDeny() {
        return currentData().isDeny();
    }

    @Override
    public void setDeny(boolean b) {
        insertOrUpdateData(data -> {
            data.setDeny(b);
            return data;
        });
    }

    @Override
    public @Nullable String getNickName() {
        return currentData().getNickName();
    }

    @Override
    public void setNickName(@Nullable String s) {
        insertOrUpdateData(data -> {
            data.setNickName(s);
            return data;
        });
    }
}
