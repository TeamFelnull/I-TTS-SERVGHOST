package dev.felnull.itts.savedata.db.entry;

public class BotInfoEntry {
    private final long botId;
    private String botName;

    public BotInfoEntry(long botId, String botName) {
        this.botId = botId;
        this.botName = botName;
    }

    public long getBotId() {
        return botId;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }
}
