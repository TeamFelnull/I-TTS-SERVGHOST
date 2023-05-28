package dev.felnull.itts.servghost.control.config;

import com.google.gson.JsonObject;

/**
 * クライアントのコンフィグを上書きするコンフィグ
 */
public class OverwriteConfig {
    /**
     * 上書きするJson
     */
    private final JsonObject overwriteJson;

    /**
     * コンストラクタ
     *
     * @param overwriteJson Json
     */
    public OverwriteConfig(JsonObject overwriteJson) {
        this.overwriteJson = overwriteJson;
    }

    public JsonObject getOverwriteJson() {
        return overwriteJson;
    }
}
