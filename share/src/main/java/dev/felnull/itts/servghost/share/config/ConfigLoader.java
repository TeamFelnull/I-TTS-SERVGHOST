package dev.felnull.itts.servghost.share.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dev.felnull.itts.servghost.share.util.JsonUtils;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConfigLoader {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T extends Supplier<JsonObject>> T loadConfig(Logger logger, File file, Function<JsonObject, T> createConfig, String configName) {
        return loadConfig(logger, file, createConfig, configName, null);
    }

    public static <T extends Supplier<JsonObject>> T loadConfig(Logger logger, File file, Function<JsonObject, T> createConfig, String configName, JsonObject overwrite) {
        JsonObject jo = null;

        if (file.exists()) {
            try (Reader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
                jo = GSON.fromJson(reader, JsonObject.class);
            } catch (JsonSyntaxException | IOException ex) {
                throw new RuntimeException("Failed to load " + configName, ex);
            }
        }

        JsonObject joo = Optional.ofNullable(jo).orElseGet(JsonObject::new);

        if (overwrite != null)
            joo = JsonUtils.overwrite(joo, overwrite);

        var config = createConfig.apply(joo);

        if (jo == null) {
            try (Writer writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
                GSON.toJson(config.get(), writer);
            } catch (IOException e) {
                logger.error("Failed to overwrite " + configName, e);
            }
        }

        return config;
    }

    public static JsonObject loadSimpleJson(Logger logger, File file, String configName) {
        JsonObject jo = null;

        if (file.exists()) {
            try (Reader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
                jo = GSON.fromJson(reader, JsonObject.class);
            } catch (JsonSyntaxException | IOException ex) {
                throw new RuntimeException("Failed to load " + configName, ex);
            }
        }

        if (jo == null) {
            jo = new JsonObject();

            try (Writer writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
                GSON.toJson(jo, writer);
            } catch (IOException e) {
                logger.error("Failed to overwrite " + configName, e);
            }
        }

        return jo;
    }
}
