package dev.felnull.itts.savedata.db.entry;

public class ServerUserDataEntry {
    private final long serverId;
    private final long userId;
    private String voiceType;
    private boolean deny;
    private String nickName;

    public ServerUserDataEntry(long serverId, long userId, String voiceType, boolean deny, String nickName) {
        this.serverId = serverId;
        this.userId = userId;
        this.voiceType = voiceType;
        this.deny = deny;
        this.nickName = nickName;
    }

    public long getServerId() {
        return serverId;
    }

    public long getUserId() {
        return userId;
    }

    public String getVoiceType() {
        return voiceType;
    }

    public void setVoiceType(String voiceType) {
        this.voiceType = voiceType;
    }

    public boolean isDeny() {
        return deny;
    }

    public void setDeny(boolean deny) {
        this.deny = deny;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
