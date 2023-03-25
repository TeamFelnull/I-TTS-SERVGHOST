package dev.felnull.itts.config;

import com.google.gson.JsonObject;
import dev.felnull.itts.core.config.voicetype.VoiceTextConfig;
import dev.felnull.itts.core.util.JsonUtils;
import org.jetbrains.annotations.NotNull;

public class VoiceTextConfigImpl extends VoiceTypeConfigImpl implements VoiceTextConfig {
    private final String apiKey;

    protected VoiceTextConfigImpl(JsonObject jo) {
        super(jo);
        this.apiKey = JsonUtils.getString(jo, "api_key", DEFAULT_API_KEY);
    }

    @Override
    protected JsonObject toJson() {
        var jo = super.toJson();
        jo.addProperty("api_key", apiKey);
        return jo;
    }

    @Override
    public @NotNull String getApiKey() {
        return apiKey;
    }
}
