package dev.felnull.itts.config;

import com.google.gson.JsonObject;
import dev.felnull.itts.core.config.voicetype.VoiceTypeConfig;
import dev.felnull.itts.core.util.JsonUtils;

public class VoiceTypeConfigImpl implements VoiceTypeConfig {
    private final boolean enable;

    protected VoiceTypeConfigImpl(JsonObject jo) {
        this.enable = JsonUtils.getBoolean(jo, "enable", DEFAULT_ENABLE);
    }

    @Override
    public boolean isEnable() {
        return enable;
    }

    protected JsonObject toJson() {
        var jo = new JsonObject();
        jo.addProperty("enable", enable);
        return jo;
    }
}
