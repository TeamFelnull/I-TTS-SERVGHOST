package dev.felnull.itts.savedata.db;

public class BotStateDataEntry {
    private long serverId;
    private long botId;
    private long connectedAudioChannel;
    private long readAroundTextChannel;

    public BotStateDataEntry(long serverId, long botId, long connectedAudioChannel, long readAroundTextChannel) {
        this.serverId = serverId;
        this.botId = botId;
        this.connectedAudioChannel = connectedAudioChannel;
        this.readAroundTextChannel = readAroundTextChannel;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public long getBotId() {
        return botId;
    }

    public void setBotId(long botId) {
        this.botId = botId;
    }

    public long getConnectedAudioChannel() {
        return connectedAudioChannel;
    }

    public void setConnectedAudioChannel(long connectedAudioChannel) {
        this.connectedAudioChannel = connectedAudioChannel;
    }

    public long getReadAroundTextChannel() {
        return readAroundTextChannel;
    }

    public void setReadAroundTextChannel(long readAroundTextChannel) {
        this.readAroundTextChannel = readAroundTextChannel;
    }
}
