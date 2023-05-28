package dev.felnull.itts.servghost.control.connect;

import dev.felnull.itts.servghost.control.ClientInstance;
import dev.felnull.itts.servghost.control.Main;
import dev.felnull.itts.servghost.share.connect.BaseSession;
import dev.felnull.itts.servghost.share.connect.ConnectionDefine;
import dev.felnull.itts.servghost.share.connect.object.S2CStartSendObject;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;

/**
 * サーバー側のセッション
 *
 * @author MORIMORI0317
 */
public class ServerSession extends BaseSession {
    /**
     * クライアント
     */
    private ClientInstance clientInstance;

    /**
     * コンストラクタ
     *
     * @param socket 接続用ソケット
     */
    public ServerSession(@NotNull Socket socket) {
        super(socket);
    }

    @Override
    protected void onReceive(@NotNull Object object) {
        //TODO 受信処理
        System.out.println(object);

        send(object);
    }

    @Override
    protected @NotNull Logger getLogger() {
        return Main.LOGGER;
    }

    @Override
    protected void onStart() {
        this.clientInstance.onOpenSession();

        send(new S2CStartSendObject(this.clientInstance.getId(), ConnectionDefine.CONNECTION_VERSION, Main.OVERWRITE_TTS_CONFIG.getOverwriteJson().toString(), Main.OVERWRITE_DB_CONFIG.getOverwriteJson().toString()));
    }

    @Override
    protected void onStop() {
        this.clientInstance.onCloseSession();
    }

    public void setClientInstance(ClientInstance clientInstance) {
        this.clientInstance = clientInstance;
    }
}
