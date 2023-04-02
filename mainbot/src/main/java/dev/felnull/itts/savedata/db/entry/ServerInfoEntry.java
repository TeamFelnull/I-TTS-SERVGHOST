package dev.felnull.itts.savedata.db.entry;

public class ServerInfoEntry {
    private final long serverId;
    private String serverName;

    public ServerInfoEntry(long serverId, String serverName) {
        this.serverId = serverId;
        this.serverName = serverName;
    }

    public long getServerId() {
        return serverId;
    }


    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
