package dev.felnull.itts.servghost.control.connect;

import dev.felnull.itts.servghost.control.Main;
import dev.felnull.itts.servghost.share.BaseSession;
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
    }

    @Override
    protected @NotNull Logger getLogger() {
        return Main.LOGGER;
    }

    @Override
    protected void onStart() {
        Main.CONTROL_SERVER.addSession(this);
    }

    @Override
    protected void onStop() {
        Main.CONTROL_SERVER.removeSession(this);
    }
}
