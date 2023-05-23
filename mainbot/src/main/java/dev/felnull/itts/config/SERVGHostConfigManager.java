package dev.felnull.itts.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dev.felnull.itts.Main;
import dev.felnull.itts.core.config.Config;
import dev.felnull.itts.core.config.ConfigContext;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class SERVGHostConfigManager implements ConfigContext {
    private static final SERVGHostConfigManager INSTANCE = new SERVGHostConfigManager();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File TTS_CONFIG_FILE = new File("./tts_config.json");
    private static final File DB_CONFIG_FILE = new File("./db_config.json");

    public static SERVGHostConfigManager getInstance() {
        return INSTANCE;
    }

    @Override
    public @Nullable Config loadConfig() {
        return loadConfig(TTS_CONFIG_FILE, ConfigImpl::new, "TTS Config");
    }

    public DBConfig loadDBConfig() {
        return loadConfig(DB_CONFIG_FILE, DBConfig::new, "DB Config");
    }

    private <T extends Supplier<JsonObject>> T loadConfig(File file, Function<JsonObject, T> createConfig, String configName) {
        JsonObject jo = null;

        if (file.exists()) {
            try (Reader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
                jo = GSON.fromJson(reader, JsonObject.class);
            } catch (JsonSyntaxException | IOException ex) {
                throw new RuntimeException("Failed to load " + configName, ex);
            }
        }

        var config = createConfig.apply(Optional.ofNullable(jo).orElseGet(JsonObject::new));

        if (jo == null) {
            try (Writer writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
                GSON.toJson(config.get(), writer);
            } catch (IOException e) {
                Main.RUNTIME.getLogger().error("Failed to overwrite " + configName, e);
            }
        }

        return config;
    }
}
