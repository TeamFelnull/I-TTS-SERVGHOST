package dev.felnull.itts.savedata.db;

public class DictUseDataEntry {
    private long serverId;
    private String dictId;
    private int priority;

    public DictUseDataEntry(long serverId, String dictId, int priority) {
        this.serverId = serverId;
        this.dictId = dictId;
        this.priority = priority;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
