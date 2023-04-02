package dev.felnull.itts.savedata.db.entry;

public class ServerDataEntry {
    private final long serverId;
    private String defaultVoiceType;
    private String ignoreRegex;
    private boolean needJoin;
    private boolean overwriteAloud;
    private boolean notifyMove;
    private int readLimit;
    private int nameReadLimit;

    public ServerDataEntry(long serverId, String defaultVoiceType, String ignoreRegex, boolean needJoin, boolean overwriteAloud, boolean notifyMove, int readLimit, int nameReadLimit) {
        this.serverId = serverId;
        this.defaultVoiceType = defaultVoiceType;
        this.ignoreRegex = ignoreRegex;
        this.needJoin = needJoin;
        this.overwriteAloud = overwriteAloud;
        this.notifyMove = notifyMove;
        this.readLimit = readLimit;
        this.nameReadLimit = nameReadLimit;
    }

    public long getServerId() {
        return serverId;
    }

    public String getDefaultVoiceType() {
        return defaultVoiceType;
    }

    public void setDefaultVoiceType(String defaultVoiceType) {
        this.defaultVoiceType = defaultVoiceType;
    }

    public String getIgnoreRegex() {
        return ignoreRegex;
    }

    public void setIgnoreRegex(String ignoreRegex) {
        this.ignoreRegex = ignoreRegex;
    }

    public boolean isNeedJoin() {
        return needJoin;
    }

    public void setNeedJoin(boolean needJoin) {
        this.needJoin = needJoin;
    }

    public boolean isOverwriteAloud() {
        return overwriteAloud;
    }

    public void setOverwriteAloud(boolean overwriteAloud) {
        this.overwriteAloud = overwriteAloud;
    }

    public boolean isNotifyMove() {
        return notifyMove;
    }

    public void setNotifyMove(boolean notifyMove) {
        this.notifyMove = notifyMove;
    }

    public int getReadLimit() {
        return readLimit;
    }

    public void setReadLimit(int readLimit) {
        this.readLimit = readLimit;
    }

    public int getNameReadLimit() {
        return nameReadLimit;
    }

    public void setNameReadLimit(int nameReadLimit) {
        this.nameReadLimit = nameReadLimit;
    }
}
