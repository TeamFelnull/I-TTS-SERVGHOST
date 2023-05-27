package dev.felnull.itts;

import dev.felnull.itts.config.DBConfig;
import dev.felnull.itts.config.SERVGHostConfigManager;
import dev.felnull.itts.control.ControlClient;
import dev.felnull.itts.core.ITTSRuntime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static final ControlClient CONTROL_CLIENT = new ControlClient();
    public static ITTSRuntime RUNTIME;
    public static DBConfig DB_CONFIG;

    public static void main(String[] args) {
        var configManager = SERVGHostConfigManager.getInstance();
        DB_CONFIG = configManager.loadDBConfig();

        CONTROL_CLIENT.start();

        if (DB_CONFIG.getUrl().isEmpty() || DB_CONFIG.getUser().isEmpty())
            throw new RuntimeException("DB config url or user is empty");

        RUNTIME = ITTSRuntime.newRuntime(new SERVGHostITTSRuntimeContext());
        RUNTIME.execute();
    }
}