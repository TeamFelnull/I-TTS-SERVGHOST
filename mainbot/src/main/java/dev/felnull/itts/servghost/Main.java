package dev.felnull.itts.servghost;

import dev.felnull.itts.core.ITTSRuntime;
import dev.felnull.itts.core.config.Config;
import dev.felnull.itts.core.config.ConfigAccess;
import dev.felnull.itts.core.savedata.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Map;

public class Main {
    public static ITTSRuntime RUNTIME;

    public static void main(String[] args) {
        RUNTIME = ITTSRuntime.newRuntime(new TestConfigAccess(), new TestSaveDataAccess(), null/*GlobalCacheTest::new*/);
        RUNTIME.execute();
    }

    private static class TestConfigAccess implements ConfigAccess {
        @Override
        public @Nullable Config loadConfig() {
            return null;
        }
    }

    private static class TestSaveDataAccess implements SaveDataAccess {

        @Override
        public boolean init() {
            return false;
        }

        @Override
        public @NotNull ServerData getServerData(long l) {
            return null;
        }

        @Override
        public @NotNull ServerUserData getServerUserData(long l, long l1) {
            return null;
        }

        @Override
        public @NotNull @Unmodifiable List<DictUseData> getAllDictUseData(long l) {
            return null;
        }

        @Override
        public @Nullable DictUseData getDictUseData(long l, @NotNull String s) {
            return null;
        }

        @Override
        public void addDictUseData(long l, @NotNull String s, int i) {

        }

        @Override
        public void removeDictUseData(long l, @NotNull String s) {

        }

        @Override
        public @NotNull BotStateData getBotStateData(long l) {
            return null;
        }

        @Override
        public @NotNull @Unmodifiable Map<Long, BotStateData> getAllBotStateData() {
            return null;
        }

        @Override
        public @NotNull @Unmodifiable List<DictData> getAllServerDictData(long l) {
            return null;
        }

        @Override
        public @Nullable DictData getServerDictData(long l, @NotNull String s) {
            return null;
        }

        @Override
        public void addServerDictData(long l, @NotNull String s, @NotNull String s1) {

        }

        @Override
        public void removeServerDictData(long l, @NotNull String s) {

        }

        @Override
        public @NotNull @Unmodifiable List<DictData> getAllGlobalDictData() {
            return null;
        }

        @Override
        public @Nullable DictData getGlobalDictData(@NotNull String s) {
            return null;
        }

        @Override
        public void addGlobalDictData(@NotNull String s, @NotNull String s1) {

        }

        @Override
        public void removeGlobalDictData(@NotNull String s) {

        }

        @Override
        public @NotNull @Unmodifiable List<Long> getAllDenyUser(long l) {
            return null;
        }
    }
}