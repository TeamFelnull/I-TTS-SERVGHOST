package dev.felnull.itts.servghost.share.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    public static String getString(@NotNull JsonObject jo, @NotNull String key, String defaultValue) {
        return jo.has(key) && jo.get(key).isJsonPrimitive() && jo.get(key).getAsJsonPrimitive().isString() ? jo.get(key).getAsString() : defaultValue;
    }

    public static int getInt(@NotNull JsonObject jo, @NotNull String key, int defaultValue) {
        return jo.has(key) && jo.get(key).isJsonPrimitive() && jo.get(key).getAsJsonPrimitive().isNumber() ? jo.get(key).getAsInt() : defaultValue;
    }

    public static JsonObject overwrite(@NotNull JsonObject base, @NotNull JsonObject overwrite) {
        return overwrite_(base.deepCopy(), overwrite).getAsJsonObject();
    }

    private static JsonElement overwrite_(JsonElement base, JsonElement overwrite) {
        if (base instanceof JsonObject jsonObject && overwrite instanceof JsonObject overwriteJo) {
            JsonObject ret = new JsonObject();

            List<String> keys = new LinkedList<>();

            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                JsonElement je = entry.getValue();
                JsonElement ov = overwriteJo.get(entry.getKey());

                if (ov != null)
                    je = overwrite_(je, ov);

                ret.add(entry.getKey(), je);

                keys.add(entry.getKey());
            }

            for (Map.Entry<String, JsonElement> entry : overwriteJo.entrySet()) {
                if (keys.contains(entry.getKey())) continue;

                ret.add(entry.getKey(), entry.getValue());
            }

            return ret;
        } else {
            return overwrite;
        }
    }
}
