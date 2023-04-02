package dev.felnull.itts.savedata.db.entry;

public class ServerDictDataEntry {
    private final long serverId;
    private final String targetWord;
    private String readWord;

    public ServerDictDataEntry(long serverId, String targetWord, String readWord) {
        this.serverId = serverId;
        this.targetWord = targetWord;
        this.readWord = readWord;
    }

    public long getServerId() {
        return serverId;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public String getReadWord() {
        return readWord;
    }

    public void setReadWord(String readWord) {
        this.readWord = readWord;
    }
}
