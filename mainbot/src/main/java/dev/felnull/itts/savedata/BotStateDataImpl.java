package dev.felnull.itts.savedata;

import dev.felnull.itts.core.savedata.BotStateData;
import dev.felnull.itts.savedata.db.BotStateDataEntry;
import dev.felnull.itts.savedata.db.SaveDataDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class BotStateDataImpl extends DBEntrySaveData<BotStateDataEntry> implements BotStateData {
    private final long serverId;
    private final long botId;

    public BotStateDataImpl(long serverId, long botId, SaveDataDAO dao) {
        super(dao, new BotStateDataEntry(serverId, botId, INIT_CONNECTED_AUDIO_CHANNEL, INIT_READ_AROUND_TEXT_CHANNEL));
        this.serverId = serverId;
        this.botId = botId;
    }

    @Override
    protected Optional<BotStateDataEntry> selectData(Connection connection) throws SQLException {
        return dao.selectBotStateData(connection, serverId, botId);
    }

    @Override
    protected void insertData(Connection connection, BotStateDataEntry data) throws SQLException {
        dao.insertBotStateData(connection, data);
    }

    @Override
    protected void updateData(Connection connection, BotStateDataEntry data) throws SQLException {
        dao.updateBotStateData(connection, data);
    }

    @Override
    public long getConnectedAudioChannel() {
        return currentData().getConnectedAudioChannel();
    }

    @Override
    public void setConnectedAudioChannel(long l) {
        insertOrUpdateData(data -> {
            data.setConnectedAudioChannel(l);
            return data;
        });
    }

    @Override
    public long getReadAroundTextChannel() {
        return currentData().getReadAroundTextChannel();
    }

    @Override
    public void setReadAroundTextChannel(long l) {
        insertOrUpdateData(data -> {
            data.setReadAroundTextChannel(l);
            return data;
        });
    }

    public long getServerId() {
        return serverId;
    }

    public long getBotId() {
        return botId;
    }
}
