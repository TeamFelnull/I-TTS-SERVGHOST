package dev.felnull.itts.config;

import com.google.gson.JsonObject;
import dev.felnull.itts.core.config.Config;
import dev.felnull.itts.core.config.voicetype.VoiceTextConfig;
import dev.felnull.itts.core.config.voicetype.VoicevoxConfig;
import dev.felnull.itts.core.util.JsonUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class ConfigImpl implements Config, Supplier<JsonObject> {
    private final String botToken;
    private final int themeColor;
    private final long cacheTime;
    private final VoiceTextConfigImpl voiceTextConfig;
    private final VoicevoxConfigImpl voicevoxConfig;
    private final VoicevoxConfigImpl coeirolnkConfig;
    private final VoicevoxConfigImpl sharevoxConfig;

    public ConfigImpl(JsonObject jo) {
        this.botToken = JsonUtils.getString(jo, "bot_token", DEFAULT_BOT_TOKEN);
        this.themeColor = JsonUtils.getInt(jo, "theme_color", DEFAULT_THEME_COLOR);
        this.cacheTime = JsonUtils.getLong(jo, "cache_time", DEFAULT_CACHE_TIME);
        this.voiceTextConfig = new VoiceTextConfigImpl(Optional.ofNullable(jo.getAsJsonObject("voice_text")).orElseGet(JsonObject::new));
        this.voicevoxConfig = new VoicevoxConfigImpl(Optional.ofNullable(jo.getAsJsonObject("voicevox")).orElseGet(JsonObject::new));
        this.coeirolnkConfig = new VoicevoxConfigImpl(Optional.ofNullable(jo.getAsJsonObject("coeirolnk")).orElseGet(JsonObject::new));
        this.sharevoxConfig = new VoicevoxConfigImpl(Optional.ofNullable(jo.getAsJsonObject("sharevox")).orElseGet(JsonObject::new));
    }

    @Override
    public JsonObject get() {
        var jo = new JsonObject();
        jo.addProperty("bot_token", this.botToken);
        jo.addProperty("theme_color", this.themeColor);
        jo.addProperty("cache_time", this.cacheTime);
        jo.add("voice_text", this.voiceTextConfig.toJson());
        jo.add("voicevox", this.voicevoxConfig.toJson());
        jo.add("coeirolnk", this.coeirolnkConfig.toJson());
        jo.add("sharevox", this.sharevoxConfig.toJson());
        return jo;
    }

    @Override
    public @NotNull String getBotToken() {
        return botToken;
    }

    @Override
    public int getThemeColor() {
        return themeColor;
    }

    @Override
    public long getCacheTime() {
        return cacheTime;
    }

    @Override
    public VoiceTextConfig getVoiceTextConfig() {
        return voiceTextConfig;
    }

    @Override
    public VoicevoxConfig getVoicevoxConfig() {
        return voicevoxConfig;
    }

    @Override
    public VoicevoxConfig getCoeirolnkConfig() {
        return coeirolnkConfig;
    }

    @Override
    public VoicevoxConfig getSharevoxConfig() {
        return sharevoxConfig;
    }


}
