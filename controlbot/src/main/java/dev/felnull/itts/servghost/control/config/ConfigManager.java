package dev.felnull.itts.servghost.control.config;

import dev.felnull.itts.servghost.control.Main;
import dev.felnull.itts.servghost.share.config.ConfigLoader;
import dev.felnull.itts.servghost.share.config.ConnectionConfig;

import java.io.File;

public class ConfigManager {
    private static final ConfigManager INSTANCE = new ConfigManager();
    private static final File CONNECTION_CONFIG_FILE = new File("./connection_config.json");
    private static final File OVERWRITE_TTS_CONFIG_FILE = new File("./overwrite_tts_config.json");
    private static final File OVERWRITE_DB_CONFIG_FILE = new File("./overwrite_db_config.json");

    public static ConfigManager getInstance() {
        return INSTANCE;
    }

    public ConnectionConfig loadConnectionConfig() {
        return ConfigLoader.loadConfig(Main.LOGGER, CONNECTION_CONFIG_FILE, ConnectionConfig::new, "Connection Config");
    }

    public OverwriteConfig loadOverwriteTTSConfig() {
        return new OverwriteConfig(ConfigLoader.loadSimpleJson(Main.LOGGER, OVERWRITE_TTS_CONFIG_FILE, "Overwrite TTS Config"));
    }

    public OverwriteConfig loadOverwriteDBConfig() {
        return new OverwriteConfig(ConfigLoader.loadSimpleJson(Main.LOGGER, OVERWRITE_DB_CONFIG_FILE, "Overwrite DB Config"));
    }
}
