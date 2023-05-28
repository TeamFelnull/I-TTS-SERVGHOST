package dev.felnull.itts.servghost.control;

import dev.felnull.itts.servghost.control.config.ConfigManager;
import dev.felnull.itts.servghost.control.config.OverwriteConfig;
import dev.felnull.itts.servghost.control.connect.ControlServer;
import dev.felnull.itts.servghost.share.config.ConnectionConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SERVGHOST版I-TTSを制御するためのプログラム
 */
public class Main {

    /**
     * ロガー
     */
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * 制御サーバー
     */
    public static final ControlServer CONTROL_SERVER = new ControlServer();

    /**
     * 接続に関するコンフィグ
     */
    public static ConnectionConfig CONNECTION_CONFIG;

    /**
     * クライアントのTTSコンフィグを上書きするコンフィグ
     */
    public static OverwriteConfig OVERWRITE_TTS_CONFIG;

    /**
     * クライアントのDBコンフィグを上書きするコンフィグ
     */
    public static OverwriteConfig OVERWRITE_DB_CONFIG;

    public static void main(String[] args) throws Exception {
        var configManager = ConfigManager.getInstance();

        CONNECTION_CONFIG = configManager.loadConnectionConfig();
        OVERWRITE_TTS_CONFIG = configManager.loadOverwriteTTSConfig();
        OVERWRITE_DB_CONFIG = configManager.loadOverwriteDBConfig();

        CONTROL_SERVER.start();
    }
}