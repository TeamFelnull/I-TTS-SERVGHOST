package dev.felnull.itts.control;

import dev.felnull.itts.Main;
import dev.felnull.itts.servghost.share.connect.BaseSession;
import dev.felnull.itts.servghost.share.connect.object.S2CStartSendObject;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;
import java.util.UUID;

/**
 * クライアント側のセッション
 *
 * @author MORIMORI0317
 */
public class ClientSession extends BaseSession {
    /**
     * サーバー側で割り振られたID
     */
    private UUID clientId;

    /**
     * コンストラクタ
     *
     * @param socket 接続用ソケット
     */
    public ClientSession(@NotNull Socket socket) {
        super(socket);
    }

    @Override
    protected void onStart() {
        Main.CONTROL_CLIENT.setCurrentSession(this);
    }

    @Override
    protected void onStop() {
        Main.CONTROL_CLIENT.setCurrentSession(null);
    }

    @Override
    protected void onReceive(@NotNull Object object) {

        if (object instanceof S2CStartSendObject s2CStartSendObject) {
            ClientReceiveHandler.receiveS2CStart(this, s2CStartSendObject);
        }
    }

    @Override
    protected @NotNull Logger getLogger() {
        return Main.LOGGER;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}
