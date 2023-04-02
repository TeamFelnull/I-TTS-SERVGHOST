package dev.felnull.itts.savedata.db.entry;

public class BotStateDataEntry {
    private final long serverId;
    private final long botId;
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

    public long getBotId() {
        return botId;
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
