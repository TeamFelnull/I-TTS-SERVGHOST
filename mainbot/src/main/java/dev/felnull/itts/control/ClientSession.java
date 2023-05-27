package dev.felnull.itts.control;

import dev.felnull.itts.Main;
import dev.felnull.itts.servghost.share.BaseSession;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;

/**
 * クライアント側のセッション
 *
 * @author MORIMORI0317
 */
public class ClientSession extends BaseSession {
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
        //TODO 受信処理
        System.out.println(object);
    }

    @Override
    protected @NotNull Logger getLogger() {
        return Main.LOGGER;
    }
}
