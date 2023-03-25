package dev.felnull.itts;

import dev.felnull.itts.core.ITTSRuntime;
import dev.felnull.itts.config.DBConfig;
import dev.felnull.itts.config.SERVGHostConfigManager;
import dev.felnull.itts.control.ControlClient;
import dev.felnull.itts.savedata.SERVGHostSaveDataManager;

public class Main {
    public static final ControlClient CONTROL_CLIENT = new ControlClient();
    public static ITTSRuntime RUNTIME;
    public static DBConfig DB_CONFIG;

    public static void main(String[] args) {
        var configManager = SERVGHostConfigManager.getInstance();
        DB_CONFIG = configManager.loadDBConfig();

        if (DB_CONFIG.getUrl().isEmpty() || DB_CONFIG.getUser().isEmpty())
            throw new RuntimeException("DB config url or user is empty");

        RUNTIME = ITTSRuntime.newRuntime(configManager, SERVGHostSaveDataManager.getInstance(), null);
        RUNTIME.execute();
    }
}