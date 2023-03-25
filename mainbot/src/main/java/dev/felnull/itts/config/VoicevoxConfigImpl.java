package dev.felnull.itts.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.felnull.itts.core.config.voicetype.VoicevoxConfig;
import dev.felnull.itts.core.util.JsonUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.List;

public class VoicevoxConfigImpl extends VoiceTypeConfigImpl implements VoicevoxConfig {
    private final List<String> apiUrls;
    private final long checkTime;

    protected VoicevoxConfigImpl(JsonObject jo) {
        super(jo);
        var apiJa = jo.getAsJsonArray("api_url");
        List<String> loadApiUrals = new ArrayList<>();
        if (apiJa != null)
            apiJa.forEach(je -> loadApiUrals.add(je.getAsString()));

        this.apiUrls = loadApiUrals.isEmpty() ? DEFAULT_API_URLS : loadApiUrals;
        this.checkTime = JsonUtils.getLong(jo, "check_time", DEFAULT_CHECK_TIME);
    }

    @Override
    protected JsonObject toJson() {
        var jo = super.toJson();

        var apiJa = new JsonArray();
        this.apiUrls.forEach(apiJa::add);

        jo.add("api_url", apiJa);
        jo.addProperty("check_time", checkTime);
        return jo;
    }

    @Override
    public @NotNull @Unmodifiable List<String> getApiUrls() {
        return apiUrls;
    }

    @Override
    public long getCheckTime() {
        return checkTime;
    }
}
