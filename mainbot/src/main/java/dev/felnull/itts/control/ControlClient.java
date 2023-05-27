package dev.felnull.itts.control;

import dev.felnull.itts.Main;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * I-TTSと制御用BOTを接続するためのクライアント
 *
 * @author MORIMORI0317
 */
public class ControlClient {

    /**
     * 接続制御スレッド
     */
    private final ConnectionThread connectionControlThread = new ConnectionThread();

    /**
     * 現在接続中のセッション
     */
    private final AtomicReference<ClientSession> currentSession = new AtomicReference<>();

    /**
     * 制御用BOTとの接続を開始する
     */
    public void start() {
        Main.LOGGER.info("Started the control client");
        this.connectionControlThread.start();
    }

    /**
     * サーバーと接続中かどうか
     *
     * @return 結果
     */
    public boolean isConnectSession() {
        return this.currentSession.get() != null;
    }

    /**
     * 現在接続中のセッションを指定する
     *
     * @param clientSession 現在接続中のセッション
     */
    public void setCurrentSession(@Nullable ClientSession clientSession) {
        this.currentSession.set(clientSession);
    }
}
