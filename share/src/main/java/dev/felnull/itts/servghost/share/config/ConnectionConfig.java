package dev.felnull.itts.servghost.share.config;

import com.google.gson.JsonObject;
import dev.felnull.itts.servghost.share.util.JsonUtils;

import java.util.function.Supplier;

/**
 * 制御BOTのコンフィグ
 */
public class ConnectionConfig implements Supplier<JsonObject> {
    /**
     * 通信に割り当てるホストネーム
     */
    private final String hostName;

    /**
     * 通信に割り当てるポート番号
     */
    private final int port;

    /**
     * コンストラクタ
     *
     * @param jsonObject コンフィグのJson
     */
    public ConnectionConfig(JsonObject jsonObject) {
        this.hostName = JsonUtils.getString(jsonObject, "host_name", "localhost");
        this.port = JsonUtils.getInt(jsonObject, "port", 8765);
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    @Override
    public JsonObject get() {
        var jo = new JsonObject();
        jo.addProperty("host_name", this.hostName);
        jo.addProperty("port", this.port);
        return jo;
    }
}
