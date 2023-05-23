package dev.felnull.itts;

import dev.felnull.itts.config.SERVGHostConfigManager;
import dev.felnull.itts.core.ITTSRuntimeContext;
import dev.felnull.itts.core.cache.GlobalCacheAccess;
import dev.felnull.itts.core.config.ConfigContext;
import dev.felnull.itts.core.log.LogContext;
import dev.felnull.itts.core.savedata.SaveDataAccess;
import dev.felnull.itts.savedata.SERVGHostSaveDataManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SERVGHostITTSRuntimeContext implements ITTSRuntimeContext {
    private final LogContext logContext = new LogContextImpl();

    @Override
    public @NotNull ConfigContext getConfigContext() {
        return SERVGHostConfigManager.getInstance();
    }

    @Override
    public @NotNull SaveDataAccess getSaveDataAccess() {
        return SERVGHostSaveDataManager.getInstance();
    }

    @Override
    public @Nullable Supplier<GlobalCacheAccess> getGlobalCacheAccessFactory() {
        return null;
    }

    @Override
    public @NotNull LogContext getLogContext() {
        return logContext;
    }

    private static class LogContextImpl implements LogContext {
        @Override
        public @NotNull Logger getLogger() {
            return Main.LOGGER;
        }
    }
}
