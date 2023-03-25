package dev.felnull.itts.config;

import com.google.gson.JsonObject;
import dev.felnull.itts.core.util.JsonUtils;

import java.util.function.Supplier;

public class DBConfig implements Supplier<JsonObject> {
    private final String url;
    private final String user;
    private final String password;

    public DBConfig(JsonObject jsonObject) {
        this.url = JsonUtils.getString(jsonObject, "url", "");
        this.user = JsonUtils.getString(jsonObject, "user", "");
        this.password = JsonUtils.getString(jsonObject, "password", "");
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    @Override
    public JsonObject get() {
        var jo = new JsonObject();
        jo.addProperty("url", url);
        jo.addProperty("user", user);
        jo.addProperty("password", password);
        return jo;
    }
}
