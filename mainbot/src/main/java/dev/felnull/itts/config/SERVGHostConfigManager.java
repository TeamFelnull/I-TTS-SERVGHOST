package dev.felnull.itts.config;

import com.google.gson.JsonObject;
import dev.felnull.itts.Main;
import dev.felnull.itts.core.config.Config;
import dev.felnull.itts.core.config.ConfigContext;
import dev.felnull.itts.servghost.share.config.ConfigLoader;
import dev.felnull.itts.servghost.share.config.ConnectionConfig;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public class SERVGHostConfigManager implements ConfigContext {
    private static final SERVGHostConfigManager INSTANCE = new SERVGHostConfigManager();
    private static final File TTS_CONFIG_FILE = new File("./tts_config.json");
    private static final File DB_CONFIG_FILE = new File("./db_config.json");
    private static final File CONNECTION_CONFIG_FILE = new File("./connection_config.json");
    private JsonObject overwriteTTSConfig;
    private JsonObject overwriteDBConfig;

    public static SERVGHostConfigManager getInstance() {
        return INSTANCE;
    }

    @Override
    public @Nullable Config loadConfig() {
        return ConfigLoader.loadConfig(Main.LOGGER, TTS_CONFIG_FILE, ConfigImpl::new, "TTS Config", this.overwriteTTSConfig);
    }

    public DBConfig loadDBConfig() {
        return ConfigLoader.loadConfig(Main.LOGGER, DB_CONFIG_FILE, DBConfig::new, "DB Config", this.overwriteDBConfig);
    }

    public ConnectionConfig loadConnectionConfig() {
        return ConfigLoader.loadConfig(Main.LOGGER, CONNECTION_CONFIG_FILE, ConnectionConfig::new, "Connection Config");
    }

    public void setOverwriteDBConfig(JsonObject overwriteDBConfig) {
        this.overwriteDBConfig = overwriteDBConfig;
    }

    public void setOverwriteTTSConfig(JsonObject overwriteTTSConfig) {
        this.overwriteTTSConfig = overwriteTTSConfig;
    }
}
