package dev.felnull.itts.savedata.db;

public class ServerDictDataEntry {
    private long serverId;
    private int dictWordId;
    private String targetWord;
    private String readWord;

    public ServerDictDataEntry(long serverId, int dictWordId, String targetWord, String readWord) {
        this.serverId = serverId;
        this.dictWordId = dictWordId;
        this.targetWord = targetWord;
        this.readWord = readWord;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public int getDictWordId() {
        return dictWordId;
    }

    public void setDictWordId(int dictWordId) {
        this.dictWordId = dictWordId;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }

    public String getReadWord() {
        return readWord;
    }

    public void setReadWord(String readWord) {
        this.readWord = readWord;
    }
}
