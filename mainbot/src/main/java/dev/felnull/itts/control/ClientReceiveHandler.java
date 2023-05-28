package dev.felnull.itts.control;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.felnull.itts.Main;
import dev.felnull.itts.config.SERVGHostConfigManager;
import dev.felnull.itts.servghost.share.connect.ConnectionDefine;
import dev.felnull.itts.servghost.share.connect.object.S2CStartSendObject;

/**
 * 受信したデータを処理する
 */
public class ClientReceiveHandler {
    /**
     * GSON
     */
    private static final Gson GSON = new Gson();

    /**
     * 開始時にサーバーからクライアントに呼ばれるデータを処理
     *
     * @param s2CStartSendObject 開始時のデータ
     */
    protected static void receiveS2CStart(ClientSession session, S2CStartSendObject s2CStartSendObject) {
        Main.LOGGER.info("Server information received");

        Preconditions.checkNotNull(s2CStartSendObject.clientId(), "Client id is null");
        Preconditions.checkNotNull(s2CStartSendObject.overwriteTTSConfig(), "Overwrite TTS Config is null");
        Preconditions.checkNotNull(s2CStartSendObject.overwriteDBConfig(), "Overwrite DB Config is null");

        if (s2CStartSendObject.version() != ConnectionDefine.CONNECTION_VERSION) {
            Main.LOGGER.error("Connection version mismatch");
            return;
        }

        session.setClientId(s2CStartSendObject.clientId());

        var configManager = SERVGHostConfigManager.getInstance();
        configManager.setOverwriteTTSConfig(GSON.fromJson(s2CStartSendObject.overwriteTTSConfig(), JsonObject.class));
        configManager.setOverwriteDBConfig(GSON.fromJson(s2CStartSendObject.overwriteDBConfig(), JsonObject.class));

        synchronized (Main.CONTROL_CLIENT.threadLock) {
            Main.CONTROL_CLIENT.threadLock.notifyAll();
        }
    }
}
